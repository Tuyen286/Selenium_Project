package com.qa.framework.utils;

import com.qa.framework.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String captureScreenshot(String testName) {
        String fileName;

        // Check whether the code is running in a CI/CD (pipeline) environment
        // Common CI/CD systems automatically enable the "CI" environment variable
        boolean isCI = System.getenv("CI") != null || System.getenv("JENKINS_URL") != null;

        if (isCI) {
            // ON THE PIPELINE: Use a timestamp to generate a unique file for each build,
            // ensuring that the artifacts from every build retain their own independent history.
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            fileName = testName + "_" + timestamp + ".png";
        } else {
            // ON LOCAL: Use a single, fixed filename to allow for automatic overwriting,
            // preventing clutter and saving disk space on your personal machine.
            fileName = testName + ".png";
        }

        String directory = "screenshots/";
        File sourceFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);

        try {
            Path destinationPath = Paths.get(directory + fileName);
            Files.createDirectories(destinationPath.getParent());
            Files.copy(sourceFile.toPath(), destinationPath);
            System.out.println("Saved screenshot to: " + destinationPath.toAbsolutePath());
            return destinationPath.toString();
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            return null;
        }
    }

    // Add screenshot to Allure 3
    public static void attachScreenshotToAllure(String testName) {
        if (DriverManager.getDriver() != null) {
            try {
                byte[] screenshotBytes = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
                Allure.attachment(testName, new ByteArrayInputStream(screenshotBytes));
            } catch (Exception e) {
                System.out.println("Lỗi khi chụp màn hình đính kèm Allure: " + e.getMessage());
            }
        }
    }
}