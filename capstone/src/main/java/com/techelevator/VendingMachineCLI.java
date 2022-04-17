package com.techelevator;

import com.techelevator.view.Menu;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.io.*;
import com.techelevator.VendingMachineItem;


public class VendingMachineCLI {

	//variables
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_EXIT};
	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION};
	private Menu menu;
	private double balance;

	//getter & setter methods
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}


	public void run() throws InterruptedException, FileNotFoundException {
		System.out.println("[generic welcome message]");
		File menuOptions = new File("capstone\\vendingmachine.csv");

		while (true) {
			String choice = (String) this.menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {

				try {
					Scanner fileScanner = new Scanner(menuOptions); // while loop prints the menu options out for customer
					while (fileScanner.hasNextLine()) {
						String data = fileScanner.nextLine();
						System.out.println(data);
					}
					fileScanner.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			if (choice.equals(MAIN_MENU_EXIT)) { // this is what happens if you select 3 on the main menu
				Scanner opt3 = new Scanner(System.in);
				System.out.println("Are you sure you want to exit? :");
				String rUSure = opt3.nextLine();
				if (rUSure.equalsIgnoreCase("n") || rUSure.equalsIgnoreCase("no")) {
					run();
				} else if (rUSure.equalsIgnoreCase("y") || rUSure.equalsIgnoreCase("yes")) {
					if (getBalance() > 0.00) {
						//change(getBalance());
					}
					opt3.close();
					System.out.println("Thank you for your purchase! Goodbye!");
				}
			}


			double cashInput = getBalance(); //we need this variable for a few places in the money handling section below

				if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
					this.menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS); // for some reason,
					// this ^ line of code makes it possible to enter the purchasing screen
					Scanner secondChoice = new Scanner(System.in); //new scanner for second menu screen
					String secondPath = secondChoice.nextLine(); //takes in the user input to be manipulated

					boolean moneyChoice = true; //boolean that determines whether user is trying to add more money

					if (secondPath.equals("1")) {        //takes user to the place to feed in the monies

						try {
							while (moneyChoice == true) {
								System.out.println("Your balance is: " + getBalance());
								System.out.println();
								System.out.println("Please enter the amount of cash you wish to input: (1, 2, 5, 10)");
								String cash = secondChoice.nextLine();
								double doubleCash = Double.parseDouble(cash); // takes the user input and parses in into a usable double

								cashInput = cashInput + doubleCash; // calculates balance available to make purchases
								System.out.println("You have $" + cashInput + " to spend, would you like to add more?  Please select Yes (Y) or No (N).");
								String addMoreMoney = secondChoice.nextLine();
								addMoreMoney = addMoreMoney.toLowerCase();
								moneyChoice = addMoreMoney.contentEquals("y");
							}
						} catch (NumberFormatException ex) {
							System.err.println("Please Insert Valid Currency");
						}
					} //ends feed money

					if (secondPath.equals("2") || !moneyChoice) {
						while (true) {
							System.out.println("Please select a product from the list below.");
							Scanner fileScanner = new Scanner(menuOptions);
							while (fileScanner.hasNextLine()) {
								String data =
										fileScanner.nextLine();
								System.out.println(data);
							}
							fileScanner.close();
							Scanner itemSelect = new Scanner(System.in);

							System.out.println("Enter Item Code: ");
							String codeEntered = itemSelect.nextLine().toUpperCase();
							VendingMachineItem item = this.menu.getItem(codeEntered);
							double price = item.getItemPrice();
							balance = cashInput - price;
							if (balance <= 0) {
								System.out.println("Please insert more money.");
							} else {
								System.out.println(item.getItemName() + " has been dispensed.");
								String soundBite = VendingMachineItem.GetSound();
								System.out.println(soundBite);
								System.out.println("You have $" + balance + " remaining.");
								System.out.println();
							}
							run(); //takes you back to the main menu (and you get to keep your money!)

							//	System.out.println("Make another purchase?");
							//	Scanner makeNewPurchase = new Scanner(System.in);
							// if (makeNewPurchase.equals("y") || makeNewPurchase.equals("Y")) {
							//		System.out.println(PURCHASE_MENU_OPTIONS);
							// }

						}


					}
					if (secondPath.equals("3")) {
						double tracker = ((double) (getBalance()));

						double totalQuartersToReturn = 0;
						double totalDimesToReturn = 0;
						double totalNickelsToReturn = 0;

						double quarter = .25;
						double dime = .10;
						double nickel = .05;
						while (tracker > 0) {
							if (tracker >= quarter) {
								totalQuartersToReturn++;
								tracker -= quarter;
							} else if (tracker >= dime) {
								totalDimesToReturn++;
								tracker -= dime;
							} else if (tracker >= nickel) {
								totalNickelsToReturn++;
								tracker -= nickel;
							}
						}
						this.balance = 0;

						System.out.println("Your change is " + totalQuartersToReturn + " quarters, " + totalDimesToReturn +
								" dimes, " + "and " + totalNickelsToReturn + " nickles.");
					}

				}

			}
		}



	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

}