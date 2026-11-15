# 🚀 Test Automation Framework (UI & API)

An automated testing framework integrating both **UI Testing (Selenium WebDriver)** and **API Testing (RestAssured)**, built with Java and TestNG. This framework follows a layered architecture (Page Object Model for UI & Service Layer for API) to maximize maintainability, code reusability, and seamless integration into CI/CD pipelines.

---

## 🛠️ Tech Stack & Libraries
* **Language:** Java (JDK 26)
* **UI Automation:** Selenium WebDriver
* **API Automation:** RestAssured
* **Testing Framework:** TestNG (Listeners, Retry Mechanism)
* **Assertions:** TestNG Assertions & AssertJ
* **Reporting & Logging:** Allure Report, Log4j2
* **Build Tool:** Maven

---

## 📂 Project Architecture
```text
Selenium_Project
├── .allure
├── .idea
├── .mvn
├── allure-results
├── screenshots
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.qa
│   │   │       └── framework
│   │   │           ├── api          # BaseApiService, UserService (API Service Layer)
│   │   │           ├── driver       # DriverManager (WebDriver initialization & lifecycle)
│   │   │           ├── pages        # UI Page Objects (BasePage, LoginPage, AlertPage...)
│   │   │           ├── utils        # Reusable utility classes (ConfigManager, RetryAnalyzer, ScreenshotUtils)
│   │   │           └── listeners    # TestListener & RetryTransformer (Screenshots on failure, retry mechanism)
│   │   └── resources
│   │       └── log4j2.xml           # Log4j2 configuration
│   └── test
│       ├── java
│       │   └── com.qa.tests
│       │       ├── api              # API test suites (UserApiTest)
│       │       ├── base             # BaseTest (Common UI setup/teardown)
│       │       └── ui               # UI test suites (LoginTest, AlertTest...)
│       └── resources
│           ├── data                 # Test data files (test.txt...)
│           └── config.properties    # Environment, URL, and browser configurations
├── target
├── .gitignore
├── pom.xml                          # Maven dependencies & plugins
└── testng.xml                       # TestNG suite configuration
```
---
## ⚙️ Key Features
* **Hybrid Architecture:** Independently yet cohesively manages both UI and API test flows within a single repository.
* **Page Object Model (POM) & Service Layer:** Decouples element locators (pages) and API endpoints (api) from test scripts (tests).
* **Smart Test Execution:** Integrates TestListener to automatically capture screenshots upon UI test failures (screenshots/) and RetryTransformer to re-run flaky tests.
* **API Request Chaining & Negative Testing:** Supports dependent API workflows (extracting tokens/IDs from responses for subsequent requests) and comprehensive negative/edge-case validation (400, 404).
* **Comprehensive Reporting:** Generates interactive visual execution reports with Allure Report and step-by-step execution logs via Log4j2.

---

## 🚀 How to Run Tests

### 1. Clone the Repository
```bash
git clone [https://github.com/your-username/Selenium_Project.git](https://github.com/your-username/Selenium_Project.git)
cd Selenium_Project
```
### 2. Run the Entire Test Suite via Maven
```bash
mvn clean test
```
### 3. Run the Entire Test Suite via Maven and run parallel
```bash
mvn clean test -Dparallel=classes -DthreadCount=2
```
### 4. Run some test scripts that we want via Maven
```bash
mvn clean test "-DtestList=run-list.txt"
```
### 5. Run Specific Test Suites
* Run API Tests only:
```bash
mvn test -Dtest=UserApiTest
```
* Run via testng.xml suite:
```bash
mvn test -DsuiteXmlFile=testng.xml
```
### 6. Generate & View Allure Report
After test execution completes, run the following command to launch the Allure report in your browser:
```bash
allure serve allure-results
```