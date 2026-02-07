package com.wilsonmanaog.automation.base;

import com.wilsonmanaog.automation.components.HeaderMenuSection;
import com.wilsonmanaog.automation.components.ItemCategoriesSection;
import com.wilsonmanaog.automation.utils.ElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {

    protected WebDriver driver;
    protected ElementActions actions;
    protected HeaderMenuSection header;
    protected ItemCategoriesSection categories;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ElementActions(driver);
        this.header = new HeaderMenuSection(driver);
        this.categories = new ItemCategoriesSection(driver);
    }

    public void click(WebElement element) {
        actions.click(element);
    }

    public void typeText(WebElement element, String text) {
        actions.typeText(element, text);
    }

    public void waitElementToBeVisible(WebElement element) {
        actions.waitElementToBeVisible(element);
    }

    public HeaderMenuSection getHeader() {
        return header;
    }

    public ItemCategoriesSection getCategories() {
        return categories;
    }
}
