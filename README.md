# Playwright Java Automation Framework

A professional test automation framework built with 
Java, Playwright, TestNG and Maven.

## Tech Stack used
- Java 17
- Playwright 1.44.0
- TestNG 7.8.0
- Maven 3.9.11
- Git / GitHub

## Project Structure
src/test/java/com/prashant/automation/tests/
├── MyFirstTest.java           # Basic TestNG assertions
├── MathOperationsTest.java    # Math operation tests
└── PlaywrightLoginTest.java   # Browser automation tests

## Tests Included
| Test Class | Test Name | Description | Status |
|---|---|---|---|
| MyFirstTest | verifyAddition | Validates 2+2=4 | PASS ✅ |
| MyFirstTest | verifyMyName | Validates string assert | PASS ✅ |
| MathOperationsTest | verifySubtraction | Validates 10-3=7 | PASS ✅ |
| MathOperationsTest | verifyMultiplication | Validates 4x5=20 | PASS ✅ |
| MathOperationsTest | verifyDivision | Validates 20/4=5 | PASS ✅ |
| PlaywrightLoginTest | testLogin | Valid login flow | PASS ✅ |
| PlaywrightLoginTest | testInvalidLogin | Invalid login flow | PASS ✅ |

## How to Run

Clone the repo:
```
git clone https://github.com/pgoel147/playwright-java-framework.git
```

Run all tests:
```
mvn test
```

## What This Framework Demonstrates
- Browser automation with Playwright
- TestNG test structure (@Test, @BeforeMethod, @AfterMethod)
- Maven dependency management
- Positive and negative test scenarios
- Professional Git workflow

## Author
Prashant Goel
QA Automation Engineer | Tosca | Playwright | AWS
https://linkedin.com/in/prashantgoel98

