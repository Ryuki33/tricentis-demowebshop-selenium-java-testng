package com.wilsonmanaog.automation.tests;

import com.wilsonmanaog.automation.base.BaseTest;
import com.wilsonmanaog.automation.config.DataSource;
import com.wilsonmanaog.automation.model.Product;
import com.wilsonmanaog.automation.pages.SearchProductsPage;
import com.wilsonmanaog.automation.utils.GenericDataProvider;
import com.wilsonmanaog.automation.utils.ProductMapper;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

public class SearchProductTest extends BaseTest {

    @Test(groups = {"SmokeTests"}, dataProvider = "dataProvider", dataProviderClass = GenericDataProvider.class)
    @DataSource("validProductSearch.json")
    public void validProductSearchTest(Map<String, Object> data) {
        //Get the Products
        List<Product> expectedProducts = ProductMapper.fromJson(data);

        //Go to Search Products Page
        SearchProductsPage searchProductsPage = homePage.searchForProduct((String) data.get("searchKeyword"));
        searchProductsPage.waitForPageToLoad();

        //Verify Product Search Results
        Assert.assertTrue(searchProductsPage.areProductsDisplayed(expectedProducts),
                "Expected products were not displayed for search keyword: " + data.get("searchKeyword"));
    }

    @Test(groups = {"ErrorValidationTests"}, dataProvider = "dataProvider", dataProviderClass = GenericDataProvider.class)
    @DataSource("invalidProductSearch.json")
    public void invalidProductSearchTest(Map<String, Object> data) {
        //Go to Search Products Page
        SearchProductsPage searchProductsPage = homePage.searchForProduct((String) data.get("searchKeyword"));
        searchProductsPage.waitForPageToLoad();

        //Verify No Products Message Displayed
        Assert.assertTrue(searchProductsPage.isNoProductsMessageDisplayed(),
                "No products message was not displayed for search keyword: " + data.get("searchKeyword"));;
    }
}
