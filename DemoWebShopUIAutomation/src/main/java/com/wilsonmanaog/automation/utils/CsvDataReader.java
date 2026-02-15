package com.wilsonmanaog.automation.utils;

public class CsvDataReader implements TestDataReader {

    @Override
    public Object[][] read(String fileName) {
        return CSVUtils.readCsv(fileName);
    }
}
