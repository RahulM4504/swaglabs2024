package com.swaglabs.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.swaglabs.base.BaseTest;

public class LoginPageTest extends BaseTest {

    @Test
    public void doLoginWithSwag() {
	loginPage.dologin(prop.getProperty("username"),prop.getProperty("password"));
	Assert.assertTrue(homePage.burgerMenuButtonDisplayed());
    }

}
