package com.swaglabs.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.swaglabs.util.ElementUtil;

public class HomePage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    public HomePage(WebDriver driver) {
	this.driver = driver;
	elementUtil = new ElementUtil(driver);
    }

    private By pageHeadingMain = By.cssSelector(".app_logo");
    private By pageHeadingSecondary = By.cssSelector(".title");
    private By burgerMenuButton = By.cssSelector("#react-burger-menu-btn");
    private By productsList = By.cssSelector(".inventory_item");
    private By addToCartButtonList = By.xpath("//button[contains(text(),'Add to cart')]");
    private By addToCartButton = By.xpath("(//button[contains(text(),'Add to cart')])[1]");
    private By goToCart = By.cssSelector(".shopping_cart_link");
    private By cartBatch = By.cssSelector(".shopping_cart_badge");
    private By productTitle = By.cssSelector(".inventory_item_name");

    public boolean mainHeadingDisplayed() {
	return elementUtil.doElementIsDisplayed(pageHeadingMain);
    }

    public boolean secondaryHeadingDisplayed() {
	return elementUtil.doElementIsDisplayed(pageHeadingSecondary);
    }

    public boolean burgerMenuButtonDisplayed() {
	return elementUtil.doElementIsDisplayed(burgerMenuButton);
    }

    public int getProductsCount() {
	List<WebElement> products = elementUtil.getElements(productsList);
	return products.size();
    }

    public int getAddToCartButtonCount() {
	List<WebElement> AddtoCartButton = elementUtil.getElements(addToCartButtonList);
	return AddtoCartButton.size();
    }

    public void clickAddToCartButton() {
	elementUtil.doClick(addToCartButton);
    }

    public CartPage openTheCart() {
	elementUtil.doClick(goToCart);
	return new CartPage(driver);
    }

    public void checkCartForProducts() {
	try {
	    // if cart is not empty check cart should have at least 2 products
	    elementUtil.waitForElementsVisible(cartBatch, 2);
	    int cartBatchCount = Integer.parseInt(elementUtil.getElement(goToCart).getText());
	    System.out.println("current cart products : " + cartBatchCount);
	    if (cartBatchCount < 2) {
		elementUtil.doClick(addToCartButton);
	    }
	} catch (Exception e) {
	    // if cart is empty run catch and add first 2 products from product page.
	    System.out.println("Cart is empty. Adding 2 Products");
	    for (int i = 0; i < 2; i++) {
		elementUtil.doClick(addToCartButton);
	    }
	}
    }
    
    public void checkProductPrice(String prodTitle) {
	WebElement prodContainer = elementUtil.getElement(productsList);
	WebElement title = prodContainer.findElement(By.xpath("//div[contains(text(),'"+prodTitle+"')]"));
	
    }

}
