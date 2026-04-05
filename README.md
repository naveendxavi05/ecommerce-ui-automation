# E-Commerce UI Automation Framework

![Java](https://img.shields.io/badge/Java-21-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.34.0-green)
![TestNG](https://img.shields.io/badge/TestNG-7.10.2-blue)
![Maven](https://img.shields.io/badge/Maven-3.x-red)
![ExtentReports](https://img.shields.io/badge/ExtentReports-5.1.1-purple)

A production-grade Selenium WebDriver automation framework built with Java and TestNG, targeting the [Sauce Demo](https://www.saucedemo.com) e-commerce application.

---

## Tech Stack

| Tool | Purpose |
|------|---------|
| Java 21 (Amazon Corretto) | Core language |
| Selenium WebDriver 4.34.0 | Browser automation |
| TestNG 7.10.2 | Test execution & parallel support |
| Maven | Build & dependency management |
| Extent Reports 5.1.1 | HTML test reports |
| Log4j2 | Production-grade logging |
| JavaFaker | Dynamic test data generation |
| Apache POI | Excel data-driven testing |
| MySQL + JDBC | Database validation |
| RetryAnalyzer | Automatic test retry on failure |

---

## Project Structure
```
ecommerce-automation/
├── src/
│   ├── main/java/com/naveen/
│   │   ├── base/          # DriverFactory, BaseTest
│   │   ├── pages/         # Page Object classes
│   │   ├── utils/         # ConfigReader, WaitUtils, ExtentReportManager, ScreenshotUtils, RetryAnalyzer
│   │   ├── db/            # JDBC database utilities
│   │   └── models/        # POJO data models
│   ├── main/resources/
│   │   ├── config.properties.template
│   │   └── log4j2.xml
│   └── test/
│       ├── java/com/naveen/tests/
│       │   ├── LoginTest.java
│       │   ├── CheckoutTest.java
│       │   └── SmokeTest.java
│       └── resources/
│           └── testng.xml
├── test-output/           # Extent Reports (generated)
├── logs/                  # Log4j2 rolling logs (generated)
└── pom.xml
```

---

## How to Run

**Prerequisites:**
- Java 21
- Maven 3.x
- Chrome browser

**Setup:**
```bash
git clone https://github.com/naveen-d-xavi/ecommerce-automation.git
cd ecommerce-automation
cp src/main/resources/config.properties.template src/main/resources/config.properties
# Edit config.properties with your values
```

**Run full suite:**
```bash
mvn test
```

**Run smoke tests only:**
```bash
mvn test -Dgroups=smoke
```

**Run regression suite:**
```bash
mvn test -Dgroups=regression
```

---

## Test Coverage

| Test Class | Tests | Coverage |
|-----------|-------|---------|
| LoginTest | 4 | Successful login, locked user, invalid password, empty fields |
| CheckoutTest | 4 | Full checkout, cart badge, cart items, empty form validation |
| SmokeTest | 2 | Page title, end-to-end checkout flow |

**Total: 10 tests**

---

## Key Features

- **Page Object Model** — clean separation of test logic and page interactions
- **ThreadLocal WebDriver** — thread-safe driver management for parallel execution
- **Extent Reports** — dark-themed HTML report with pass/fail status
- **Screenshot on failure** — auto-captured and embedded in report
- **RetryAnalyzer** — flaky tests automatically retry once
- **Log4j2** — console + rolling file logging, no System.out.println
- **ConfigReader** — zero hardcoded values, all config driven
- **Test Groups** — run smoke or regression subsets independently

---

## Report

After running, open `test-output/ExtentReport.html` in your browser.

---

## Author

**Naveen D Xavi** — QA Automation Engineer  
[LinkedIn](https://linkedin.com/in/naveen-d-xavi) | [GitHub](https://github.com/naveen-d-xavi)