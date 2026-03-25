# Playwright Java Automation Framework

A professional test automation framework built with
Java, Playwright, TestNG and Maven using 
Page Object Model (POM) design pattern.

## Tech Stack
- Java 17
- Playwright 1.44.0
- TestNG 7.8.0
- Maven 3.9.11
- Git / GitHub
- Design Pattern: Page Object Model (POM)

## Project Structure
```
src/
├── main/java/com/prashant/automation/
│   └── pages/
│       └── LoginPage.java        # Page Object - login actions
│
└── test/java/com/prashant/automation/
    └── tests/
        ├── MyFirstTest.java           # Basic TestNG tests
        ├── MathOperationsTest.java    # Math operation tests
        ├── PlaywrightLoginTest.java   # Direct Playwright tests
        └── LoginTest.java             # POM based login tests
```

## Tests Included
| Test Class | Test Name | Description | Status |
|---|---|---|---|
| MyFirstTest | verifyAddition | Validates addition | PASS ✅ |
| MyFirstTest | verifyMyName | Validates string | PASS ✅ |
| MathOperationsTest | verifySubtraction | Validates 10-3=7 | PASS ✅ |
| MathOperationsTest | verifyMultiplication | Validates 4x5=20 | PASS ✅ |
| MathOperationsTest | verifyDivision | Validates 20/4=5 | PASS ✅ |
| PlaywrightLoginTest | testLogin | Valid login flow | PASS ✅ |
| PlaywrightLoginTest | testInvalidLogin | Invalid login | PASS ✅ |
| LoginTest | testValidLogin | POM valid login | PASS ✅ |
| LoginTest | testInvalidLogin | POM invalid login | PASS ✅ |
| LoginTest | testEmptyUsername | Empty username | PASS ✅ |
| LoginTest | testEmptyPassword | Empty password | PASS ✅ |

## Design Pattern — Page Object Model
```
Without POM:                    With POM:
──────────────────────────      ──────────────────────
page.fill("#username",..)  →    loginPage.loginWith()
page.fill("#password",..)  →    (one clean line)
page.click("button")       →    
```

## How to Run

Clone the repo:
```
git clone https://github.com/pgoel147/playwright-java-framework.git
```

Install Playwright browsers:
```
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

Run all tests:
```
mvn test
```

## Author
Prashant Goel
QA Automation Engineer | Tosca | Playwright | AWS
https://linkedin.com/in/prashantgoel98