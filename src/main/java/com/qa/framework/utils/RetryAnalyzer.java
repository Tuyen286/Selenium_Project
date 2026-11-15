package com.qa.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 1; // Number of retries upon test failure (e.g., 1 time)
    private static final Logger log = LogManager.getLogger(RetryAnalyzer.class);

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            log.warn("⚠️ Test case [{}] FAILED. Preparing to retry (Retry) for the time: {}", result.getName(), retryCount);
            return true; // Returning true means that TestNG will re-run this test case.
        }
        return false; // Retry limit reached; accepting failure.
    }
}