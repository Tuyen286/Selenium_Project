package com.qa.tests.ui;

import com.qa.framework.pages.AlertPage;
import com.qa.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertTest extends BaseTest {

    @Test
    public void testAcceptAlert() {
        AlertPage alertPage = new AlertPage();
        logger.info("Accept the alert");
        alertPage.open()
                .clickAlertButton("Click for JS Alert")
                .acceptAlert();
        Assert.assertEquals(alertPage.getResult(), "You successfully clicked an alert");
    }

    @Test
    public void testGetAlertText() {
        AlertPage alertPage = new AlertPage();
        logger.info("Get text on Alert popup");
        String text = alertPage.open()
                .clickAlertButton("Click for JS Alert")
                .getAlertText();
        Assert.assertEquals(text, "I am a JS Alert");
    }

    @Test
    public void testDismissAlert() {
        AlertPage alertPage = new AlertPage();
        logger.info("Dismiss Alert popup");
        alertPage.open()
                .clickAlertButton("Click for JS Confirm")
                .dismissAlert();
        Assert.assertEquals(alertPage.getResult(), "You clicked: Cancel");
    }

    @Test
    public void testEnterTextAndAcceptAlert() {
        AlertPage alertPage = new AlertPage();
        logger.info("Enter text on Alert popup and click on OK");
        alertPage.open()
                .clickAlertButton("Click for JS Prompt")
                .enterTextOnAlert("text")
                .acceptAlert();
        Assert.assertEquals(alertPage.getResult(), "You entered: text");
    }

    @Test
    public void testEnterTextAndDismissAlert() {
        AlertPage alertPage = new AlertPage();
        logger.info("Enter text on Alert popup and click on Cancel");
        alertPage.open()
                .clickAlertButton("Click for JS Prompt")
                .enterTextOnAlert("text")
                .dismissAlert();
        Assert.assertEquals(alertPage.getResult(), "You entered: null");
    }
}
