package com.qa.listeners;

import com.qa.framework.utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final Logger log = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        // Automatically assign the test method name to ThreadContext at the very start of a test case.
        String testName = result.getMethod().getMethodName();
        ThreadContext.put("testName", testName);
        log.info("=== Starting Test: {} ===", testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test Case PASSED: {}", result.getName());
        cleanup();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Capture detailed error information, including the stack trace, when a test case fails.
        log.error("Test Case FAILED: {}", result.getName(), result.getThrowable());

        // add screenshot on local
        ScreenshotUtils.captureScreenshot(result.getName());
        //add screenshot on Allure report
        ScreenshotUtils.attachScreenshotToAllure(result.getName());

        cleanup();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("Test Case SKIPPED: {}", result.getName());
        cleanup();
    }

    private void cleanup() {
        ThreadContext.clearMap();
    }
}