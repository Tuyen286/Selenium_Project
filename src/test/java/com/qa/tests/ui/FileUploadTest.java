package com.qa.tests.ui;

import com.qa.framework.pages.FileUploadPage;
import com.qa.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FileUploadTest extends BaseTest {

    @Test
    public void testFileUpload() {
        FileUploadPage uploadPage = new FileUploadPage();
        logger.info("Starting File Upload Test");

        String uploadedFileName = uploadPage.open()
                .uploadFileByName("test.txt")
                .getUploadedFileName();

        logger.info("Verify uploaded file name");
        Assert.assertEquals(uploadedFileName, "test.txt");
    }
}
