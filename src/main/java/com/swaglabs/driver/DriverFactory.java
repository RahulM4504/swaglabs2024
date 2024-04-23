package com.swaglabs.driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    public WebDriver driver;
    public Properties prop;
    public OptionsManager op;
    public static String highlight;

    public WebDriver initDriver(Properties prop) {	
	String browserName = prop.getProperty("browsername").toLowerCase().trim();
	highlight = prop.getProperty("highlight");
	op = new OptionsManager(prop);
	if (browserName.equalsIgnoreCase("chrome")) {
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver(op.getChromeOptions());

	} else if (browserName.equalsIgnoreCase("firefox")) {
	    driver = new FirefoxDriver(op.getFirefoxOptions());

	} else if (browserName.equalsIgnoreCase("edge")) {
	    driver = new EdgeDriver(op.getEdgeOptions());

	} else {
	    System.out.println("please enter correct browser name");
	}
	driver.manage().deleteAllCookies();
	driver.manage().window().maximize();
	driver.get(prop.getProperty("url"));
	return driver;
    }

    public Properties initProperties() {
	try {
	    FileInputStream fis = new FileInputStream("./src/test/resources/config/config.properties");
	    prop = new Properties();
	    prop.load(fis);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return prop;
    }
}
