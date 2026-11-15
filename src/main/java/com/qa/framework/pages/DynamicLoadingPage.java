package com.qa.framework.pages;

import com.qa.framework.driver.DriverManager;
import com.qa.framework.utils.ConfigManager;
import org.openqa.selenium.By;

public class DynamicLoadingPage extends BasePage {
    private static final String url = ConfigManager.getBaseUrl() + "/dynamic_loading/1";
    private final By startButton = By.cssSelector("#start button");
    private final By loadingSpinner = By.id("loading");
    private final By finishText = By.cssSelector("#finish h4");

    public DynamicLoadingPage open(){
        DriverManager.openUrl(url);
        return this;
    }

    public DynamicLoadingPage clickStart() {
        click(startButton);
        return this;
    }

    public DynamicLoadingPage waitUntilLoadingDisappears() {
        waitForElementInvisible(loadingSpinner);
        return this;
    }

    public String getLoadedText() {
        return getText(finishText);
    }

}
