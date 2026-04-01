package com.naveen.tests;

import com.naveen.base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(SmokeTest.class);
    @Test
    public void verifyHomePageTitle() {
        String title = driver.getTitle();
        log.info("Page title is: {}", title);
        Assert.assertEquals(title, "Swag Labs");
        log.info("Smoke test passed - Swag Labs title verified");
    }
}