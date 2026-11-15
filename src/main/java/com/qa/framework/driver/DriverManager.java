package com.qa.framework.driver;

import com.qa.framework.utils.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverManager {
    // ThreadLocal to ensure thread safety during parallel execution
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Get the current thread-safe WebDriver instance.
     * @return WebDriver
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Initialize the WebDriver based on the specified browser type.
     * @param browser Name of the browser (e.g., chrome, firefox)
     */
    public static void initDriver(String browser) {
        if (getDriver() == null) {
            // if browser is null or empty, get from config.properties
            if (browser == null || browser.trim().isEmpty()) {
                browser = ConfigManager.getBrowser();
            }

            switch (browser.toLowerCase()) {
                case "firefox":
                    driver.set(new FirefoxDriver());
                    break;
                case "edge":
                    driver.set(new EdgeDriver());
                    break;
                case "chrome":
                default:
                    driver.set(new ChromeDriver());
                    break;
            }

            // Maximize cửa sổ trình duyệt
            getDriver().manage().window().maximize();

            // Add Implicit Wait from ConfigManager
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigManager.getImplicitWait()));
        }
    }

    /**
     * Overload: init WebDriver default (get browser from config.properties)
     */
    public static void initDriver() {
        initDriver(ConfigManager.getBrowser());
    }

    /**
     * open Url in config.properties
     */
    public static void openBaseUrl() {
        if (getDriver() != null) {
            getDriver().get(ConfigManager.getBaseUrl());
        } else {
            throw new RuntimeException("❌ Driver has not been initialized! Please call DriverManager.initDriver() before opening a URL.");
        }
    }

    /**
     * Open the url
     * @param url url that you want to direct
     */
    public static void openUrl(String url) {
        if (getDriver() != null && url != null) {
            getDriver().get(url);
        } else if (getDriver() == null) {
            throw new RuntimeException("❌ Driver has not been initialized! Please call DriverManager.initDriver() before opening a URL.");
        } else {
            throw new RuntimeException("❌ Cannot find the url");
        }
    }

    /**
     * Quit the browser and remove the driver instance from ThreadLocal.
     */
    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

}