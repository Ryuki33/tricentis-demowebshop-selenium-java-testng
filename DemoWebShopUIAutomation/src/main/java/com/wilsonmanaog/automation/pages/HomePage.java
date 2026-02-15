package com.wilsonmanaog.automation.pages;

import com.wilsonmanaog.automation.base.BasePage;
import com.wilsonmanaog.automation.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    @FindBy(css="div.header-logo")
    WebElement homepageLogo;

    @FindBy(css="input#small-searchterms")
    WebElement searchTextBox;

    @FindBy(css="input.search-box-button")
    WebElement searchButton;

    private final String url;

    public HomePage(WebDriver driver) {
        super(driver);
        this.url = ConfigReader.get("baseUrl");
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(url);
        waitElementToBeVisible(homepageLogo);
    }

    public SearchProductsPage searchForProduct(String productName) {
        typeText(searchTextBox, productName);
        click(searchButton);
        return new SearchProductsPage(driver);
    }
}
