package com.prashant.automation.tests;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.Test;


public class PlaywrightLoginNegativeTest {
	
    Playwright playwright;
    Browser browser;
    Page page;
    
    @Test
    public void testInvalidLogin() {
        // Go to login page
        page.navigate("https://the-internet.herokuapp.com/login");

        // Enter wrong credentials
        page.fill("#username", "wronguser");
        page.fill("#password", "wrongpassword");

        // Click login
        page.click("button[type='submit']");

        // Verify error message appears
        String errorMessage = page.locator(".flash.error").innerText();
        System.out.println("Error message: " + errorMessage);

        Assert.assertTrue(
            errorMessage.contains("Your username is invalid"),
            "Expected invalid login error message"
        );

        System.out.println("TEST PASSED - Invalid login handled correctly!");
    }
}
