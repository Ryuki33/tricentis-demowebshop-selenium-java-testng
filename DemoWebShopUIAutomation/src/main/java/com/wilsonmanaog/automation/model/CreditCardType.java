package com.wilsonmanaog.automation.model;

public enum CreditCardType {
    VISA("Visa"),
    MASTERCARD("Master card"),
    DISCOVER("Discover"),
    AMEX("Amex");

    private final String name;

    CreditCardType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
