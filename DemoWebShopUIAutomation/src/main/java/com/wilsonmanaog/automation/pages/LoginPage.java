package com.wilsonmanaog.automation.pages;

import com.wilsonmanaog.automation.base.BasePage;
import com.wilsonmanaog.automation.model.User;
import com.wilsonmanaog.automation.utils.LogUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    private static final Logger log = LogUtils.getLogger(LoginPage.class);

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
        log.info("Waiting for the login page to load...");
        waitElementToBeVisible(loginPageTitle);
    }

    public void login(User user) {
        log.info("Logging in with user: " + user.getEmail());
        waitForPageToLoad();
        typeText(emailTextBox, user.getEmail());
        typeText(passwordTextBox, user.getPassword());
        click(loginButton);
    }

    public boolean isLoginErrorMessageDisplayed() {
        log.info("Checking if login error message is displayed...");
        return loginErrorMessage.isDisplayed();
    }
}
