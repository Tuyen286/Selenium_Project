package com.qa.tests.ui;

import com.qa.framework.pages.DynamicLoadingPage;
import com.qa.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DynamicLoadingTest extends BaseTest {

    @Test
    public void testDynamicLoadingElement() {
        DynamicLoadingPage dynamicLoadingPage = new DynamicLoadingPage();
        logger.info("Click on Start button");
        String actualText = dynamicLoadingPage.open()
                .clickStart()
                .waitUntilLoadingDisappears()
                .getLoadedText();
        logger.info("Verify the text after loading");
        Assert.assertEquals(actualText, "Hello World!");
    }
}
