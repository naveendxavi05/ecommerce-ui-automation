package com.naveen.tests;

import com.naveen.base.BaseTest;
import com.naveen.pages.CartPage;
import com.naveen.pages.CheckoutPage;
import com.naveen.pages.LoginPage;
import com.naveen.pages.OrderConfirmationPage;
import com.naveen.pages.ProductsPage;
import com.naveen.utils.RetryAnalyzer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(CheckoutTest.class);

    @Test(groups = {"smoke","regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void verifySuccessfulCheckout() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        OrderConfirmationPage confirmationPage = new OrderConfirmationPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        productsPage.addBackpackToCart();
        productsPage.goToCart();
        cartPage.clickCheckout();
        checkoutPage.fillShippingInfo("John", "Doe", "12345");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        Assert.assertTrue(confirmationPage.isOrderConfirmed(),
                "Order confirmation not displayed");
        log.info("Verified successful checkout");
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void verifyCartBadgeCount() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        productsPage.addBackpackToCart();

        Assert.assertEquals(productsPage.getCartBadgeCount(), "1",
                "Cart badge count should be 1 after adding one item");
        log.info("Verified cart badge count is 1");
    }

    @Test(groups = {"regression"})
    public void verifyCartItemDisplayed() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        productsPage.addBackpackToCart();
        productsPage.goToCart();

        Assert.assertTrue(cartPage.isCartItemDisplayed(),
                "Cart item should be displayed");
        Assert.assertEquals(cartPage.getCartItemName(), "Sauce Labs Backpack",
                "Cart item name mismatch");
        log.info("Verified cart item is displayed correctly");
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void verifyCheckoutWithEmptyInfo() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        productsPage.addBackpackToCart();
        productsPage.goToCart();
        cartPage.clickCheckout();
        checkoutPage.clickContinue();

        String error = checkoutPage.getErrorMessage();
        Assert.assertTrue(error.contains("First Name is required"),
                "Expected first name required error");
        log.info("Verified empty checkout info error: {}", error);
    }
}