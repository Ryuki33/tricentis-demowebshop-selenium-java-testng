package com.wilsonmanaog.automation.pages;

import com.wilsonmanaog.automation.base.BasePage;
import com.wilsonmanaog.automation.model.Product;
import com.wilsonmanaog.automation.utils.LogUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchProductsPage extends BasePage {

    private static final Logger log = LogUtils.getLogger(SearchProductsPage.class);

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
        log.info("Waiting for the search products page to load...");
        waitElementToBeVisible(searchProductsPageTitle);
    }

    public boolean areProductsDisplayed(List<Product> expectedProducts) {
        log.info("Checking if expected products are displayed in the search results...");
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
        log.info("Checking if 'No products found' message is displayed...");
        return noProductsMessage.isDisplayed();
    }
}
