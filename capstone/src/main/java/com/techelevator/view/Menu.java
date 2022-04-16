package com.techelevator.view;


import com.techelevator.VendingMachineItem;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Menu() {
		HashMap<String, VendingMachineItem> vendingMachineItems = new HashMap<String, VendingMachineItem>(){};
		vendingMachineItems.put("A1", new VendingMachineItem("Potato Crisps", 3.05, "Chip", 5));
		vendingMachineItems.put("A2", new VendingMachineItem("Stackers",1.45, "Chip", 5));
		vendingMachineItems.put("A3", new VendingMachineItem("Grain Waves", 2.75, "Chip", 5));
		vendingMachineItems.put("A4", new VendingMachineItem("Cloud Popcorn", 3.65, "Chip", 5));
		vendingMachineItems.put("B1", new VendingMachineItem("Moonpie", 1.80, "Candy", 5));
		vendingMachineItems.put("B2", new VendingMachineItem("Cowtales", 1.50 , "Candy", 5));
		vendingMachineItems.put("B3", new VendingMachineItem("Wonka Bar", 1.50,"Candy",5));
		vendingMachineItems.put("B4", new VendingMachineItem("Crunchie", 1.75,"Candy",5));
		vendingMachineItems.put("C1", new VendingMachineItem("Cola", 1.25,"Drink",5));
		vendingMachineItems.put("C2", new VendingMachineItem("Dr. Salt", 1.50,"Drinkk",5));
		vendingMachineItems.put("C3", new VendingMachineItem("Mountain Melter", 1.50,"Drink",5));
		vendingMachineItems.put("C4", new VendingMachineItem("Heavy", 1.50,"Drink",5));
		vendingMachineItems.put("D1", new VendingMachineItem("U-Chews", .85,"Gum",5));
		vendingMachineItems.put("D2", new VendingMachineItem("Little League Chew", .95,"Gum",5));
		vendingMachineItems.put("D3", new VendingMachineItem("Chiclets", .75,"Gum",5));
		vendingMachineItems.put("D4", new VendingMachineItem("Triplemint", .75,"Gum",5));
	}

//	public VendingMachineItem GetItem(String key) {
//		Set<String> key = ve
//		return null;
//	}


	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}
}
