package com.wilsonmanaog.automation.utils;

import com.wilsonmanaog.automation.model.UserData;
import net.datafaker.Faker;

public class UserGenerator {

    private static final Faker faker = new Faker();

    public static UserData generateRandomUser() {

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        String email =
                firstName.toLowerCase()
                        + "."
                        + lastName.toLowerCase()
                        + System.currentTimeMillis()
                        + "@testmail.com";

        return new UserData(firstName, lastName, email);
    }
}
