package com.qa.framework.driver;

import com.qa.framework.utils.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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

            // Automatic detection of CI/CD environments (GitHub Actions) or the -Dheadless=true parameter
            boolean isHeadless = "true".equalsIgnoreCase(System.getenv("CI"))
                    || "true".equalsIgnoreCase(System.getProperty("headless"));

            switch (browser.toLowerCase()) {
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (isHeadless) {
                        firefoxOptions.addArguments("-headless");
                        firefoxOptions.addArguments("--width=1920");
                        firefoxOptions.addArguments("--height=1080");
                    }
                    driver.set(new FirefoxDriver(firefoxOptions));
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (isHeadless) {
                        edgeOptions.addArguments("--headless=new");
                        edgeOptions.addArguments("--no-sandbox");
                        edgeOptions.addArguments("--disable-dev-shm-usage");
                        edgeOptions.addArguments("--window-size=1920,1080");
                    }
                    driver.set(new EdgeDriver(edgeOptions));
                    break;

                case "chrome":
                default:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (isHeadless) {
                        chromeOptions.addArguments("--headless=new");
                        chromeOptions.addArguments("--no-sandbox");
                        chromeOptions.addArguments("--disable-dev-shm-usage");
                        chromeOptions.addArguments("--window-size=1920,1080");
                    }
                    driver.set(new ChromeDriver(chromeOptions));
                    break;
            }

            // Chỉ maximize cửa sổ khi chạy có giao diện (tránh lỗi ở một số bản Linux headless)
            if (!isHeadless) {
                getDriver().manage().window().maximize();
            }

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