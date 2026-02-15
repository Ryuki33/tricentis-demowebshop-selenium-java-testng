package com.wilsonmanaog.automation.pages;

import com.wilsonmanaog.automation.base.BasePage;
import com.wilsonmanaog.automation.model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchProductsPage extends BasePage {

    @FindBy(xpath="//div/h1[contains(text(),'Search')]")
    WebElement searchProductsPageTitle;

    @FindBy(css="div.item-box")
    List<WebElement> products;

    @FindBy(xpath="//div[@class='search-results']/strong[contains(text(),'No products were found that matched your criteria')]")
    WebElement noProductsMessage;

    public SearchProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitForPageToLoad() {
        waitElementToBeVisible(searchProductsPageTitle);
    }

    public boolean areProductsDisplayed(List<Product> expectedProducts) {
        for (Product expectedProduct : expectedProducts) {
            boolean productFound = products.stream()
                    .anyMatch(e -> e.findElement(By.cssSelector("h2.product-title a")).getText().equalsIgnoreCase(expectedProduct.getName()));
            if (!productFound) {
                return false;
            }
        }
        return true;
    }

    public boolean isNoProductsMessageDisplayed() {
        return noProductsMessage.isDisplayed();
    }
}
