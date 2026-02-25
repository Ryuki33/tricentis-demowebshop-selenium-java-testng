package com.wilsonmanaog.automation.components;

import com.wilsonmanaog.automation.base.BaseComponent;
import com.wilsonmanaog.automation.pages.LoginPage;
import com.wilsonmanaog.automation.pages.RegisterUserPage;
import com.wilsonmanaog.automation.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderMenuSection extends BaseComponent {

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
        click(logInLink);
        return new LoginPage(driver);
    }

    public RegisterUserPage goToRegisterPage() {
        click(registerLink);
        return new RegisterUserPage(driver);
    }

    public ShoppingCartPage goToShoppingCartPage() {
        click(shoppingCartLink);
        return new ShoppingCartPage(driver);
    }

    public boolean isLoginSuccessful(String userName) {
        return accountLink.getText().equalsIgnoreCase(userName);
    }
}
