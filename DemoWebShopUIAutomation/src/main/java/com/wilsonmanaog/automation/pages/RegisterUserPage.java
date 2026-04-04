package com.wilsonmanaog.automation.pages;

import com.wilsonmanaog.automation.base.BasePage;
import com.wilsonmanaog.automation.model.UserRegistration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterUserPage extends BasePage {

    @FindBy(id="gender-male")
    WebElement maleGenderRadioButton;

    @FindBy(id="gender-female")
    WebElement femaleGenderRadioButton;

    @FindBy(id="FirstName")
    WebElement firstNameTextBox;

    @FindBy(id="LastName")
    WebElement lastNameTextBox;

    @FindBy(id="Email")
    WebElement emailTextBox;

    @FindBy(id="Password")
    WebElement passwordTextBox;

    @FindBy(id="ConfirmPassword")
    WebElement confirmPasswordTextBox;

    @FindBy(id="register-button")
    WebElement registerButton;

    @FindBy(xpath="//div/h1[contains(text(),'Register')]")
    WebElement registerUserPageTitle;

    @FindBy(xpath="//div[@class='result' and contains(text(),'Your registration completed')]")
    WebElement registrationSuccessMessage;

    @FindBy(css="span.field-validation-error span")
    WebElement registrationErrorMessage;

    public RegisterUserPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitForPageToLoad() {
        waitElementToBeVisible(registerUserPageTitle);
    }

    public void registerUser(UserRegistration user) {
        waitForPageToLoad();
        if (user.getGender().equalsIgnoreCase("Male")) {
            click(maleGenderRadioButton);
        } else if (user.getGender().equalsIgnoreCase("Female")) {
            click(femaleGenderRadioButton);
        }
        typeText(firstNameTextBox, user.getFirstName());
        typeText(lastNameTextBox, user.getLastName());
        typeText(emailTextBox, user.getEmail());
        typeText(passwordTextBox, user.getPassword());
        typeText(confirmPasswordTextBox, user.getPassword());
        click(registerButton);
    }

    public boolean isRegistrationSuccessful(String email) {
        return registrationSuccessMessage.isDisplayed() &&
                getHeader().isLoginSuccessful(email);
    }

    public boolean isRegistrationErrorMessageDisplayed(String errorMessage) {
        return registrationErrorMessage.isDisplayed() &&
                registrationErrorMessage.getText().equalsIgnoreCase(errorMessage);
    }
}
