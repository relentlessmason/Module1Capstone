package com.techelevator;

public class VendingMachineItem {
    public String itemName;
    public double itemPrice;
    public String itemType;
    public int remainingStock;

    public VendingMachineItem(String itemName, double itemPrice, String itemType, int remainingStock) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemType = itemType;
        this.remainingStock	= remainingStock;
        //Do getters for everything and setter for remaining stock since it will change
    }

    public String GetSound(){
        if(this.itemType.equals("Candy")) {			// create if statements to apply sounds to each item
            return "Munch, Munch, Yum!";
        }

        if(this.itemType.equals("Chip")) {
            return "Crunch, Crunch, Yum!";
        }

        if(this.itemType.equals("Drink")) {
            return "Glug, Glug, Yum!";
        }

        if(this.itemType.equals("Gum")) {
            return "Chew, Chew, Yum!";
        }

        return null;
    }
}
