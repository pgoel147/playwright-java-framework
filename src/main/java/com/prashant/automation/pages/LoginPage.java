package com.prashant.automation.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

	// The Playwright page object
	private Page page;
	
	// URL of the login page
	private static final String LOGIN_URL = "https://the-internet.herokuapp.com/login";
	
	// - Element Locator -- 
	
	private static final String USERNAME_INPUT = "#username";
	private static final String PASSWORD_INPUT = "#password";
	private static final String LOGIN_Button   = "button[type='submit']";
	private static final String SUCCESS_MSG    = ".flash.success";
	private static final String ERRROR_MSG	   = ".flash.error";
	
	
// -- constructor--
	//Runs when we create login page --
	
	public LoginPage(Page page) {
		this.page = page;
	}

	// -- Action steps /Modules -- 
	
	public void goToLoginPage() {
		page.navigate(LOGIN_URL);
		System.out.println("Navigated to login page");
	}
	
	public void enterUsername(String username) {
		page.fill(USERNAME_INPUT, username);
		System.out.println("Enetred username: " + username );
	}
	
	public void enterPassword(String password) {
		page.fill(PASSWORD_INPUT, password);
		System.out.println("Entered password");
	}
	
	public void clickLoginButton() {
		page.click(LOGIN_Button);
		System.out.println("Clicked Login button");
	}
	
	public String getSuccessMessage() {
		return page.locator(SUCCESS_MSG).innerText();
	}
	
	public String getErrorMessage() {
		return page.locator(ERRROR_MSG).innerText();
	}
	
	// ── Combined Actions ──────────────────────────────
    // Do multiple steps in one call
    // ///'ly Tosca Module that handles full login
	
	public void loginWith(String username, String password) {
		goToLoginPage();
		enterUsername(username);
		enterPassword(password);
		clickLoginButton();
	}
	
}
