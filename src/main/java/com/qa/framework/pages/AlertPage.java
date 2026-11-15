package com.qa.framework.pages;

import com.qa.framework.driver.DriverManager;
import com.qa.framework.utils.ConfigManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AlertPage extends BasePage {
    private static final String url = ConfigManager.getBaseUrl() + "/javascript_alerts";
    private final By result = By.id("result");
    private By alertButton (String buttonName) {
        return By.xpath("//button[text()='" + buttonName + "']");
    }

    public AlertPage open(){
        DriverManager.openUrl(url);
        return this;
    }

    private Alert switchToAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public AlertPage clickAlertButton(String buttonName) {
        click(alertButton(buttonName));
        return this;
    }

    public void acceptAlert() {
        switchToAlert().accept();
    }

    public void dismissAlert() {
        switchToAlert().dismiss();
    }

    public AlertPage enterTextOnAlert(String text) {
        switchToAlert().sendKeys(text);
        return this;
    }

    public String getAlertText() {
        return switchToAlert().getText();
    }

    public String getResult() {
        return getText(result);
    }
}
