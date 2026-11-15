package com.qa.framework.pages;

import com.qa.framework.driver.DriverManager;
import com.qa.framework.utils.ConfigManager;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RadioButtonsPage extends BasePage {
    private static final String url = ConfigManager.getBaseUrl() + "/radio_buttons";
    private static final Logger log = LoggerFactory.getLogger(RadioButtonsPage.class);
    private final By content = By.id("result");

    private By favoriteColorOption(String option) {
        return By.id(option.toLowerCase());
    }
    private By favoriteSportOption(String option) {
        return By.xpath("//input[@value='" + option.toLowerCase() + "']");
    }

    public RadioButtonsPage open(){
        DriverManager.openUrl(url);
        return this;
    }

    public void selectOption(By locator, boolean isEnabled, boolean isSelected, String option){
        if (isEnabled) {
            if (!isSelected) {
                click(locator);
            } else {
                log.info("{} option has already been checked", option);
            }
        } else {
            log.info("{} option is disabled, cannot be selected!", option);
        }
    }

    public void selectFavoriteColorOption(String option) {
        selectOption(favoriteColorOption(option), isFavoriteColorEnabled(option), isFavoriteColorSelected(option), option);
    }

    public void selectFavoriteSportOption(String option) {
        selectOption(favoriteSportOption(option), isFavoriteSportEnabled(option), isFavoriteSportSelected(option), option);
    }

    public boolean isFavoriteSportSelected(String option) {
        return isSelected(favoriteSportOption(option));
    }

    public boolean isFavoriteColorSelected(String option) {
        return isSelected(favoriteColorOption(option));
    }

    public String getContent() {
        return getText(content);
    }

    public boolean isFavoriteSportEnabled(String option) {
        return isEnabled(favoriteSportOption(option));
    }

    public boolean isFavoriteColorEnabled(String option) {
        return isEnabled(favoriteColorOption(option));
    }
}
