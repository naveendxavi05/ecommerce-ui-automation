package com.naveen.tests;

import com.naveen.base.BaseTest;
import com.naveen.pages.LoginPage;
import com.naveen.pages.OrderConfirmationPage;
import com.naveen.pages.CartPage;
import com.naveen.pages.CheckoutPage;
import com.naveen.pages.ProductsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(SmokeTest.class);
    @Test(groups = {"smoke", "regression"})
    public void verifyHomePageTitle() {
        String title = driver.getTitle();
        log.info("Page title is: {}", title);
        Assert.assertEquals(title, "Swag Labs");
        log.info("Smoke test passed - Swag Labs title verified");
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyCheckoutFlow() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        OrderConfirmationPage confirmationPage = new OrderConfirmationPage(driver);

        // Login
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                "Products page not displayed after login");

        // Add to cart
        productsPage.addBackpackToCart();
        Assert.assertEquals(productsPage.getCartBadgeCount(), "1",
                "Cart badge count mismatch");

        // Go to cart
        productsPage.goToCart();
        Assert.assertTrue(cartPage.isCartPageDisplayed(),
                "Cart page not displayed");
        Assert.assertTrue(cartPage.isCartItemDisplayed(),
                "Cart item not displayed");

        // Checkout
        cartPage.clickCheckout();
        Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed(),
                "Checkout page not displayed");

        // Fill shipping info
        checkoutPage.fillShippingInfo("John", "Doe", "12345");
        checkoutPage.clickContinue();

        // Finish order
        checkoutPage.clickFinish();
        Assert.assertTrue(confirmationPage.isOrderConfirmed(),
                "Order confirmation not displayed");

        log.info("End-to-end checkout flow passed");
    }
}