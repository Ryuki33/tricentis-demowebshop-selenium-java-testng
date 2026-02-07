package com.wilsonmanaog.automation.utils;

import com.wilsonmanaog.automation.config.DataSource;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class GenericDataProvider {

    @DataProvider(name = "csvData")
    public static Object[][] provideCsvData(Method testMethod) {

        DataSource dataSource = testMethod.getAnnotation(DataSource.class);

        if (dataSource == null) {
            throw new RuntimeException(
                    "Missing @DataSource on test method: " + testMethod.getName()
            );
        }

        List<Map<String, String>> rows =
                CSVUtils.readCsv("testdata/"+ dataSource.value());

        Object[][] data = new Object[rows.size()][1];
        for (int i = 0; i < rows.size(); i++) {
            data[i][0] = rows.get(i);
        }

        return data;
    }
}
