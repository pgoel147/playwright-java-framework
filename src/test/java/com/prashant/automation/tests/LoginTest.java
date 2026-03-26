package com.prashant.automation.tests;

import com.microsoft.playwright.*;
import com.prashant.automation.pages.LoginPage;
import com.prashant.automation.pages.SecureAreaPage;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

    Playwright playwright;
    Browser browser;
    Page page;
    LoginPage loginPage;
    SecureAreaPage secureAreaPage;

    @BeforeMethod
    public void setUp() {
        // Start browser
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions().setHeadless(false)
        );
        page = browser.newPage();

        // Create LoginPage object
        // From now on we ONLY talk to LoginPage
        // never to page directly
        loginPage = new LoginPage(page);

        System.out.println("Browser opened!");
    }

    @Test
    public void testValidLogin() {
        // Read how clean this is!
        // No locators, no selectors
        // Just plain English actions
        loginPage.loginWith("tomsmith", "SuperSecretPassword!");

        String message = loginPage.getSuccessMessage();
        System.out.println("Message: " + message);

        Assert.assertTrue(
            message.contains("secure area"),
            "Valid login failed!"
        );

        System.out.println("PASSED - Valid login works!");
    }

    @Test
    public void testInvalidLogin() {
        loginPage.loginWith("wronguser", "wrongpassword");

        String message = loginPage.getErrorMessage();
        System.out.println("Message: " + message);

        Assert.assertTrue(
            message.contains("Your username is invalid"),
            "Expected invalid login error!"
        );

        System.out.println("PASSED - Invalid login handled!");
    }

    @Test
    public void testEmptyUsername() {
        loginPage.goToLoginPage();
        loginPage.enterUsername("");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLoginButton();

        String message = loginPage.getErrorMessage();

        Assert.assertTrue(
            message.contains("Your username is invalid"),
            "Expected error for empty username!"
        );

        System.out.println("PASSED - Empty username handled!");
    }

    @Test
    public void testEmptyPassword() {
        loginPage.goToLoginPage();
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();

        String message = loginPage.getErrorMessage();

        Assert.assertTrue(
            message.contains("Your password is invalid"),
            "Expected error for empty password!"
        );

        System.out.println("PASSED - Empty password handled!");
    }
    
    @Test
    public void testLoginAndLogout() {
        // Login first
        loginPage.loginWith("tomsmith", "SuperSecretPassword!");

        // Now we're on secure area page
        SecureAreaPage secureArea = new SecureAreaPage(page);

        // Verify we landed on secure area
        String header = secureArea.getHeaderText();
        Assert.assertTrue(
            header.contains("Secure Area"),
            "Should be on secure area page!"
        );
        System.out.println("On secure area: " + header);

        // Now logout
        secureArea.clickLogout();

        String logoutMsg = secureArea.getFlashMessage();
        Assert.assertTrue(
            logoutMsg.contains("logged out"),
            "Should show logout message!"
            );

        System.out.println("PASSED - Login and logout flow complete!");
    }

    @AfterMethod
    public void tearDown() {
        if (page != null) page.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
        System.out.println("Browser closed!");
    }

    
    
}