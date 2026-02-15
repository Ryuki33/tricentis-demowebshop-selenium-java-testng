package com.wilsonmanaog.automation.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVUtils {

    private CSVUtils() {
        // prevent instantiation
    }

    public static Object[][] readCsv(String resourcePath) {
        List<Map<String, String>> rows = new ArrayList<>();

        try {
            InputStream is = CSVUtils.class
                    .getClassLoader()
                    .getResourceAsStream(resourcePath);

            if (is == null) {
                throw new RuntimeException("CSV file not found: " + resourcePath);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String[] headers = reader.readLine().split(",");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",", -1);
                Map<String, String> row = new HashMap<>();

                for (int i = 0; i < headers.length; i++) {
                    row.put(headers[i].trim(), values[i].trim());
                }
                rows.add(row);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read CSV: " + resourcePath, e);
        }

        Object[][] data = new Object[rows.size()][1];
        for (int i = 0; i < rows.size(); i++) {
            data[i][0] = rows.get(i);
        }
        return data;
    }
}
