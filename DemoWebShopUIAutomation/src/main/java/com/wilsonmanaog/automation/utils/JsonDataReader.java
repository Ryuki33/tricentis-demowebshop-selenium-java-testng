package com.wilsonmanaog.automation.utils;

public class JsonDataReader implements TestDataReader {

    @Override
    public Object[][] read(String fileName) {
        return JsonUtils.readJson(fileName);
    }
}