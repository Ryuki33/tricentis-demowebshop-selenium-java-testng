package com.wilsonmanaog.automation.utils;

import java.io.IOException;

public class CsvDataReader implements TestDataReader {

    @Override
    public Object[][] read(String fileName) throws IOException {
        return CSVUtils.readCsv(fileName);
    }
}
