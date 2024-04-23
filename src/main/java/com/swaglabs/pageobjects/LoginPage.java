package com.swaglabs.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.swaglabs.util.ElementUtil;

public class LoginPage {
    private WebDriver driver;
    private ElementUtil elementUtil;
    private By username = By.cssSelector("#user-name");
    private By password = By.cssSelector("#password");
    private By loginButton = By.xpath("//input[@id='login-button']");
    
    public LoginPage(WebDriver driver) {
	this.driver=driver;
	elementUtil = new ElementUtil(driver);
    }
    
    public HomePage dologin(String uName, String pwd ) {	
	elementUtil.doSendKeys(username, uName);
	elementUtil.doSendKeys(password, pwd);
	elementUtil.doClick(loginButton);	
	return new HomePage(driver);
    }

}
