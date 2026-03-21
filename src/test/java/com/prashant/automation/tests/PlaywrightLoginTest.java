package com.prashant.automation.tests;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PlaywrightLoginTest {
	
	Playwright playwright;
	Browser browser;
	Page page;
	
	@BeforeMethod
	public void SetUp() {
		//Start Playwright
		playwright = Playwright.create();
		
		//open Chrome browser
		//headless=false means you can see the browser opening
		browser = playwright.chromium().launch( 
				new BrowserType.LaunchOptions().setHeadless(false)
				);
		
		//open a new Tab
		page = browser.newPage() ;
		
		System.out.println("Browser opened successfully!");
	}
	
	@Test 
	public void testLogin() {
		//step 1 - Go to website
		page.navigate("https://the-internet.herokuapp.com/login");
		System.out.println("Navigate to login page");
		
		//Step 2 - Type Username 
		page.fill("#username", "tomsmith");
		System.out.println("Entered username");
		
		//step 3 - Type Password 
		page.fill("#password", "SuperSecretPassword!");
		System.out.println("Enetred Password");
		
		//step 4 - Click on login button
		page.click("button[type='submit']");
		System.out.println("Clicked login button");
		
		//step 5 - Verify success message
		String successMessage = page.locator(".flash.success").innerText();
		System.out.println("Message found: " + successMessage);
		
		Assert.assertTrue(
			    successMessage.contains("You logged into a secure area!") ||
			    successMessage.contains("secure area"),
			    "Login failed - success message not found!"
			);
		
		
		System.out.println("Test Passed - Login successfull");
	}

	@AfterMethod
	public void tearDown() {
		//close browser after test
		if (page != null) page.close();
		if (browser != null) browser.close();
		if (playwright != null) playwright.close();
		System.out.println("Browser Closed!");
	}
}
