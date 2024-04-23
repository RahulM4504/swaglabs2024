package com.swaglabs.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.swaglabs.util.ElementUtil;

public class CartPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    public CartPage(WebDriver driver) {
	this.driver = driver;
	elementUtil = new ElementUtil(driver);
    }

    private By cartItemList = By.cssSelector(".cart_item");
    private By removeButtonList = By.xpath("//button[contains(text(),'Remove')]");
    private By checkoutButton = By.xpath("//button[contains(text(),'Checkout')]");
    private By yourCartHeading = By.cssSelector(".header_secondary_container");

    public int getCartItemCount() {
	List<WebElement> cartItem = elementUtil.getElements(cartItemList);
	return cartItem.size();
    }

    public int getRemoveCount() {
	List<WebElement> cartItem = elementUtil.getElements(removeButtonList);
	return cartItem.size();
    }

    public CheckoutInfoPage clickCheckoutButton() {
	elementUtil.getElement(checkoutButton).click();
	return new CheckoutInfoPage(driver);
    }

    public String getCartPageSeconderyHeading() {
	return elementUtil.getElement(yourCartHeading).getText();
    }

}
