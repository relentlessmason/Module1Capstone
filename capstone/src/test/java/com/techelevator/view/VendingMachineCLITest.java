package com.techelevator.view;

import java.io.*;

import com.techelevator.VendingMachineCLI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import junit.framework.TestCase;

public class VendingMachineCLITest {


    @Test
    public void feedMoneyAccurateMoneyTest(){
        VendingMachineCLI test = new VendingMachineCLI();
        String cash = "5";
        test.feedMoneyChecking(cash);
        Assert.assertTrue(test.balance == 5);
    }

    @Test
    public void feedMoneyFailedMoneyTest(){
        VendingMachineCLI test = new VendingMachineCLI();
        String cash = "c";
        test.feedMoneyChecking(cash);
        Assert.assertTrue(test.balance == 0);
    }

    @Test
    public void choosingExitWithNotYOrN() throws IOException, InterruptedException {
        VendingMachineCLI test = new VendingMachineCLI();
        String rUSure = "c";
        test.exit(rUSure);
        Assert.assertEquals("Invalid input. Please enter valid input.", "Invalid input. Please enter valid input." );
    }
    @Test
    public void choosingExitWithYOrN() throws IOException, InterruptedException {
        VendingMachineCLI test = new VendingMachineCLI();
        String rUSure = "y";
        test.exit(rUSure);
        Assert.assertEquals("Thank you for your purchase! Goodbye!", "Thank you for your purchase! Goodbye!");
    }


}
