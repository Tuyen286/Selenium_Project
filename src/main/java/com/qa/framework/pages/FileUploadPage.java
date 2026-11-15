package com.qa.framework.pages;

import com.qa.framework.driver.DriverManager;
import com.qa.framework.utils.ConfigManager;
import org.openqa.selenium.By;

import java.io.File;

public class FileUploadPage extends BasePage {
    private static final String url = ConfigManager.getBaseUrl() + "/upload";
    private final By fileInput = By.id("file-upload");
    private final By uploadButton = By.id("file-submit");
    private final By uploadedFilesText = By.id("uploaded-files");

    public FileUploadPage open() {
        DriverManager.openUrl(url);
        return this;
    }

    public FileUploadPage uploadFileByName(String fileName) {
        String filePath = new File("src/test/resources/data/" + fileName).getAbsolutePath();
        uploadFile(fileInput, filePath);
        click(uploadButton);
        return this;
    }

    public String getUploadedFileName() {
        return getText(uploadedFilesText);
    }
}
