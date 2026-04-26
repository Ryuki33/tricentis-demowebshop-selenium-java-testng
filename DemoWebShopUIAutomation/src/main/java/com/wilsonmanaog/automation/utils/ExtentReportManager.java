package com.wilsonmanaog.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getInstance() {
        if (extent == null) {
            String path = System.getProperty("user.dir") + "/reports/report_" + System.currentTimeMillis() + ".html";
            ExtentSparkReporter spark = new ExtentSparkReporter(path);
            spark.config().setReportName("Demo Web Shop Automation Test Results");
            spark.config().setDocumentTitle("Demo Web Shop Test Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Tester", "Wilson Manaog");
        }
        return extent;
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }
}
