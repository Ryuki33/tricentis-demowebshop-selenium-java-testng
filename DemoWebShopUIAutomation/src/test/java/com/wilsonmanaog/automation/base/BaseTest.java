package com.wilsonmanaog.automation.base;

import com.wilsonmanaog.automation.driver.DriverFactory;
import com.wilsonmanaog.automation.driver.DriverManager;
import com.wilsonmanaog.automation.listeners.TestListener;
import com.wilsonmanaog.automation.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.IOException;

@Listeners(TestListener.class)
public class BaseTest {

    protected HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        WebDriver driver = DriverFactory.createDriver();
        DriverManager.setDriver(driver);
        homePage = new HomePage(driver);
        homePage.open();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            driver.quit();
            DriverManager.unload();
        }
    }
}
