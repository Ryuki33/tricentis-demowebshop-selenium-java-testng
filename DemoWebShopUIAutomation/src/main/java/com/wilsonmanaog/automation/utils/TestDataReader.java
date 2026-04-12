package com.wilsonmanaog.automation.utils;

import java.io.IOException;

public interface TestDataReader {
        Object[][] read(String fileName) throws IOException;
}
