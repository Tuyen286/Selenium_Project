package com.qa.framework.pages;

import com.qa.framework.driver.DriverManager;
import com.qa.framework.utils.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class DropdownListPage extends BasePage {
    private static final String url = ConfigManager.getBaseUrl() + "/dropdown";
    private final By dropdownList = By.id("dropdown");

    private Select getDropdownSelect() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownList));
        return new Select(element);
    }

    public DropdownListPage open(){
        DriverManager.openUrl(url);
        return this;
    }

    public void selectOption (String option){
        getDropdownSelect().selectByVisibleText(option);
    }

    public String getSelectedOption(){
        return getDropdownSelect().getFirstSelectedOption().getText();
    }

    public List<String> getAllOptions() {
        List<WebElement> options = getDropdownSelect().getOptions();
        List<String> optionsText = new ArrayList<>();

        for(WebElement option : options) {
            optionsText.add(option.getText());
        }

        return  optionsText;
    }
}
