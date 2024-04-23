package com.swaglabs.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.swaglabs.driver.DriverFactory;
import com.swaglabs.pageobjects.CartPage;
import com.swaglabs.pageobjects.CheckoutInfoPage;
import com.swaglabs.pageobjects.HomePage;
import com.swaglabs.pageobjects.LoginPage;
import com.swaglabs.pageobjects.OverviewPage;

public class BaseTest {
    DriverFactory factory;    
    WebDriver driver;
    protected Properties prop;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected CartPage cartPage;
    protected OverviewPage overviewPage;
    protected CheckoutInfoPage checkoutInfoPage;
    
   
    @BeforeTest
    public void beforeTest() {
	factory = new DriverFactory();
	prop = factory.initProperties();
	driver = factory.initDriver(prop);
	loginPage = new LoginPage(driver);
	homePage = new HomePage(driver);
	cartPage = new CartPage(driver);
	overviewPage = new OverviewPage(driver);
	checkoutInfoPage = new CheckoutInfoPage(driver);
	
    }
    
    @AfterTest
    public void tearDown() {
	driver.quit();
    }

}
