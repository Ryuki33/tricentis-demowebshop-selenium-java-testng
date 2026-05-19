package com.wilsonmanaog.automation.utils;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementActions {

    private static final Logger log = LogUtils.getLogger(ElementActions.class);
    private final WebDriverWait wait;

    public ElementActions(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void waitElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(WebElement element) {
        waitElementToBeClickable(element);
        element.click();
    }

    public void typeText(WebElement element, String text) {
        waitElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    public void selectOptionByVisibleText(WebElement element, String text) {
        waitElementToBeClickable(element);
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            waitElementToBeVisible(element);
            return element.isDisplayed();
        } catch (Exception e) {
            log.warn("Element not displayed: " + e.getMessage());
            return false;
        }
    }
}
