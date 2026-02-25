package com.wilsonmanaog.automation.tests;

import com.wilsonmanaog.automation.base.BaseTest;
import com.wilsonmanaog.automation.config.DataSource;
import com.wilsonmanaog.automation.model.UserRegistration;
import com.wilsonmanaog.automation.pages.RegisterUserPage;
import com.wilsonmanaog.automation.utils.GenericDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class RegisterUserTest extends BaseTest {

    @Test(groups = {"SmokeTests"}, dataProvider = "dataProvider", dataProviderClass = GenericDataProvider.class)
    @DataSource("validUserRegistration.csv")
    public void validUserRegistrationTest(Map<String, String> data) {
        //Go to Registration Page
        RegisterUserPage registerUserPage = homePage.getHeader().goToRegisterPage();
        registerUserPage.waitForPageToLoad();

        //Perform Registration
        UserRegistration userRegistration = new UserRegistration(data.get("gender"), data.get("firstName"), data.get("lastName"), data.get("email"), data.get("password"));
        registerUserPage.registerUser(userRegistration.getGender(), userRegistration.getFirstName(), userRegistration.getLastName(), userRegistration.getEmail(), userRegistration.getPassword());

        //Verify Registration Successful
        Assert.assertTrue(registerUserPage.isRegistrationSuccessful(userRegistration.getEmail()),
                "Registration was not successful for user: " + userRegistration.getEmail());;
    }

    @Test(groups = {"ErrorValidationTests"}, dataProvider = "dataProvider", dataProviderClass = GenericDataProvider.class)
    @DataSource("invalidUserRegistration.csv")
    public void invalidUserRegistrationTest(Map<String, String> data) {
        //Go to Registration Page
        RegisterUserPage registerUserPage = homePage.getHeader().goToRegisterPage();
        registerUserPage.waitForPageToLoad();

        //Perform Registration
        UserRegistration userRegistration = new UserRegistration(data.get("gender"), data.get("firstName"), data.get("lastName"), data.get("email"), data.get("password"));
        registerUserPage.registerUser(userRegistration.getGender(), userRegistration.getFirstName(), userRegistration.getLastName(), userRegistration.getEmail(), userRegistration.getPassword());

        //Verify Registration Error Message Displayed
        Assert.assertTrue(registerUserPage.isRegistrationErrorMessageDisplayed(data.get("expectedErrorMessage")),
                "Expected registration error message was not displayed: " + data.get("expectedErrorMessage"));
    }
}
