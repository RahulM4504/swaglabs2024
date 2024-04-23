package com.swaglabs.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.swaglabs.base.BaseTest;

public class CartPageTest extends BaseTest{
    
    @BeforeClass
    public void cartpageSetup() {
	homePage = loginPage.dologin(prop.getProperty("username"),prop.getProperty("password"));
	homePage.openTheCart();	
    }
    
    @Test
    public void verifyCartPageHeading() {
	String heading = cartPage.getCartPageSeconderyHeading();
	Assert.assertEquals(heading, "Your Cart");
    }
    

}
