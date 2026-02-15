package com.wilsonmanaog.automation.model;

public class Product {
    private final String name;
    private final String price;
    private final String quantity;

    public Product(String name, String price, String quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }
}
