package com.qa.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties = new Properties();
    private static final Logger log = LogManager.getLogger(ConfigManager.class);

    // This static block will execute automatically the first time the class is accessed, in order to load the configuration file.
    static {
        try {
            String filePath = "src/test/resources/config.properties";
            FileInputStream input = new FileInputStream(filePath);
            properties.load(input);
            input.close();
        } catch (IOException e) {
            log.error("❌ Unable to find or read the config.properties!", e);
            throw new RuntimeException("❌ Unable to find or read the config.properties file at the path: src/test/resources/config.properties!");
        }
    }

    // get property
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static String getBaseUrl() {
        return getProperty("baseUrl");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicitWait"));
    }

    public static String getApiUrl() {
        return getProperty("apiUrl");
    }
}
