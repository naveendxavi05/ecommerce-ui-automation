package com.naveen.tests;

import com.naveen.base.BaseTest;
import com.naveen.pages.LoginPage;
import com.naveen.pages.ProductsPage;
import com.naveen.utils.ExtentReportManager;
import com.naveen.utils.RetryAnalyzer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(LoginTest.class);

    @BeforeSuite
    public void initReport() {
        ExtentReportManager.getInstance();
        log.info("Extent Report initialized");
    }

    @Test(groups = {"smoke","regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void verifySuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                "Products page not displayed after successful login");
        log.info("Verified successful login");
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void verifyLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("locked_out_user", "secret_sauce");
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("locked out"),
                "Expected locked out error message");
        log.info("Verified locked out user error: {}", error);
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void verifyInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "wrong_password");
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Username and password do not match"),
                "Expected invalid credentials error");
        log.info("Verified invalid password error: {}", error);
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void verifyEmptyUsername() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLogin();
        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("Username is required"),
                "Expected username required error");
        log.info("Verified empty username error: {}", error);
    }
    @AfterSuite
    public void generateReport() {
        ExtentReportManager.flush();
        log.info("Extent Report generated at test-output/ExtentReport.html");
    }
}