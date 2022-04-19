package com.techelevator.view;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.techelevator.VendingMachineCLI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import junit.framework.TestCase;

public class VendingMachineCLITest {

    @Test
    public void aBalanceShouldPrint(){

    }

    @Test
    public void returningBalanceCrazyChangeTest(){

    }

    @Test
    public void FeedMoneyAccurateMoneyTest(){
        VendingMachineCLI test = new VendingMachineCLI();
        String cash = "5";
        test.FeedMoneyChecking(cash);
        Assert.assertTrue(test.balance == 5);
    }

    @Test
    public void FeedMoneyFailedMoneyTest(){
        VendingMachineCLI test = new VendingMachineCLI();
        String cash = "c";
        test.FeedMoneyChecking(cash);
        Assert.assertTrue(test.balance == 0);
    }

}