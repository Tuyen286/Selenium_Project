package com.qa.tests.ui;

import com.qa.framework.pages.LoginPage;
import com.qa.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage();
        logger.info("Enter username and password then click on Login button");
        loginPage.open()
                .enterUsername("tomsmith")
                .enterPassword("SuperSecretPassword!")
                .clickLoginButton();

        // Add verification/assertions here later
        logger.info("Verify message");
        String alertText = loginPage.getFlashMessageText();
        Assert.assertTrue(alertText.contains("You logged into a secure area!"),
                "Login failed! Flash message text was: " + alertText);
    }

    @Test
    public void testFailedLogin() {
        LoginPage loginPage = new LoginPage();
        logger.info("Enter username and wrong password then click on Login button");
        loginPage.open()
                .enterUsername("tomsmith")
                .enterPassword("SuperSecretPassword")
                .clickLoginButton();

        // Add verification/assertions here later -> need to check report so I make failed script
        logger.info("Verify failed message");
        String alertText = loginPage.getFlashMessageText();
        Assert.assertTrue(alertText.contains("You logged into a secure area!"),
                "Login failed! Flash message text was: " + alertText);
    }
}