package com.wilsonmanaog.automation.tests;

import com.wilsonmanaog.automation.base.BaseTest;
import com.wilsonmanaog.automation.config.DataSource;
import com.wilsonmanaog.automation.model.User;
import com.wilsonmanaog.automation.pages.LoginPage;
import com.wilsonmanaog.automation.utils.GenericDataProvider;
import com.wilsonmanaog.automation.utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;

public class LoginTest extends BaseTest {

    @Test(groups = {"SmokeTests"}, dataProvider = "dataProvider", dataProviderClass = GenericDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    @DataSource("validUserLogin.csv")
    public void validLoginTest(Map<String, String> data) {
        //Go to Login Page
        LoginPage loginPage = homePage.getHeader().goToLoginPage();

        //Perform Login
        User user = new User(data.get("email"), data.get("password"));
        loginPage.login(user);

        //Verify Login Successful
        Assert.assertTrue(homePage.getHeader().isLoginSuccessful(user.getEmail()),
                "Login was not successful for user: " + user.getEmail());
    }

    @Test(groups = {"ErrorValidationTests"}, dataProvider = "dataProvider", dataProviderClass = GenericDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    @DataSource("invalidUserLogin.csv")
    public void invalidLoginTest(Map<String, String> data) {
        //Go to Login Page
        LoginPage loginPage = homePage.getHeader().goToLoginPage();

        //Perform Login
        User user = new User(data.get("email"), data.get("password"));
        loginPage.login(user);

        //Verify Login Error Message
        Assert.assertTrue(loginPage.isLoginErrorMessageDisplayed(),
                "Expected login error message was not displayed for user: " + user.getEmail());
    }
}
