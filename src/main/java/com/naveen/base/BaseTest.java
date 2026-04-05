package com.naveen.base;

import com.naveen.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {

    private static final Logger log = LogManager.getLogger(BaseTest.class);
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {
        String browser = ConfigReader.getProperty("browser");
        log.info("Starting test: {}", method.getName());
        log.info("Launching browser: {}", browser);

        DriverFactory.initDriver(browser);
        driver = DriverFactory.getDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicit.wait")))
        );

        String baseUrl = ConfigReader.getProperty("base.url");
        driver.get(baseUrl);
        log.info("Navigated to: {}", baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            log.error("Test FAILED: {}", result.getMethod().getMethodName());
        } else {
            log.info("Test PASSED: {}", result.getMethod().getMethodName());
        }
        DriverFactory.quitDriver();
        log.info("Browser closed");
    }
}