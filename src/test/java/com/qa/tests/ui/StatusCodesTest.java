package com.qa.tests.ui;

import com.qa.framework.pages.StatusCodesPage;
import com.qa.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StatusCodesTest extends BaseTest {

    @Test
    public void testStatusCodeLink() {
        StatusCodesPage statusCodesPage = new StatusCodesPage();
        logger.info("Click on '200' status link ");
        statusCodesPage.open()
                        .clickStatusCode("200");
        logger.info("Verify page of 200 status code");
        Assert.assertTrue(statusCodesPage.getContentText().contains("200"), "Content displays wrong status code for 200");
        logger.info("Navigate back to previous page");
        statusCodesPage.backToPreviousPage();
        logger.info("Click on '404' status link ");
        statusCodesPage.clickStatusCode("404");
        logger.info("Verify page of 404 status code");
        Assert.assertTrue(statusCodesPage.getContentText().contains("404"), "Content displays wrong status code for 404");
    }
}
