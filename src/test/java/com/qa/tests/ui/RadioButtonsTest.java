package com.qa.tests.ui;

import com.qa.framework.pages.RadioButtonsPage;
import com.qa.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RadioButtonsTest extends BaseTest {

    @Test
    public void testSelectFavoriteColor() {
        RadioButtonsPage radioButtonsPage = new RadioButtonsPage();
        logger.info("Click on Green radio button");
        radioButtonsPage.open()
                        .selectFavoriteColorOption("Green");
        logger.info("Verify Green radio button is checked and content includes green");
        Assert.assertTrue(radioButtonsPage.isFavoriteColorSelected("Green"), "'Green' option is not selected");
        Assert.assertEquals(radioButtonsPage.getContent(), "Color: green, Sport: none");
    }

    @Test
    public void testSelectFavoriteSport() {
        RadioButtonsPage radioButtonsPage = new RadioButtonsPage();
        radioButtonsPage.open();
        logger.info("Check 'Hokey' option is disabled");
        Assert.assertFalse(radioButtonsPage.isFavoriteSportEnabled("Hockey"), "'Hockey' option should be disabled!");
        logger.info("Select 'Football' option");
        radioButtonsPage.selectFavoriteSportOption("Football");
        Assert.assertTrue(radioButtonsPage.isFavoriteSportSelected("Football"), "'Football' option is not selected");
        Assert.assertEquals(radioButtonsPage.getContent(), "Color: blue, Sport: football");
        logger.info("Select 'Tennis' option");
        radioButtonsPage.selectFavoriteSportOption("Tennis");
        Assert.assertTrue(radioButtonsPage.isFavoriteSportSelected("Tennis"), "'Tennis' option is not selected");
        Assert.assertEquals(radioButtonsPage.getContent(), "Color: blue, Sport: tennis");
    }

}
