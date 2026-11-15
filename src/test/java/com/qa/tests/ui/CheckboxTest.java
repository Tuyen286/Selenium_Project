package com.qa.tests.ui;

import com.qa.framework.pages.CheckBoxPage;
import com.qa.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckboxTest extends BaseTest {

    @Test
    public void testSelectedCheckbox(){
        CheckBoxPage checkBoxPage = new CheckBoxPage();
        logger.info("Select checkbox 1");
        checkBoxPage.open()
                    .selectCheckbox(1);
        logger.info("Verify checkbox 1 is checked");
        Assert.assertTrue(checkBoxPage.isCheckboxSelected(1));
    }

    @Test
    public void testUnselectedCheckbox(){
        CheckBoxPage checkBoxPage = new CheckBoxPage();
        logger.info("Unselected checkbox 2");
        checkBoxPage.open()
                    .unSelectCheckbox(2);
        logger.info("Verify that checkbox 2 is unselected");
        Assert.assertFalse(checkBoxPage.isCheckboxSelected(2));
    }
}
