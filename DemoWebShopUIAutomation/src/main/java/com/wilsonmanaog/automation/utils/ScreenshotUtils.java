package com.wilsonmanaog.automation.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenshotUtils {
    public static String capture(String testCaseName, WebDriver driver) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            String path = System.getProperty("user.dir") +
                    "/reports/screenshots/" +
                    testCaseName + "_" + System.currentTimeMillis() + ".png";

            File file = new File(path);
            file.getParentFile().mkdirs(); // ensure folder exists

            FileUtils.copyFile(source, file);
            return path;

        } catch (Exception e) {
            throw new RuntimeException("Failed to capture screenshot", e);
        }
    }
}
