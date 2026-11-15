package com.qa.tests.ui;

import com.qa.framework.pages.DropdownListPage;
import com.qa.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class DropdownListTest extends BaseTest {

    @Test
    public void testDefaultSelectedDropdownOption(){
        DropdownListPage dropdownListPage = new DropdownListPage();
        dropdownListPage.open();
        logger.info("Verify the default selected option on dropdown list");
        Assert.assertEquals(dropdownListPage.getSelectedOption(), "Please select an option");
    }

    @Test
    public void testSelectDropdownOption(){
        DropdownListPage dropdownListPage = new DropdownListPage();
        logger.info("Selected Option 1 on dropdown list");
        dropdownListPage.open()
                .selectOption("Option 1");
        logger.info("Verify that Option 1 displays on dropdown list");
        Assert.assertEquals(dropdownListPage.getSelectedOption(), "Option 1");
    }

    @Test
    public void testVerifyAllDropdownOptions() {
        DropdownListPage dropdownListPage = new DropdownListPage();
        dropdownListPage.open();
        List<String> expectedOptions = Arrays.asList("Please select an option", "Option 1", "Option 2");
        List<String> actualOptions = dropdownListPage.getAllOptions();
        logger.info("Verify all options on dropdown list");
        Assert.assertEquals(actualOptions, expectedOptions);
    }

    @Test(expectedExceptions = org.openqa.selenium.NoSuchElementException.class)
    public void testSelectNonExistentOption() {
        DropdownListPage dropdownListPage = new DropdownListPage();
        logger.info("Check when selecting non existed option on dropdown list");
        dropdownListPage.open()
                .selectOption("Option 3");
    }
}
