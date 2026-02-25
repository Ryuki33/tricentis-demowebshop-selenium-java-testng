package com.wilsonmanaog.automation.pages;

import com.wilsonmanaog.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage {

    private final String PRODUCT_TITLE = "//div/h1[contains(text(),'%s')]";

    @FindBy(css="input.button-1.add-to-cart-button")
    WebElement addToCartButton;

    @FindBy(css="input.qty-input")
    WebElement quantityTextBox;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getProductTitle(String productName) {
        String formattedXpath = String.format(PRODUCT_TITLE, productName);
        return driver.findElement(By.xpath(formattedXpath));
    }

    public void waitForPageToLoad(String productName) {
        waitElementToBeVisible(getProductTitle(productName));
    }

    public void addProductToCart(int quantity) {
        typeText(quantityTextBox, String.valueOf(quantity));
        click(addToCartButton);
    }
}
