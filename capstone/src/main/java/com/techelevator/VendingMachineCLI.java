package com.techelevator;

import com.techelevator.view.Menu;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import com.techelevator.VendingMachineItem;


import static com.techelevator.VendingMachineItem.itemType;


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
	private File menuOptions;
	private double cashInput;
	File vendingLog = new File("capstone/vendinglog.log");
	DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();

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

	private void PrintBalance() throws Exception {
		try {
			Scanner scanner = new Scanner(System.in);
			while (true) {
				System.out.println("Your balance is: " + getBalance());
				System.out.println();
				System.out.println("Please enter the amount of cash you wish to input: (1, 2, 5, 10)");
				String cash = scanner.nextLine();
				double doubleCash = Double.parseDouble(cash); // takes the user input and parses in into a usable double
				if(cash.equals("1") || cash.equals("2") || cash.equals("5") || cash.equals("10")) {
					this.balance = this.cashInput + doubleCash; // calculates balance available to make purchases
					System.out.println("You have $" + this.balance + " to spend, would you like to add more?  Please select Yes (Y) or No (N).");
					String addMoreMoney = scanner.nextLine();
					addMoreMoney = addMoreMoney.toLowerCase();

					if (addMoreMoney.contentEquals("n")) {
						break;
					}
				}
				else {
					System.out.println("Please enter valid bills");
				}
				try(PrintWriter writer = new PrintWriter(new FileWriter(vendingLog, true))){
					writer.println(dateTime.format(now) + "FEED MONEY: " + cashInput + balance);
				}
			}
		}
		catch(Exception e){
			throw e;
		}
	}

	private void GetMainMenuOptionDisplayItems(String choice) throws FileNotFoundException{
		if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
			try {
				Scanner fileScanner = new Scanner(this.menuOptions); // while loop prints the menu options out for customer
				while (fileScanner.hasNextLine()) {
					String data = fileScanner.nextLine();
					System.out.println(data);
				}

				fileScanner.close();
			} catch (FileNotFoundException e) {
				throw e;
			}
		}
	}

	private void Exit(String choice) throws IOException, InterruptedException {
		if (choice.equals(MAIN_MENU_EXIT)) { // this is what happens if you select 3 on the main menu
			Scanner opt3 = new Scanner(System.in);
			System.out.println("Are you sure you want to exit? :");
			String rUSure = opt3.nextLine();
			if (rUSure.equalsIgnoreCase("n") || rUSure.equalsIgnoreCase("no")) {
				this.run();
			}
			if (rUSure.equalsIgnoreCase("y") || rUSure.equalsIgnoreCase("yes")) {
				if (getBalance() > 0.00) {
					returningBalance();
				}
				System.out.println("Thank you for your purchase! Goodbye!");
			}
			else {
				System.out.println("Invalid option, please try again.");
			}
		}
	}

	private void PurchaseMenuOption1(String purchaseChoice){
		if (purchaseChoice.equals(PURCHASE_MENU_FEED_MONEY)) {        //takes user to the place to feed in the monies
			try{
				PrintBalance();
			} catch (Exception ex) {
				System.err.println("Please Insert Valid Currency");
			}
		} //ends feed money
	}

	private void PurchaseMenuOption2(String purchaseChoice) throws IOException, InterruptedException {
		if (purchaseChoice.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
			while (true) {
				System.out.println("Please select a product from the list below.");
				Scanner fileScanner = new Scanner(this.menuOptions);
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
				if(item == null){
					System.out.println("Item doesn't exist, please enter valid item location");
				}
				if(item != null) {
					double price = item.getItemPrice();
					if (balance <= 0) {
						System.out.println("Please insert more money.");
					} else {
						balance = this.cashInput - price;
						try (PrintWriter writer = new PrintWriter(new FileWriter(vendingLog, true))) {
							writer.println(dateTime.format(now) + item.getItemName() + cashInput + balance);
						}
						System.out.println(item.getItemName() + " has been dispensed.");
						String soundBite = VendingMachineItem.GetSound(itemType);
						System.out.println(soundBite);
						System.out.println("You have $" + this.balance + " remaining.");
						System.out.println();
					}
				}
				this.run(); //takes you back to the main menu (and you get to keep your money!)

				//	System.out.println("Make another purchase?");
				//	Scanner makeNewPurchase = new Scanner(System.in);
				// if (makeNewPurchase.equals("y") || makeNewPurchase.equals("Y")) {
				//		System.out.println(PURCHASE_MENU_OPTIONS);
				// }

			}
		}
	}
	private void returningBalance() throws IOException {
		double tracker = getBalance();

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
			try(PrintWriter writer = new PrintWriter(new FileWriter(vendingLog, true))){
				writer.println(dateTime.format(now) + "GIVE GHANGE: " + balance + "0");
			}
		}
		this.balance = 0;

		System.out.println("Your change is " + totalQuartersToReturn + " quarters, " + totalDimesToReturn +
				" dimes, " + "and " + totalNickelsToReturn + " nickles.");

	}


	private void PurchaseMenuOption3(String purchaseChoice) throws IOException {
		if (purchaseChoice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
			returningBalance();
		}

	}

	public void run() throws InterruptedException, IOException {
		this.menuOptions = new File("capstone/vendingmachine.csv");

		while (true) {
			String choice = (String) this.menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			try {
				GetMainMenuOptionDisplayItems(choice);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			Exit(choice);

			this.cashInput = getBalance(); //we need this variable for a few places in the money handling section below

			if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				String purchaseChoice  = (String) this.menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				// this ^ line of code makes it possible to enter the purchasing screen
				PurchaseMenuOption1(purchaseChoice);
				PurchaseMenuOption2(purchaseChoice);
				PurchaseMenuOption3(purchaseChoice);
			}
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();

		}

}