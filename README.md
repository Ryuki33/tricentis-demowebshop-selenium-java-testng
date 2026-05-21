**Selenium Test Automation Framework**

A scalable and maintainable UI test automation framework built with Java, Selenium WebDriver, TestNG, and Maven.
This framework follows the Page Object Model (POM) design pattern and is designed for easy test execution, reporting, logging, and future scalability.


**Tech Stack**
 - Java
 - Selenium WebDriver
 - TestNG
 - Maven
 - Log4j2
 - Extent Report
 - Page Object Model (POM)


**Features**
 - Page Object Model implementation
 - Cross-browser support
 - TestNG test execution
 - Retry Analyzer for flaky tests
 - Logging using Log4j2
 - Maven build support
 - Reusable utility methods
 - Test Reporting using Extent Reports
 - Screenshot capture on failures
 - Scalable project structure
 - CI/CD integration with Github Actions and Jenkins


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


**Installation**

Clone the repository:
git clone your-repository-url

Navigate to the project directory:
cd your-project-name

Install dependencies:
mvn clean install


**Running Tests**

Run all tests
mvn test

Run using TestNG XML defined in pom.xml Profile
mvn test -P profile-name

Run specific test class
mvn test -Dtest=test-class-name


**Logging**

This framework uses Log4j2 for logging.

Log files are automatically generated inside the /logs directory.

Supported log levels:
 - INFO
 - DEBUG
 - WARN
 - ERROR


**Retry Mechanism**

A custom Retry Analyzer is implemented to automatically rerun failed tests caused by temporary issues such as:
 - Slow page loads
 - Timing issues
 - Intermittent UI failures


**Reporting**

Test execution results can be found in reports folder.

This includes:
 - Extent Reports
 - Failed test screenshots


**Configuration**

Framework configuration can be modified inside src/main/resources/GlobalData.properties

Proprties:
browser=chrome
baseUrl=https://demowebshop.tricentis.com


**Best Practices Used**
- Explicit waits over Thread.sleep()
- Reusable utility methods
- Centralized driver management
- Clean separation of concerns
- Parameterized configuration
- Maintainable locators
- Logging and debugging support


**Why This Framework?**
This framework was built to demonstrate:
- Real-world automation framework design
- Maintainable automation architecture
- Java and Selenium automation skills
- TestNG implementation knowledge
- Automation best practices used in enterprise projects


**Author**
Developed by Wilson Manaog.

