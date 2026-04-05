package com.naveen.pages;

import com.naveen.utils.ConfigReader;
import com.naveen.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private static final Logger log = LogManager.getLogger(LoginPage.class);
    private WebDriver driver;

    // Locators
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterUsername(String username) {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        WaitUtils.waitForVisible(driver, usernameField, wait).sendKeys(username);
        log.info("Entered username: {}", username);
    }

    public void enterPassword(String password) {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        WaitUtils.waitForVisible(driver, passwordField, wait).sendKeys(password);
        log.info("Entered password");
    }

    public void clickLogin() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        WaitUtils.waitForClickable(driver, loginButton, wait).click();
        log.info("Clicked login button");
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        String error = WaitUtils.waitForVisible(driver, errorMessage, wait).getText();
        log.info("Error message: {}", error);
        return error;
    }

    public boolean isLoginPageDisplayed() {
        return driver.findElements(loginButton).size() > 0;
    }
}