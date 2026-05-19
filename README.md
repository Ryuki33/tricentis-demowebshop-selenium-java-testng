**Selenium Test Automation Framework**

A scalable and maintainable UI test automation framework built with Java, Selenium WebDriver, TestNG, and Maven.
This framework follows the Page Object Model (POM) design pattern and is designed for easy test execution, reporting, logging, and future scalability.

**Tech Stack**
 - Java
 - Selenium WebDriver
 - TestNG
 - Maven
 - Log4j2
 - Page Object Model (POM)

**Features**
 - Page Object Model implementation
 - Cross-browser support
 - TestNG test execution
 - Retry Analyzer for flaky tests
 - Logging using Log4j2
 - Maven build support
 - Reusable utility methods
 - Screenshot capture on failures
 - Scalable project structure
 - Easy integration with CI/CD pipelines

**Project Structure**
```
src
├── main
│   └── java
│   |   ├── base
│   |   ├── components
│   |   ├── config
|   |   ├── driver
|   |   ├── model
|   |   ├── pages
│   |   └── utils
|   └── resources
│       └── GlobalData.properties
|
├── test
│   └── java
│   |   ├── base
│   |   ├── listeners
│   |   └── tests
|   └── resources
│       ├── testdata
│       ├── testSuites
│       └── log4j2.xml
|
├── logs
├── reports
|   └── screenshots
└── pom.xml
```
**Design Pattern**

This framework uses the Page Object Model (POM) design pattern to improve:
 - Test maintainability
 - Code readability
 - Reusability
 - Separation of test logic and page logic

**Prerequisites**

Before running the framework, make sure the following are installed:
 - Java JDK 17+ (or your preferred version)
 - Maven
 - Chrome / Firefox / Edge browser
 - IDE such as IntelliJ IDEA or Eclipse IDE
