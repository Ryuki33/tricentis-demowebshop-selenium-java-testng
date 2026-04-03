package com.wilsonmanaog.automation.tests;

import com.wilsonmanaog.automation.base.BaseTest;
import com.wilsonmanaog.automation.config.ConfigReader;
import com.wilsonmanaog.automation.config.DataSource;
import com.wilsonmanaog.automation.model.*;
import com.wilsonmanaog.automation.pages.*;
import com.wilsonmanaog.automation.utils.GenericDataProvider;
import com.wilsonmanaog.automation.utils.ProductMapper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

public class CheckoutProductEndToEndTest extends BaseTest {

    @BeforeMethod
    public void login() {
        //Go to Login Page
        LoginPage loginPage = homePage.getHeader().goToLoginPage();
        loginPage.waitForPageToLoad();

        //Perform Login
        User user = new User(ConfigReader.get("email"), ConfigReader.get("password"));
        loginPage.login(user.getEmail(), user.getPassword());
    }

    @Test(groups = {"RegressionTests"}, dataProvider = "dataProvider", dataProviderClass = GenericDataProvider.class)
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

        //Complete Billing and Shipping Address Steps
        Map<String, String> billingAddressData = (Map<String, String>) ((Map<String, Object>) data.get("checkoutProduct")).get("billingAddress");
        Address billingAddress = new Address(billingAddressData.get("country"), billingAddressData.get("state"), billingAddressData.get("city"), billingAddressData.get("address1"), billingAddressData.get("zipPostalCode"), billingAddressData.get("phoneNumber"));
        checkoutPage.completeBillingAddressStep(billingAddress.getCountry(), billingAddress.getState(), billingAddress.getCity(), billingAddress.getAddress(), billingAddress.getZipCode(), billingAddress.getPhoneNumber());

        Map<String, String> shippingAddressData = (Map<String, String>) ((Map<String, Object>) data.get("checkoutProduct")).get("shippingAddress");
        Address shippingAddress = new Address(shippingAddressData.get("country"), shippingAddressData.get("state"), shippingAddressData.get("city"), shippingAddressData.get("address1"), shippingAddressData.get("zipPostalCode"), shippingAddressData.get("phoneNumber"));
        checkoutPage.completeShippingAddressStep(shippingAddress.getCountry(), shippingAddress.getState(), shippingAddress.getCity(), shippingAddress.getAddress(), shippingAddress.getZipCode(), shippingAddress.getPhoneNumber());

        //Select Shipping Method
        checkoutPage.completeShippingMethodStep(ShippingMethod.valueOf((String) ((Map<String, Object>) data.get("checkoutProduct")).get("shippingMethod")));

        //Select Payment Method and Complete Payment Information
        checkoutPage.completePaymentMethodStep(PaymentMethod.valueOf((String) ((Map<String, Object>) ((Map<String, Object>) data.get("checkoutProduct")).get("paymentDetails")).get("paymentMethod")));
        checkoutPage.completePaymentInfoStep((Map<String, Object>) ((Map<String, Object>) data.get("checkoutProduct")).get("paymentDetails"));

        //Verify Order Details before Confirming Order
        //Setting tax value to zero as it is always defined as zero in the application and is not included in the test data
        double tax = 0.00;
        Assert.assertTrue(checkoutPage.areConfirmOrderDetailsCorrect(
                billingAddress,
                shippingAddress,
                tax,
                PaymentMethod.valueOf((String) ((Map<String, Object>) ((Map<String, Object>) data.get("checkoutProduct")).get("paymentDetails")).get("paymentMethod")),
                ShippingMethod.valueOf((String) ((Map<String, Object>) data.get("checkoutProduct")).get("shippingMethod")),
                products), "Order details verification failed. Expected order details to be correct before confirming order."
        );

        //Verify Order Confirmation
        Assert.assertTrue(checkoutPage.isConfirmOrderSuccessful(), "Order confirmation failed. Expected order to be confirmed successfully.");
    }

    @AfterMethod
    public void logout() {
        homePage.getHeader().logout();
    }
}
