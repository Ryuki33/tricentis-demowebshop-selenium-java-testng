package com.wilsonmanaog.automation.tests;

import com.wilsonmanaog.automation.base.BaseTest;
import com.wilsonmanaog.automation.config.ConfigReader;
import com.wilsonmanaog.automation.config.DataSource;
import com.wilsonmanaog.automation.model.*;
import com.wilsonmanaog.automation.pages.*;
import com.wilsonmanaog.automation.utils.GenericDataProvider;
import com.wilsonmanaog.automation.utils.ProductMapper;
import com.wilsonmanaog.automation.utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

public class CheckoutProductEndToEndTest extends BaseTest {

    @BeforeMethod(alwaysRun = true, groups = {"RegressionTests"})
    public void loginAndEmptyShoppingCart() {
        //Go to Login Page
        LoginPage loginPage = homePage.getHeader().goToLoginPage();

        //Perform Login
        User user = new User(ConfigReader.get("email"), ConfigReader.get("password"));
        loginPage.login(user);

        //Go to Shopping Cart
        ShoppingCartPage shoppingCartPage = homePage.getHeader().goToShoppingCartPage();
        shoppingCartPage.waitForPageToLoad();

        //Remove products from cart if there is one
        shoppingCartPage.removeAllProductsFromCart();
    }

    @Test(groups = {"RegressionTests"}, dataProvider = "dataProvider", dataProviderClass = GenericDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    @DataSource("checkoutProducts.json")
    @SuppressWarnings("unchecked")
    public void checkoutProductTest(Map<String, Object> data) {
        //Get the Products
        List<Product> products = ProductMapper.fromJson((Map<String, Object>) data.get("checkoutProduct"));

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

        //Checkout Products in Cart
        ShoppingCartPage shoppingCartPage = homePage.getHeader().goToShoppingCartPage();
        shoppingCartPage.waitForPageToLoad();
        CheckoutPage checkoutPage = shoppingCartPage.goToCheckoutPage();
        checkoutPage.waitForPageToLoad();

        //Complete Billing Address Step
        Map<String, String> billingAddressData = (Map<String, String>) ((Map<String, Object>) data.get("checkoutProduct")).get("billingAddress");
        Address billingAddress = new Address(billingAddressData.get("country"), billingAddressData.get("state"), billingAddressData.get("city"), billingAddressData.get("address1"), billingAddressData.get("zipPostalCode"), billingAddressData.get("phoneNumber"));
        checkoutPage.completeBillingAddressStep(billingAddress);

        //Complete Shipping Address Step
        Map<String, Object> shippingInfo = (Map<String, Object>) ((Map<String, Object>) data.get("checkoutProduct")).get("shippingInfo");
        checkoutPage.completeShippingAddressStep(shippingInfo);

        //Select Shipping Method if Required
        checkoutPage.completeShippingMethodStepIfRequired(shippingInfo);

        //Select Payment Method and Complete Payment Information
        checkoutPage.completePaymentMethodStep(PaymentMethod.valueOf((String) ((Map<String, Object>) ((Map<String, Object>) data.get("checkoutProduct")).get("paymentDetails")).get("paymentMethod")));
        checkoutPage.completePaymentInfoStep((Map<String, Object>) ((Map<String, Object>) data.get("checkoutProduct")).get("paymentDetails"));

        //Verify Order Details before Confirming Order
        //Setting tax value to zero as it is always defined as zero in the application and is not included in the test data
        double tax = 0.00;
        Assert.assertTrue(checkoutPage.areConfirmOrderDetailsCorrect(
                billingAddress,
                shippingInfo,
                tax,
                PaymentMethod.valueOf((String) ((Map<String, Object>) ((Map<String, Object>) data.get("checkoutProduct")).get("paymentDetails")).get("paymentMethod")),
                ShippingMethod.valueOf((String) shippingInfo.get("shippingMethod")),
                products), "Order details verification failed. Expected order details to be correct before confirming order."
        );

        //Verify Order Confirmation
        Assert.assertTrue(checkoutPage.isConfirmOrderSuccessful(), "Order confirmation failed. Expected order to be confirmed successfully.");
    }

    @AfterMethod(alwaysRun = true, groups = {"RegressionTests"})
    public void removeProductsFromCartAndLogOut() {
        //Go to Shopping Cart Page
        ShoppingCartPage shoppingCartPage = homePage.getHeader().goToShoppingCartPage();
        shoppingCartPage.waitForPageToLoad();

        //Remove all products from cart
        shoppingCartPage.removeAllProductsFromCart();

        //Logout
        homePage.getHeader().logout();
    }
}
