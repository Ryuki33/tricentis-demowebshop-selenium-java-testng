package com.wilsonmanaog.automation.model;

public class UserRegistration {
    private final String gender;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    public UserRegistration(String gender, String firstName, String lastName, String email, String password) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getGender() {
        return gender;
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

    public String getPassword() {
        return password;
    }
}
