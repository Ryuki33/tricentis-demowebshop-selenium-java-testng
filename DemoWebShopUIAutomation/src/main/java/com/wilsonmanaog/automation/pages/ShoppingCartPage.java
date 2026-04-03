package com.wilsonmanaog.automation.pages;

import com.wilsonmanaog.automation.base.BasePage;
import com.wilsonmanaog.automation.model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShoppingCartPage extends BasePage {

    @FindBy(xpath="//div/h1[contains(text(),'Shopping cart')]")
    WebElement shoppingCartPageTitle;

    @FindBy(css="input[name='updatecart']")
    WebElement updateShoppingCartButton;

    @FindBy(xpath="//div[@class='order-summary-content' and contains(text(),'Your Shopping Cart is empty!')]")
    WebElement shoppingCartEmptyMessage;

    @FindBy(css="tr.cart-item-row")
    List<WebElement> productsInCart;

    @FindBy(id="termsofservice")
    WebElement termsOfServiceCheckbox;

    @FindBy(id="checkout")
    WebElement checkOutButton;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitForPageToLoad() {
        waitElementToBeVisible(shoppingCartPageTitle);
    }

    public boolean isProductAddedToCart(Product product) {
        return productsInCart.stream()
                .anyMatch(e -> e.findElement(By.cssSelector("td.product a")).getText().equalsIgnoreCase(product.getName()));
    }

    public boolean areAllProductsAddedToCart(List<Product> products) {
         return products.stream()
                .allMatch(this::isProductAddedToCart);
    }

    public boolean isTotalPriceOfProductInCartCorrect(Product product) {
        return productsInCart.stream()
                .filter(e -> e.findElement(By.cssSelector("td.product a")).getText().trim().equalsIgnoreCase(product.getName()))
                .anyMatch(e -> e.findElement(By.cssSelector("td.subtotal.nobr.end span.product-subtotal")).getText().trim().equalsIgnoreCase(String.format("%.2f", product.getPrice() * product.getQuantity())));
    }

    public boolean areTotalPricesOfProductsInCartCorrect(List<Product> products) {
        return products.stream()
                .allMatch(this::isTotalPriceOfProductInCartCorrect);
    }

    public void removeProductFromCart(Product product) {
        productsInCart.stream()
                .filter(e -> e.findElement(By.cssSelector("td.product a")).getText().trim().equalsIgnoreCase(product.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in cart: " + product.getName()))
                .findElement(By.cssSelector("td.remove-from-cart input[type='checkbox']"))
                .click();
        click(updateShoppingCartButton);
    }

    public void removeAllProductsFromCart(List<Product> products) {
        products.forEach(this::removeProductFromCart);
    }

    public CheckoutPage goToCheckoutPage() {
        click(termsOfServiceCheckbox);
        click(checkOutButton);
        return new CheckoutPage(driver);
    }
}
