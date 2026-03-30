package com.prashant.automation.tests;

import com.microsoft.playwright.*;
import com.prashant.automation.pages.InternetPage;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;

public class InternetActionsTest {

	Playwright playwright;
	Browser browser;
	Page page;
	InternetPage internetpage;
	
	
	@BeforeMethod
	public void setUp() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
				.setHeadless(false)
	);
		page = browser.newPage();
		internetpage = new InternetPage(page);
	
	
	// -- Create Screeshot folder if not exists -- 
	
		String projectPath = System.getProperty("user.dir");
		new File(projectPath + "/screenshots").mkdirs();
		System.out.println("Screenshots folder: " 
		    + projectPath + "/screenshots");
	System.out.println("Browser Opened");
	}

	//test screenshot
	@Test
	public void testScreenshot() {
	    page.navigate("https://the-internet.herokuapp.com");

	    // Take screenshot
	    String projectPath = System.getProperty("user.dir");
	    page.screenshot(new com.microsoft.playwright.Page
	        .ScreenshotOptions()
	        .setPath(java.nio.file.Paths.get(
	            projectPath + "/screenshots/homepage.png"))
	        .setFullPage(true));

	    // Verify file exists
	    File screenshot = new File(
	        projectPath + "/screenshots/homepage.png");
	    System.out.println("Looking for screenshot at: " 
	        + screenshot.getAbsolutePath());

	    Assert.assertTrue(
	        screenshot.exists(),
	        "Screenshot file should exist!"
	    );

	    System.out.println("PASSED - Screenshot captured!");
	    
	}
	
	// Dropdown -- 
	@Test 
	public void testDropdown () {
		internetpage.goToDropdown();
		
		// Take Screenshot before selection
		
		internetpage.takeScreenshot("dropdown_before.png");
		
		//select option 2 
		internetpage.selectDropdownByValue("2");
		
		//Take screenshot after selection
		internetpage.takeScreenshot("dropdown_afetr.png");
		
		//verify correct option
		String selected = internetpage.getSelctDropdownText();
		System.out.println("Selected value: " + selected);

		Assert.assertTrue(
		    selected.equals("2") || selected.contains("Option 2"),
		    "Option 2 should be selected!"
		);
		System.out.println("Passed - Dropdown works");
	}
	
	// Checkboxes
	@Test 
	public void testCheckboxes() {
		internetpage.goTocheckboxes();
	
	
	// check both checkboxes
	internetpage.checkCheckbox1();
	internetpage.checkCheckbox2();
	
	// Take screenshot as proof
    internetpage.takeScreenshot("checkboxes.png");

    // Verify both are checked
    Assert.assertTrue(
        internetpage.isCheckbox1Checked(),
        "Checkbox 1 should be checked!"
    );
    Assert.assertTrue(
        internetpage.isCheckbox2Checked(),
        "Checkbox 2 should be checked!"
    );

    System.out.println("PASSED - Checkboxes work!");
	
	}
	
	// ── Test 4: Alerts 
    @Test
    public void testAlert() {
        internetpage.goToAlerts();

        // Handle alert popup
        internetpage.clickAlertAndAccept();

        // Wait for result to appear
        page.waitForTimeout(1000);

        // Get actual result text
        String result = internetpage.getResultText();

        // Print exactly what website returns
        System.out.println("ACTUAL result text: '" + result + "'");

        // Verify result is not empty
        Assert.assertFalse(
            result.isEmpty(),
            "Result text should not be empty!"
        );

        System.out.println("PASSED - Alert handled!");
    }
	
 // ── Test 5: Confirm dialog ────────────────────────
    @Test
    public void testConfirmDialog() {
        internetpage.goToAlerts();

        // Handle confirm popup
        internetpage.ClickConfirmAndAccept();

        // Wait for result to appear
        page.waitForTimeout(1000);

        // Get actual result text
        String result = internetpage.getResultText();

        // Print exactly what website returns
        System.out.println("ACTUAL confirm result: '" + result + "'");

        // Verify result is not empty
        Assert.assertFalse(
            result.isEmpty(),
            "Confirm result should not be empty!"
        );

        // Verify it was accepted not dismissed
        Assert.assertTrue(
            result.contains("Ok") || result.contains("ok") 
            || result.contains("confirm"),
            "Confirm should be accepted!" 
            );

        System.out.println("Passed - Confirm dialog handled");
    }

    // ── Test 6: Table data ────────────────────────────
    @Test
    public void testTableData() {
        internetpage.goToTables();

        // Read first name from row 1
        String firstName = internetpage.getCellValues(1, 2);
        System.out.println("First name in row 1: " + firstName);

        // Read last name from row 1
        String lastName = internetpage.getCellValues(1, 1);
        System.out.println("Last name in row 1: " + lastName);

        // Verify data is not empty
        Assert.assertFalse(
            firstName.isEmpty(),
            "First name should not be empty!"
        );
        Assert.assertFalse(
            lastName.isEmpty(),
            "Last name should not be empty!"
        );

        // Take screenshot of table
        internetpage.takeScreenshot("table_data.png");

        System.out.println("PASSED - Table data read!");
    }

    @AfterMethod
    public void tearDown() {
        if (page != null) page.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
        System.out.println("Browser closed!");
    }
}
