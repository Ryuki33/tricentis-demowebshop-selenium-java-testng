package com.wilsonmanaog.automation.utils;

import com.wilsonmanaog.automation.config.DataSource;
import org.testng.annotations.DataProvider;
import java.lang.reflect.Method;

public class GenericDataProvider {

    @DataProvider(name = "dataProvider")
    public static Object[][] provideData(Method testMethod) {

        DataSource dataSource = testMethod.getAnnotation(DataSource.class);
        TestDataReader testDataReader;

        if (dataSource == null) {
            throw new RuntimeException(
                    "Missing @DataSource on test method: " + testMethod.getName()
            );
        }

        if (dataSource.value().endsWith(".csv")) {
            testDataReader = new CsvDataReader();
            return testDataReader.read("testdata/"+ dataSource.value());
        } else if (dataSource.value().endsWith(".json")) {
            testDataReader = new JsonDataReader();
            return testDataReader.read("testdata/"+ dataSource.value());
        } else {
            throw new RuntimeException(
                    "Unsupported data source format: " + dataSource.value()
            );
        }
    }
}
