package com.swaglabs.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.swaglabs.util.ElementUtil;

public class OverviewPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    private By itemPriceList = By.cssSelector(".inventory_item_price");
    private By itemTotal = By.cssSelector(".summary_subtotal_label");
    private By tax = By.cssSelector(".summary_tax_label");
    private By total = By.cssSelector(".summary_total_label");
    private By finishButton = By.xpath("//button[@name='finish']");
    private By successMessage = By.cssSelector("h2.complete-header");

    public OverviewPage(WebDriver driver) {
	this.driver = driver;
	elementUtil = new ElementUtil(driver);
    }

    public double addIndividualItemPrice() {
	double sum = 0;
	List<WebElement> price = elementUtil.getElements(itemPriceList);
	for (int i = 0; i < price.size(); i++) {
	    String clearSymbol = price.get(i).getText().replace("$", "");
	    double netPrice = Double.parseDouble(clearSymbol);
	    sum = sum + netPrice;
	}
	System.out.println(sum);
	return sum;
    }

    public double getItemTotal() {
	String[] stringPrice = elementUtil.getElement(itemTotal).getText().split("\\$");
	String numPrice = stringPrice[1];
	double itemtotal = Double.parseDouble(numPrice);
	return itemtotal;
    }

    public double getTax() {
	String[] stringTax = elementUtil.getElement(tax).getText().split("\\$");
	String numTax = stringTax[1];
	double taxAmount = Double.parseDouble(numTax);
	return taxAmount;
    }

    public double getTotalAfterTax() {
	String[] stringGrandTotal = elementUtil.getElement(total).getText().split("\\$");
	String numGrandTotal = stringGrandTotal[1];
	double totalAfterTax = Double.parseDouble(numGrandTotal);
	return totalAfterTax;
    }

    public void clickFinish() {
	elementUtil.getElement(finishButton).click();
    }

    public String successMessage() {
	return elementUtil.doElementGetText(successMessage);
    }

}
