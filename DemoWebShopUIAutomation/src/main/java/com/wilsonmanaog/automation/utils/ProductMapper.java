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
        List<Map<String, Object>> expectedProducts =
                (List<Map<String, Object>>) data.get("products");

        for (Map<String, Object> p : expectedProducts) {
            products.add(new Product((String) p.get("name"), (String) p.get("category"), (Double) p.get("price"), (Integer) p.get("quantity")));
        }

        return products;
    }
}
