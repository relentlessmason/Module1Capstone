package com.techelevator;

public class Change {
    public void change (double coinChange){

        int quarters = 0;
        int dimes = 0;
        int nickels = 0;

        while (coinChange >= .25){
            quarters = quarters +1;
            coinChange = coinChange - .25;
        }
        while (coinChange >= .10){
            dimes = dimes +1;
            coinChange = coinChange - .10;
        }
        while (coinChange >= .05){
            nickels = nickels +1;
            coinChange = coinChange -.05;
        }
        while (coinChange <=.04) {
            System.out.println("Invalid change, please add larger coins");
        }
        System.out.println("Thank you for your purchase, here is your change!" + quarters + "quarters, " + dimes + "dimes, " + nickels + "nickels, enjoy your snack!");
    }
}
