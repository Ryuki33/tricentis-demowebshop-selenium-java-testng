package com.wilsonmanaog.automation.model;

public enum ShippingMethod {
    GROUND("Ground", 10.00),
    NEXT_DAY_AIR("Next Day Air", 40.00),
    SECOND_DAY_AIR("2nd Day Air", 20.00),
    IN_STORE_PICKUP("In-Store Pickup", 0.00);

    private final String name;
    private final double cost;

    ShippingMethod(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}
