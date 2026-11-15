package com.qa.tests.ui;

import com.qa.framework.pages.FramePage;
import com.qa.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FrameTest extends BaseTest {

    @Test
    public void testEnterTextIntoFrame(){
        FramePage framePage = new FramePage();
        String actualText = framePage.openIFramePage()
                .enterTextEditor("Test input text into different frame")
                .getEditorText();
        Assert.assertEquals(actualText, "Test input text into different frame");
    }

    @Test
    public void testNestedFramesContent() {
        FramePage framePage = new FramePage();
        framePage.openNestedFramesPage();

        logger.info("Verify text inside Left frame");
        Assert.assertEquals(framePage.getLeftFrameText(), "LEFT");

        logger.info("Verify text inside Middle frame");
        Assert.assertEquals(framePage.getMiddleFrameText(), "MIDDLE");

        logger.info("Verify text inside Bottom frame");
        Assert.assertEquals(framePage.getBottomFrameText(), "BOTTOM");
    }
}
