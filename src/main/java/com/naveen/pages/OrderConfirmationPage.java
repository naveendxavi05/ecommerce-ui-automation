package com.naveen.pages;

import com.naveen.utils.ConfigReader;
import com.naveen.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmationPage {

    private static final Logger log = LogManager.getLogger(OrderConfirmationPage.class);
    private WebDriver driver;

    // Locators
    private By confirmationHeader = By.className("complete-header");
    private By confirmationText = By.className("complete-text");
    private By backHomeButton = By.id("back-to-products");

    // Constructor
    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public String getConfirmationHeader() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        String header = WaitUtils.waitForVisible(driver, confirmationHeader, wait).getText();
        log.info("Confirmation header: {}", header);
        return header;
    }

    public String getConfirmationText() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        String text = WaitUtils.waitForVisible(driver, confirmationText, wait).getText();
        log.info("Confirmation text: {}", text);
        return text;
    }

    public void clickBackHome() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        WaitUtils.waitForClickable(driver, backHomeButton, wait).click();
        log.info("Clicked back to products button");
    }

    public boolean isOrderConfirmed() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        String header = WaitUtils.waitForVisible(driver, confirmationHeader, wait).getText();
        boolean confirmed = header.equalsIgnoreCase("Thank you for your order!");
        log.info("Order confirmed: {}", confirmed);
        return confirmed;
    }
}