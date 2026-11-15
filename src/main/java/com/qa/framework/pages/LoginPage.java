package com.qa.framework.pages;

import com.qa.framework.driver.DriverManager;
import com.qa.framework.utils.ConfigManager;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private static final String url = ConfigManager.getBaseUrl() + "/login";
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By flashMessage = By.id("flash");

    public LoginPage open(){
        DriverManager.openUrl(url);
        return this;
    }

    /**
     * Enter username into the username field.
     *
     * @param username username string
     * @return LoginPage instance for method chaining
     */
    public LoginPage enterUsername(String username) {
        enterText(usernameField, username);
        return this;
    }

    /**
     * Enter password into the password field.
     *
     * @param password password string
     * @return LoginPage instance for method chaining
     */
    public LoginPage enterPassword(String password) {
        enterText(passwordField, password);
        return this;
    }

    /**
     * Click on the login button.
     */
    public void clickLoginButton() {
        click(loginButton);
    }

    /**
     * Get the success/failure alert text.
     * @return Alert message text
     */
    public String getFlashMessageText() {
        return getText(flashMessage);
    }
}