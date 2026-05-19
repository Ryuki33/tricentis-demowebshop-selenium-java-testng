package com.wilsonmanaog.automation.model;

public class UserData {

    private final String firstName;
    private final String lastName;
    private final String email;

    public UserData(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
