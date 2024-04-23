package com.swaglabs.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.swaglabs.base.BaseTest;


public class OverviewPageTest extends BaseTest{
    
    @BeforeClass
    public void overviewPageSetup() {
	homePage = loginPage.dologin(prop.getProperty("username"),prop.getProperty("password"));
    }
    
    @Test(priority=0)
    public void verifySumOfProductPriceEqualSItemTotalPriceTest() {
	homePage.checkCartForProducts();
	cartPage= homePage.openTheCart();
	checkoutInfoPage = cartPage.clickCheckoutButton();
	checkoutInfoPage.enterCheckoutDetails("Rahul", "Mhatre", "400721");
	checkoutInfoPage.clickContinueButton();
	double productPriceSum = overviewPage.addIndividualItemPrice();
	double itemTotal = overviewPage.getItemTotal();
	Assert.assertEquals(productPriceSum, itemTotal);
    }
    
    @Test(priority=1)
    public void verifyFinishButtonNavigationTest() {
	homePage.checkCartForProducts();
	cartPage= homePage.openTheCart();
	checkoutInfoPage = cartPage.clickCheckoutButton();
	checkoutInfoPage.enterCheckoutDetails("Rahul", "Mhatre", "400721");
	checkoutInfoPage.clickContinueButton();
	overviewPage.clickFinish();
	String msg = overviewPage.successMessage();
	Assert.assertEquals(msg, "Thank you for your order!");
    }
    
    

}
