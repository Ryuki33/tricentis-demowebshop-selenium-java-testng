package com.wilsonmanaog.automation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    private JsonUtils() {
        // prevent instantiation
    }

    public static Object[][] readJson(String resourcePath) {
        ObjectMapper mapper = null;

        try {
            mapper = new ObjectMapper();

            InputStream is = JsonUtils.class
                    .getClassLoader()
                    .getResourceAsStream(resourcePath);

            if (is == null) {
                throw new RuntimeException("JSON file not found: " + resourcePath);
            }

            // convert JSON → List<Map<String,Object>>
            List<Map<String, Object>> data =
                    mapper.readValue(is, List.class);

            // convert to Object[][] for TestNG
            Object[][] testData = new Object[data.size()][1];

            for (int i = 0; i < data.size(); i++) {
                testData[i][0] = data.get(i);
            }

            return testData;

        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON: " + resourcePath, e);
        } finally {
            if (mapper != null) {
                mapper = null; // help GC
            }
        }
    }
}
