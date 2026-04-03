package com.wilsonmanaog.automation.model;

public enum PaymentMethod {
    CASH_ON_DELIVERY("Cash On Delivery", 7.00),
    CHECK_MONEY("Check / Money Order", 5.00),
    CREDIT_CARD("Credit Card", 0.00),
    PURCHASE_ORDER("Purchase Order", 0.00);

    private final String name;
    private final double paymentMethodAdditionalFee;

    PaymentMethod(String name, double paymentMethodAdditionalFee) {
        this.name = name;
        this.paymentMethodAdditionalFee = paymentMethodAdditionalFee;
    }

    public String getName() {
        return name;
    }

    public double getPaymentMethodAdditionalFee() {
        return paymentMethodAdditionalFee;
    }
}
