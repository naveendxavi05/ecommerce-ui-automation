package com.naveen.pages;

import com.naveen.utils.ConfigReader;
import com.naveen.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {

    private static final Logger log = LogManager.getLogger(ProductsPage.class);
    private WebDriver driver;

    // Locators
    private By pageTitle = By.className("title");
    private By addToCartButton = By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']");
    private By cartIcon = By.className("shopping_cart_link");
    private By cartBadge = By.className("shopping_cart_badge");

    // Constructor
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public String getPageTitle() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        String title = WaitUtils.waitForVisible(driver, pageTitle, wait).getText();
        log.info("Products page title: {}", title);
        return title;
    }

    public void addBackpackToCart() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        WaitUtils.waitForClickable(driver, addToCartButton, wait).click();
        log.info("Added Sauce Labs Backpack to cart");
    }

    public void goToCart() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        WaitUtils.waitForClickable(driver, cartIcon, wait).click();
        log.info("Clicked cart icon");
    }

    public String getCartBadgeCount() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        String count = WaitUtils.waitForVisible(driver, cartBadge, wait).getText();
        log.info("Cart badge count: {}", count);
        return count;
    }

    public boolean isProductsPageDisplayed() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        String title = WaitUtils.waitForVisible(driver, pageTitle, wait).getText();
        return title.equalsIgnoreCase("Products");
    }
}