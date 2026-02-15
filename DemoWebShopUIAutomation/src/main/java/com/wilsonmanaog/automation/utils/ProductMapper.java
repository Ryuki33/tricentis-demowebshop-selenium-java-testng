package com.wilsonmanaog.automation.utils;

import com.wilsonmanaog.automation.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductMapper {

    private ProductMapper(){}

    @SuppressWarnings("unchecked")
    public static List<Product> fromJson(Map<String, Object> data) {

        List<Product> products = new ArrayList<>();
        List<Map<String, String>> expectedProducts =
                (List<Map<String, String>>) data.get("expectedProducts");

        for (Map<String, String> p : expectedProducts) {
            products.add(new Product(p.get("name"), p.get("price"), p.get("quantity")));
        }

        return products;
    }
}
