package com.wilsonmanaog.automation.tests;

import com.wilsonmanaog.automation.base.BaseTest;
import com.wilsonmanaog.automation.config.ConfigReader;
import com.wilsonmanaog.automation.config.DataSource;
import com.wilsonmanaog.automation.model.Product;
import com.wilsonmanaog.automation.model.ProductCategory;
import com.wilsonmanaog.automation.model.User;
import com.wilsonmanaog.automation.pages.ListOfProductsPage;
import com.wilsonmanaog.automation.pages.LoginPage;
import com.wilsonmanaog.automation.pages.ProductPage;
import com.wilsonmanaog.automation.pages.ShoppingCartPage;
import com.wilsonmanaog.automation.utils.GenericDataProvider;
import com.wilsonmanaog.automation.utils.ProductMapper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

public class AddProductToCartTest extends BaseTest {

    private List<Product> products;

    @BeforeMethod(groups = {"SmokeTests"})
    public void login() {
        //Go to Login Page
        LoginPage loginPage = homePage.getHeader().goToLoginPage();

        //Perform Login
        User user = new User(ConfigReader.get("email"), ConfigReader.get("password"));
        loginPage.login(user);
    }

    @Test(groups = {"SmokeTests"}, dataProvider = "dataProvider", dataProviderClass = GenericDataProvider.class)
    @DataSource("addProductsToCart.json")
    public void addProductsToCartTest(Map<String, Object> data) {
        //Get the Products
        products = ProductMapper.fromJson(data);

        //Add Products to Cart
        for (Product product : products) {
            //Go to List of Products page for the Product Category
            ListOfProductsPage listOfProductsPage = homePage.getCategories().selectProductCategory(ProductCategory.valueOf(product.getCategory()));
            listOfProductsPage.waitForPageToLoad(ProductCategory.valueOf(product.getCategory()));

            //Select the Product and Add to Cart
            ProductPage productPage = listOfProductsPage.selectProductByName(product.getName());
            productPage.waitForPageToLoad(product.getName());
            productPage.addProductToCart(product.getQuantity());
        }

        //Verify that all Products were added to Cart successfully
        ShoppingCartPage shoppingCartPage = homePage.getHeader().goToShoppingCartPage();
        shoppingCartPage.waitForPageToLoad();
        Assert.assertTrue(shoppingCartPage.areAllProductsAddedToCart(products),
                "Not all products were added to cart successfully");
        Assert.assertTrue(shoppingCartPage.areTotalPricesOfProductsInCartCorrect(products),
                "Total Prices of products in cart are not correct");
    }

    @AfterMethod(alwaysRun = true, groups = {"SmokeTests"})
    public void removeProductsFromCart() {
        //Go to Shopping Cart Page
        ShoppingCartPage shoppingCartPage = homePage.getHeader().goToShoppingCartPage();
        shoppingCartPage.waitForPageToLoad();

        //Remove all products from cart
        shoppingCartPage.removeAllProductsFromCart(products);
    }
}
