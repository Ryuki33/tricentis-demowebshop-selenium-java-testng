package com.wilsonmanaog.automation.model;

public class Address {
    private final String country;
    private final String state;
    private final String city;
    private final String address;
    private final String zipCode;
    private final String phoneNumber;

    public Address(String country, String state, String city, String address, String zipCode, String phoneNumber) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
