package com.wilsonmanaog.automation.components;

import com.wilsonmanaog.automation.base.BaseComponent;
import com.wilsonmanaog.automation.model.ItemCategory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ItemCategoriesSection extends BaseComponent {

    @FindBy(xpath="//div[@class='header-menu']/ul/li/a")
    List<WebElement> categoryLinks;

    public ItemCategoriesSection(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectItemCategory(ItemCategory category) {
        WebElement categoryLink = categoryLinks.stream()
                .filter(e -> e.getText().equalsIgnoreCase(category.getDisplayName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Category not found: " + category.getDisplayName()));
        click(categoryLink);
    }
}
