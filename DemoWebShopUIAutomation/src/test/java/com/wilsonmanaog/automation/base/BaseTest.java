package com.wilsonmanaog.automation.base;

import com.wilsonmanaog.automation.driver.DriverFactory;
import com.wilsonmanaog.automation.driver.DriverManager;
import com.wilsonmanaog.automation.listeners.TestListener;
import com.wilsonmanaog.automation.pages.HomePage;
import com.wilsonmanaog.automation.utils.LogUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.lang.reflect.Method;

@Listeners(TestListener.class)
public class BaseTest {

    private static final Logger log = LogUtils.getLogger(BaseTest.class);
    protected HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) throws IOException {
        String testName = method.getDeclaringClass().getSimpleName();
        log.info(String.format("=== Test Started: %s ===", testName));
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
        log.info("=== Test Finished ===");
    }
}
