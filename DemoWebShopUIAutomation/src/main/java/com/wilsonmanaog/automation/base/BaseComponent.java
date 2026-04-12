package com.wilsonmanaog.automation.base;

import com.wilsonmanaog.automation.utils.ElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseComponent {

    protected WebDriver driver;
    protected ElementActions actions;

    public BaseComponent(WebDriver driver) {
        this.driver = driver;
        this.actions = new ElementActions(driver);
    }

    public void click(WebElement element) {
        actions.click(element);
    }

    public boolean isElementDisplayed(WebElement element) {
        return actions.isElementDisplayed(element);
    }
}
