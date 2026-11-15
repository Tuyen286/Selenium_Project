package com.qa.framework.pages;

import com.qa.framework.driver.DriverManager;
import com.qa.framework.utils.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FramePage extends BasePage {
    private static final String urlIFrame = ConfigManager.getBaseUrl() + "/iframe";
    private static final String urlNestedFrame = ConfigManager.getBaseUrl() + "/nested_frames";
    private final By iFrameLocator = By.id("mce_0_ifr");
    private final By frameTop = By.name("frame-top");
    private final By frameLeft = By.name("frame-left");
    private final By frameMiddle = By.name("frame-middle");
    private final By frameRight = By.name("frame-right");
    private final By frameBottom = By.name("frame-bottom");
    private final By textArea = By.id("tinymce");
    private final By paragraph = By.xpath("//body/p");
    private final By bodyContent = By.tagName("body");

    public FramePage openIFramePage(){
        DriverManager.openUrl(urlIFrame);
        return this;
    }

    public void openNestedFramesPage(){
        DriverManager.openUrl(urlNestedFrame);
    }

    public void switchToFrame(By... frameLocators){
        for (By frame : frameLocators) {
            WebElement frameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(frame));
            driver.switchTo().frame(frameElement);
        }
    }

    public void switchToDefaultFrame(){
        driver.switchTo().defaultContent();
    }

    public FramePage enterTextEditor(String text){
        switchToFrame(iFrameLocator);
        click(textArea);
        enterText(textArea, text);
        switchToDefaultFrame();
        return this;
    }

    public String getEditorText() {
        driver.switchTo().frame(driver.findElement(iFrameLocator));
        String text = getText(paragraph);
        driver.switchTo().defaultContent();
        return text;
    }

    public String getLeftFrameText() {
        switchToFrame(frameTop, frameLeft);
        String text = getText(bodyContent);
        switchToDefaultFrame();
        return text.trim();
    }

    public String getMiddleFrameText() {
        switchToFrame(frameTop, frameMiddle);
        String text = getText(bodyContent);
        switchToDefaultFrame();
        return text.trim();
    }

    public String getBottomFrameText() {
        switchToFrame(frameBottom);
        String text = getText(bodyContent);
        switchToDefaultFrame();
        return text.trim();
    }

}
