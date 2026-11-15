package com.qa.listeners;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;

public class TestFilterListener implements IMethodInterceptor {

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        // 1. Prioritize retrieving the test list file name from the Maven command-line parameter (-DtestList=...)
        String fileName = System.getProperty("testList");

        // 2. Fallback: If command-line property is not provided, check testng.xml suite parameters
        if (fileName == null || fileName.isEmpty()) {
            fileName = context.getCurrentXmlTest().getParameter("testList");
        }

        // 3. If no file is specified anywhere, default to an empty string to allow a full suite execution
        if (fileName == null || fileName.trim().isEmpty()) {
            System.out.println("No test filter file specified -> Running the entire test suite.");
            return methods;
        }

        List<String> allowedTests = new ArrayList<>();
        try {
            // Locate the configuration file dynamically within the classpath resources/data/ directory
            java.net.URL fileUrl = getClass().getClassLoader().getResource("data/" + fileName);
            if (fileUrl == null) {
                System.out.println("Target test list file [data/" + fileName + "] not found. Executing all tests.");
                return methods;
            }

            // Read and parse allowed test method names, ignoring empty lines and comments (#)
            java.nio.file.Path path = java.nio.file.Paths.get(fileUrl.toURI());
            allowedTests = java.nio.file.Files.readAllLines(path).stream()
                    .map(String::trim)
                    .filter(line -> !line.isEmpty() && !line.startsWith("#"))
                    .toList();

        } catch (Exception e) {
            System.out.println("Error reading the test list file [" + fileName + "]: " + e.getMessage());
            return methods;
        }

        // If the filter file is empty, run all tests
        if (allowedTests.isEmpty()) {
            return methods;
        }

        // Filter and retain only the test methods whose names match the allowed list
        List<IMethodInstance> result = new ArrayList<>();
        for (IMethodInstance method : methods) {
            String methodName = method.getMethod().getMethodName();
            if (allowedTests.contains(methodName)) {
                result.add(method);
            }
        }

        System.out.println("TestFilterListener filtering active with file: [" + fileName + "] — Total selected tests: " + result.size());
        return result;
    }
}