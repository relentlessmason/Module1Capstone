package com.techelevator;

public class VendingMachineItem {
    public  String itemName;
    public double itemPrice;
    public static String itemType;
    public int remainingStock;

    public VendingMachineItem(String itemName, double itemPrice, String itemType, int remainingStock) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemType = itemType;
        this.remainingStock	= remainingStock;
    }

    public  String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public static String getItemType() {
        return itemType;
    }

    public int getRemainingStock() {
        return remainingStock;
    }



    public static String GetSound(String itemType){
        if(VendingMachineItem.itemType.equals("Candy")) {			// create if statements to apply sounds to each item
            return "Munch, Munch, Yum!";
        }

        if(VendingMachineItem.itemType.equals("Chip")) {
            return "Crunch, Crunch, Yum!";
        }

        if(VendingMachineItem.itemType.equals("Drink")) {
            return "Glug, Glug, Yum!";
        }

        if(VendingMachineItem.itemType.equals("Gum")) {
            return "Chew, Chew, Yum!";
        }

        return null;
    }
}
