package com.naveen.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Map;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-features=PasswordLeakDetection,PasswordManager");
            options.addArguments("--password-store=basic");
            options.setExperimentalOption("prefs", Map.of(
                    "credentials_enable_service", false,
                    "profile.password_manager_enabled", false
            ));
            driver.set(new ChromeDriver(options));
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver.set(new FirefoxDriver());
        } else {
            throw new RuntimeException("Browser not supported: " + browser);
        }
    }
    public static WebDriver getDriver()
    {
        return driver.get();
    }
    public static void quitDriver()
    {
        if(driver.get() != null)
        {
            driver.get().quit();
            driver.remove();
        }
    }

}
