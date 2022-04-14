package com.techelevator;

import com.techelevator.view.Menu;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

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

	public void run() {
		File menuOptions = new File("capstone/vendingmachine.csv");
		Scanner checkUserChoice = new Scanner(System.in);
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
			}
		}

	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
