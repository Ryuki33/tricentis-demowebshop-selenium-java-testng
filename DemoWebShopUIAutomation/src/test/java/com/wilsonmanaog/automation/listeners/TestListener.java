package com.wilsonmanaog.automation.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.wilsonmanaog.automation.base.BaseTest;
import com.wilsonmanaog.automation.driver.DriverManager;
import com.wilsonmanaog.automation.utils.ExtentReportManager;
import com.wilsonmanaog.automation.utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestListener extends BaseTest implements ITestListener {

    private final ExtentReports extent = ExtentReportManager.getInstance();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(
                result.getTestClass().getName() + " - " + result.getMethod().getMethodName()
        );
        test.assignCategory(result.getMethod().getGroups());
        ExtentReportManager.setTest(test);
        ExtentReportManager.getTest().info(Arrays.toString(result.getParameters()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverManager.getDriver();
        String path = ScreenshotUtils.capture(result.getMethod().getMethodName(), driver);
        ExtentReportManager.getTest().fail(result.getThrowable()).addScreenCaptureFromPath(path);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().skip("Test Skipped");
    }


    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
        ExtentReportManager.getTest().fail(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}

