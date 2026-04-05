package com.naveen.pages;

import com.naveen.utils.ConfigReader;
import com.naveen.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {

    private static final Logger log = LogManager.getLogger(CartPage.class);
    private WebDriver driver;

    // Locators
    private By pageTitle = By.className("title");
    private By cartItem = By.className("cart_item");
    private By itemName = By.className("inventory_item_name");
    private By checkoutButton = By.id("checkout");
    private By continueShoppingButton = By.id("continue-shopping");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public String getPageTitle() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        String title = WaitUtils.waitForVisible(driver, pageTitle, wait).getText();
        log.info("Cart page title: {}", title);
        return title;
    }

    public String getCartItemName() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        String name = WaitUtils.waitForVisible(driver, itemName, wait).getText();
        log.info("Cart item name: {}", name);
        return name;
    }

    public boolean isCartItemDisplayed() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        WaitUtils.waitForVisible(driver, cartItem, wait);
        boolean displayed = driver.findElements(cartItem).size() > 0;
        log.info("Cart item displayed: {}", displayed);
        return displayed;
    }

    public void clickCheckout() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        WaitUtils.waitForClickable(driver, checkoutButton, wait).click();
        log.info("Clicked checkout button");
        new WebDriverWait(driver, Duration.ofSeconds(wait))
                .until(d -> d.getCurrentUrl().contains("checkout-step-one"));
        log.info("Checkout step one URL confirmed");
    }

    public void clickContinueShopping() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        WaitUtils.waitForClickable(driver, continueShoppingButton, wait).click();
        log.info("Clicked continue shopping button");
    }

    public boolean isCartPageDisplayed() {
        int wait = Integer.parseInt(ConfigReader.getProperty("explicit.wait"));
        String title = WaitUtils.waitForVisible(driver, pageTitle, wait).getText();
        return title.equalsIgnoreCase("Your Cart");
    }
}