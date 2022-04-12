package com.techelevator;


public class VendingMachineItem {
    public String itemName;
    public double itemPrice;
    public String itemSound;
    public String itemType;
    public int remainingStock;

    public VendingMachineItem(String itemName, double itemPrice, String itemType, int remainingStock) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemType = itemType;
        this.remainingStock	= remainingStock;

        if(this.itemType.equals("Candy")) {			// create if statements to apply sounds to each item
            this.itemSound 	= "Munch, Munch, Yum!";
        }
        if(this.itemType.equals("Chip")) {
            this.itemSound	= "Crunch, Crunch, Yum!";
        }
        if(this.itemType.equals("Drink")) {
            this.itemSound 	= "Glug, Glug, Yum!";
        }
        if(this.itemType.equals("Gum")) {
            this.itemSound 	= "Chew, Chew, Yum!";
        }

        //Do getters for everything and setter for remaining stock since it will change

    }
}
