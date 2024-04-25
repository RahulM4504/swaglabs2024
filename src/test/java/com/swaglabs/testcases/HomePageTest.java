package com.swaglabs.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.swaglabs.base.BaseTest;

public class HomePageTest extends BaseTest {

    @BeforeClass
    public void homePageSetup() {
	homePage = loginPage.dologin(prop.getProperty("username"),prop.getProperty("password"));
    }

    @Test
    public void VerifyMainHeadingISDisplayed() {
	Assert.assertTrue(homePage.mainHeadingDisplayed());
    }

    @Test
    public void VerifySubHeadingISDisplayed() {
	Assert.assertTrue(homePage.secondaryHeadingDisplayed());
    }

    @Test
    public void VerifyThreeBarMenuISDisplayed() {
	Assert.assertTrue(homePage.burgerMenuButtonDisplayed());
    }

    @Test
    public void VerifyAddToCartButtonIsDisplayedForEachProduct() {
	int prodCount = homePage.getProductsCount();
	int buttonCount = homePage.getAddToCartButtonCount();
	Assert.assertEquals(prodCount, buttonCount);
    }

    @Test
    public void VerifyCartLinkNavigation() {
	homePage.openTheCart();
	String heading = cartPage.getCartPageSeconderyHeading();
	Assert.assertEquals(heading, "Your Cart 123444444");
    }

}
