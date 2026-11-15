package com.qa.framework.pages;

import com.qa.framework.driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final int TIMEOUT = 10;// Default timeout duration in seconds

    /**
     * Constructor for BasePage to initialize driver and explicit wait.
     */
    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    /**
     * Wait for an element to be clickable and click on it.
     *
     * @param locator By locator of the element
     */
    protected void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    /**
     * Wait for an element to be visible, clear existing text, and type new text.
     *
     * @param locator By locator of the element
     * @param text Text to type
     */
    protected void enterText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Wait for an element to be visible and retrieve its text content.
     *
     * @param locator By locator of the element
     * @return Text of the element
     */
    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    /**
     * Check if an element is displayed on the page within the timeout.
     *
     * @param locator By locator of the element
     * @return true if displayed, false otherwise
     */
    protected boolean isElementDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait element until it's invisible
     * @param locator By locator of the element
     */
    protected void waitForElementInvisible(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Check if an element is selected (check for checkbox, radio, dropdown)
     * @param locator By locator of the element
     * @return  true if it shows on DOM
     */
    protected boolean isSelected(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return element.isSelected();
    }

    /**
     * Check if an element is enabled
     * @param locator By locator of the element
     * @return true if it is enabled
     */
    protected boolean isEnabled(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return element.isEnabled();
    }

    /**
     * Navigate back to the previous page
     */
    public void backToPreviousPage() {
        driver.navigate().back();
    }

    /**
     * Wait for file input element to be present and send absolute file path
     * @param locator By locator of the file input
     * @param filePath Absolute path of the file to upload
     */
    protected void uploadFile(By locator, String filePath){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.sendKeys(filePath);
    }
}