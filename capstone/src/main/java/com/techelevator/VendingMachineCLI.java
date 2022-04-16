package com.techelevator;

import com.techelevator.view.Menu;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import com.techelevator.VendingMachineItem;

import javax.security.auth.SubjectDomainCombiner;

public class VendingMachineCLI {


	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_EXIT };

	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION};

	private Menu menu;
	private double balance;

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
		File menuOptions = new File("capstone/vendingmachine.csv");
		//Scanner checkUserChoice = new Scanner(System.in);
		Scanner userCash;
		//Scanner userProductID;
		double cashInput = 0;
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				try {
					Scanner fileScanner = new Scanner(menuOptions);
					while(fileScanner.hasNextLine()){
						String data = fileScanner.nextLine();
						System.out.println(data);
					}
					fileScanner.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}

			if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

				Scanner secondChoice = new Scanner(System.in);
				String secondPath = secondChoice.nextLine();
				boolean moneyChoice = true;
				if (secondPath.equals("1")) {		//feed some money
					userCash = new Scanner(System.in);
					try {
						while (moneyChoice == true) {
							System.out.println("Please enter the amount of cash you wish to input: (1, 2, 5, 10)");
							String cash = userCash.nextLine();
							double doubleCash = Double.parseDouble(cash);
							cashInput = cashInput + doubleCash;
							System.out.println("You have $" + cashInput + " to spend, would you like to add more?  Please select Yes (Y) or No (N).");
							String addMoreMoney = userCash.nextLine();
							addMoreMoney = addMoreMoney.toLowerCase();
							moneyChoice = addMoreMoney.contentEquals("y");
						}
					}
					catch (NumberFormatException ex){
						System.err.println("Please Insert Valid Currency");
					}
				}
				if (secondPath.equals("2") || !moneyChoice) {
					System.out.println("Please select a product from the list below.");
					Scanner fileScanner = new Scanner(menuOptions);
					while(fileScanner.hasNextLine()){
						String data = fileScanner.nextLine();
						System.out.println(data);
					}
					fileScanner.close();
					Scanner itemSelect = new Scanner(System.in);

					System.out.println("Enter Item Code: ");
					String codeEntered = itemSelect.nextLine();
				}
				if (secondPath.equals("3")) {
					System.out.println("Goodbye!");
					Thread.sleep(5000);
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
