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
        test.feedMoneyValid(cash);
        Assert.assertTrue(test.balance == 5);
    }

    @Test
    public void feedMoneyFailedMoneyTest(){
        VendingMachineCLI test = new VendingMachineCLI();
        String cash = "c";
        test.feedMoneyValid(cash);
        Assert.assertTrue(test.balance == 0);
    }


}
