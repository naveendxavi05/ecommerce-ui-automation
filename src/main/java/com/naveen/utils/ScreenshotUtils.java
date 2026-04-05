package com.naveen.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {

    private static final Logger log = LogManager.getLogger(ScreenshotUtils.class);

    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String screenshotDir = "test-output/screenshots/";
            String fileName = screenshotDir + testName + "_" + timestamp + ".png";

            Files.createDirectories(Paths.get(screenshotDir));

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), Paths.get(fileName));

            log.info("Screenshot saved: {}", fileName);
            return fileName;
        } catch (IOException e) {
            log.error("Failed to take screenshot: {}", e.getMessage());
            return null;
        }
    }
}