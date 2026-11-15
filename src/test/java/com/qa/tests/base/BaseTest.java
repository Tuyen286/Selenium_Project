package com.qa.tests.base;

import com.qa.framework.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected final Logger logger = LogManager.getLogger(getClass());

    @BeforeMethod
    public void setUp(){
        DriverManager.initDriver();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        DriverManager.quitDriver();
    }
}