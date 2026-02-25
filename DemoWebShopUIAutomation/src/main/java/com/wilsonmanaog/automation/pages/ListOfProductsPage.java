package com.wilsonmanaog.automation.pages;

import com.wilsonmanaog.automation.base.BasePage;
import com.wilsonmanaog.automation.model.ProductCategory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ListOfProductsPage extends BasePage {

    private final String PRODUCTS_TITLE = "//div/h1[contains(text(),'%s')]";

    @FindBy(css="div.item-box")
    List<WebElement> products;

    public ListOfProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getProductsTitle(ProductCategory productCategory) {
        String formattedXpath = String.format(PRODUCTS_TITLE, productCategory.getDisplayName());
        return driver.findElement(By.xpath(formattedXpath));
    }

    public void waitForPageToLoad(ProductCategory productCategory) {
        waitElementToBeVisible(getProductsTitle(productCategory));
    }

    public ProductPage selectProductByName(String productName) {
        WebElement productLink = products.stream()
                .filter(e -> e.findElement(By.cssSelector("h2.product-title a")).getText().trim().equalsIgnoreCase(productName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found: " + productName))
                .findElement(By.cssSelector("h2.product-title a"));
        click(productLink);
        return new ProductPage(driver);
    }
}
