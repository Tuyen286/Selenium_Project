package com.qa.framework.pages;

import com.qa.framework.driver.DriverManager;
import com.qa.framework.utils.ConfigManager;
import org.openqa.selenium.By;

public class StatusCodesPage extends BasePage {
    private final String url = ConfigManager.getBaseUrl() + "/status_codes";
    private final By contentPage = By.xpath("//div[@id='content']//p");
    private By statusCode (String code){
        return By.xpath("//a[text()='" + code + "']");
    }

    public StatusCodesPage open() {
        DriverManager.openUrl(url);
        return this;
    }

    public void clickStatusCode (String code) {
        click(statusCode(code));
    }

    public String getContentText() {
        return getText(contentPage);
    }
}
