package com.wilsonmanaog.automation.components;

import com.wilsonmanaog.automation.base.BaseComponent;
import com.wilsonmanaog.automation.model.ProductCategory;
import com.wilsonmanaog.automation.pages.ListOfProductsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ProductCategoriesSection extends BaseComponent {

    @FindBy(xpath="//div[@class='header-menu']/ul/li/a")
    List<WebElement> categoryLinks;

    public ProductCategoriesSection(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ListOfProductsPage selectProductCategory(ProductCategory category) {
        WebElement categoryLink = categoryLinks.stream()
                .filter(e -> e.getText().equalsIgnoreCase(category.getDisplayName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Category not found: " + category.getDisplayName()));
        click(categoryLink);
        return new ListOfProductsPage(driver);
    }
}
