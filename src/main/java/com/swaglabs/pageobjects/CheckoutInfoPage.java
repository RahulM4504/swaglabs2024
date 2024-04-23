package com.swaglabs.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.swaglabs.util.ElementUtil;

public class CheckoutInfoPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    private By checkoutFirstName = By.xpath("//input[@name='firstName']");
    private By checkoutLastName = By.xpath("//input[@name='lastName']");
    private By zipCode = By.xpath("//input[@name='postalCode']");
    private By continueButton = By.xpath("//input[@name='continue']");

    public CheckoutInfoPage(WebDriver driver) {
	this.driver = driver;
	elementUtil = new ElementUtil(driver);
    }

    public void enterCheckoutDetails(String fname, String lname, String zipcode) {
	elementUtil.doSendKeys(checkoutFirstName, fname);
	elementUtil.doSendKeys(checkoutLastName, lname);
	elementUtil.doSendKeys(zipCode, zipcode);
    }

    public OverviewPage clickContinueButton() {
	elementUtil.doClick(continueButton);
	return new OverviewPage(driver);
    }

}
