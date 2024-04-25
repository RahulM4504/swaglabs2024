package com.swaglabs.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    public WebDriver driver;
    public Properties prop;
    public OptionsManager op;
    public static String highlight;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    public WebDriver initDriver(Properties prop) {
	String browserName = prop.getProperty("browsername").toLowerCase().trim();
	highlight = prop.getProperty("highlight");
	op = new OptionsManager(prop);
	if (browserName.equalsIgnoreCase("chrome")) {
	    WebDriverManager.chromedriver().setup();

	    tlDriver.set(new ChromeDriver(op.getChromeOptions()));

	} else if (browserName.equalsIgnoreCase("firefox")) {

	    tlDriver.set(new FirefoxDriver(op.getFirefoxOptions()));
	} else if (browserName.equalsIgnoreCase("edge")) {

	    tlDriver.set(new EdgeDriver(op.getEdgeOptions()));
	} else {
	    System.out.println("please enter correct browser name");
	}
	getDriver().manage().deleteAllCookies();
	getDriver().manage().window().maximize();
	getDriver().get(prop.getProperty("url"));
	return getDriver();
    }

    public synchronized static WebDriver getDriver() {
	return tlDriver.get();
    }

    public Properties initProperties() {
	FileInputStream fis = null;
	prop = new Properties();
	String envName = System.getProperty("env").toLowerCase().trim();
	System.out.println("Running Tests on " + envName + " env");
	try {
	    if (envName == null) {
		System.out.println("Environment not specified by user hence running tests on QA env");
		fis = new FileInputStream("./src/main/resources/config/qa.config.properties");
	    } else {
		switch (envName) {
		case "qa":
		    fis = new FileInputStream("./src/main/resources/config/qa.config.properties");
		    break;
		case "dev":
		    fis = new FileInputStream("./src/main/resources/config/dev.config.properties");
		    break;
		case "stage":
		    fis = new FileInputStream("./src/main/resources/config/stage.config.properties");
		    break;
		case "prod":
		    fis = new FileInputStream("./src/main/resources/config/prod.config.properties");
		    break;
		default:
		    System.out.println("Wrong env specified. Please specify correct environment to run the tests");
		    break;
		}
	    }
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	try {
	    prop.load(fis);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return prop;
    }

    public static String getScreenshot() {
	File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
	String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
	File destination = new File(path);
	try {
	    FileHandler.copy(srcFile, destination);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return path;
    }

}
