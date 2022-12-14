# Test Automation Framework

Automating functional testcases for UI and API. This project uses the page object model (POM) design.

### Stack and Libraries

- Java
- Selenium WebDriver
- TestNG
- RestAssured
- Maven
- Extent Reports and logging

### Pre-requisites

- JDK 
- Maven configuration

### How to build and run this project manually?

- Clone the Web-API-TestAutomation repository using the below command

```
git clone https://github.com/Rubinacs/Web-API-TestAutomation.git
```

- Change path to root directory of Web-API-TestAutomation project

```
cd Web-API-TestAutomation
```

- Run the test

```
mvn clean install
```

### Project components

- Page objects are in the directory src/main/java/pages
- Test classes are in the directory src/test/java
- Properties and json are in the directory src/test/resources

### Highlights

- This framework supports Chrome browser
- Screenshot on test failure: A screenshot of the active browser is captured and stored in the screenshots folder 
- Extent reporting and logging: After the test finishes, a visual report is generated for all the executed test cases from the suite. This report can be found in the 'report' folder


#### 
<p align="center">
    <img src="https://github.com/Rubinacs/Web-API-TestAutomation/blob/main/reportcapure.PNG">
</p>


