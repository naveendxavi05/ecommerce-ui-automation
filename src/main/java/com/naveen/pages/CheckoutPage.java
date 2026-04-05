package com.naveen.pages;

import com.naveen.utils.ConfigReader;
import com.naveen.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private static final Logger log = LogManager.getLogger(CheckoutPage.class);
    private WebDriver driver;

    // Locators
    private By pageTitle = By.className("title");
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By zipCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By itemTotal = By.className("summary_subtotal_label");
    private By errorMessage = By.cssSelector("[data-test='error']");

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public String getPageTitle() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        String title = WaitUtils.waitForVisible(driver, pageTitle, wait).getText();
        log.info("Checkout page title: {}", title);
        return title;
    }

    public void enterFirstName(String firstName) {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        WaitUtils.waitForClickable(driver, firstNameField, wait).click();
        driver.findElement(firstNameField).sendKeys(firstName);
        log.info("Entered first name: {}", firstName);
    }

    public void enterLastName(String lastName) {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        WaitUtils.waitForClickable(driver, lastNameField, wait).click();
        driver.findElement(lastNameField).sendKeys(lastName);
        log.info("Entered last name: {}", lastName);
    }

    public void enterZipCode(String zipCode) {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        WaitUtils.waitForClickable(driver, zipCodeField, wait).click();
        driver.findElement(zipCodeField).sendKeys(zipCode);
        log.info("Entered zip code: {}", zipCode);
    }

    public void fillShippingInfo(String firstName, String lastName, String zipCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZipCode(zipCode);
        log.info("Filled shipping info for: {} {}", firstName, lastName);
    }

    public void clickContinue() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        WaitUtils.waitForClickable(driver, continueButton, wait).click();
        log.info("Clicked continue button");
    }

    public void clickFinish() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        WaitUtils.waitForClickable(driver, finishButton, wait).click();
        log.info("Clicked finish button");
    }

    public String getItemTotal() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        String total = WaitUtils.waitForVisible(driver, itemTotal, wait).getText();
        log.info("Item total: {}", total);
        return total;
    }

    public String getErrorMessage() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        String error = WaitUtils.waitForVisible(driver, errorMessage, wait).getText();
        log.info("Error message: {}", error);
        return error;
    }

    public boolean isCheckoutPageDisplayed() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        String title = WaitUtils.waitForVisible(driver, pageTitle, wait).getText();
        return title.equalsIgnoreCase("Checkout: Your Information");
    }
}