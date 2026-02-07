package com.wilsonmanaog.automation.pages;

import com.wilsonmanaog.automation.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(id="Email")
    WebElement emailTextBox;

    @FindBy(id="Password")
    WebElement passwordTextBox;

    @FindBy(css="input.login-button")
    WebElement loginButton;

    @FindBy(xpath="//div/h1[contains(text(),'Welcome, Please Sign In!')]")
    WebElement loginPageTitle;

    @FindBy(xpath="//div[@class='message-error']//span[contains(text(),'Login was unsuccessful. Please correct the errors and try again.')]")
    WebElement loginErrorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitForPageToLoad() {
        waitElementToBeVisible(loginPageTitle);
    }

    public void login(String email, String password) {
        typeText(emailTextBox, email);
        typeText(passwordTextBox, password);
        click(loginButton);
    }

    public boolean isLoginErrorMessageDisplayed() {
        return loginErrorMessage.isDisplayed();
    }
}
