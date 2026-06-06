package com.wilsonmanaog.automation.components;

import com.wilsonmanaog.automation.base.BaseComponent;
import com.wilsonmanaog.automation.pages.LoginPage;
import com.wilsonmanaog.automation.pages.RegisterUserPage;
import com.wilsonmanaog.automation.pages.ShoppingCartPage;
import com.wilsonmanaog.automation.utils.LogUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderMenuSection extends BaseComponent {

    private static final Logger log = LogUtils.getLogger(HeaderMenuSection.class);

    @FindBy(css="a.ico-login")
    WebElement logInLink;

    @FindBy(css="a.ico-logout")
    WebElement logoutLink;

    @FindBy(css="a.account")
    WebElement accountLink;

    @FindBy(css="a.ico-register")
    WebElement registerLink;

    @FindBy(css="a.ico-cart")
    WebElement shoppingCartLink;

    @FindBy(css="a.ico-wishlist")
    WebElement wishlistLink;

    public HeaderMenuSection(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage goToLoginPage() {
        log.info("Clicking the login link in the header menu...");
        click(logInLink);
        return new LoginPage(driver);
    }

    public RegisterUserPage goToRegisterPage() {
        log.info("Clicking the register link in the header menu...");
        click(registerLink);
        return new RegisterUserPage(driver);
    }

    public ShoppingCartPage goToShoppingCartPage() {
        log.info("Clicking the shopping cart link in the header menu...");
        int attempts = 0;
        while (attempts < 3) {
            try {
                click(shoppingCartLink);
                return new ShoppingCartPage(driver);
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
        throw new RuntimeException("Element still stale after retries");
    }

    public boolean isLoginSuccessful(String userName) {
        log.info("Checking if login was successful by verifying the account link text...");
        return accountLink.getText().equalsIgnoreCase(userName);
    }

    public void logout() {
        log.info("Clicking the logout link in the header menu...");
        click(logoutLink);
    }

    public boolean isLogoutSuccessful() {
        log.info("Checking if logout was successful by verifying the login link is displayed...");
        return isElementDisplayed(logInLink);
    }
}
