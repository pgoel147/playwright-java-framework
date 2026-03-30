package com.prashant.automation.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import java.nio.file.Paths;


public class InternetPage {

	private Page page;
	
	//-- urls-- 
	
	private static final String BASE_URL 		= "https://the-internet.herokuapp.com";
	private static final String DROPDOWN_URL 	= BASE_URL + "/dropdown";
	private static final String CHECKBOX_URL 	= BASE_URL + "/checkboxes"; 
	private static final String ALERT_URL 		= BASE_URL + "/javascript_alerts";
	private static final String TABLES_URL 		= BASE_URL +"/tables";
	
	
	
	// -- Locators-- 
	
	private static final String DROPDOWN 	= "#dropdown";
	private static final String CHECKBOX_1 	= "input[type='checkbox']:nth-of-type(1)";
	private static final String CHECKBOX_2 	= "input[type='checkbox']:nth-of-type(2)";
	private static final String ALERT_BTN   = "button:has-text('Click for JS Alert')";
	private static final String CONFIRM_BTN = "button:has-text('Click for JS Confirm')";
	private static final String RESULT_TXT 	= "#result";
	
	
	// -- Constructor--
	
	public InternetPage(Page page) {
		this.page = page;
	}
	
	// - Screenshots -- 
	
	public void takeScreenshot(String fileName) {
		page.screenshot(new Page.ScreenshotOptions()
		.setPath(Paths.get("screenshots/" + fileName))
		.setFullPage(true));
		System.out.println("Screenshot saved: " + fileName);
	}
	
	// -- DropDown -- 
	
	public void goToDropdown() {
		page.navigate(DROPDOWN_URL);
	}
	
	public void selectDropdownByValue(String Value) {
		page.selectOption(DROPDOWN, new SelectOption().setValue(Value));
		System.out.println("Selected the dropdown: " + Value);
	}
	
	public String getSelctDropdownText() {
		return page.inputValue(DROPDOWN);
	}
	
	// -- checkboxes--
	
	public void goTocheckboxes() {
		page.navigate(CHECKBOX_URL);
		}
				
			public void checkCheckbox1() {
				if (!page.locator(CHECKBOX_1).isChecked()) {
					page.locator(CHECKBOX_1).check();
				}
					System.out.println("Checkbox 1 checked");
	}
	
			public void checkCheckbox2() {
				if(!page.locator(CHECKBOX_2).isChecked()) {
					page.locator(CHECKBOX_2).check();
				}
				System.out.println("Checkbox 2 checked");
			}
	
	public boolean isCheckbox1Checked() {
		return page.locator(CHECKBOX_1).isChecked();
	}
	
	public boolean isCheckbox2Checked() {
		return page.locator(CHECKBOX_2).isChecked();
		
	}
	
	
	// -- Alerts- 
	
	public void goToAlerts() {
		page.navigate(ALERT_URL);
		}
	
	
	public String clickAlertAndAccept() {
		//- Listener 
		final String[] alertMessage = {""};
		page.onDialog(dialog -> { 
			alertMessage[0] = dialog.message();
			dialog.accept();
			System.out.println("Alert Message:" + dialog.message());
		});
	
		page.click(ALERT_BTN);
		page.waitForTimeout(500);
		return alertMessage[0];
	}
		
		public String ClickConfirmAndAccept() {
			final String[] alertMessage = {""};
			page.onDialog(dialog -> {
				alertMessage[0]	= dialog.message();
				dialog.accept();
				System.out.println("Confirm accepted:" + dialog.message());
				});
			page.click(CONFIRM_BTN);
			page.waitForTimeout(500);
			return alertMessage[0];
	}
	
		public String getResultText() {
			return page.locator(RESULT_TXT).innerText();
		}
	
	// - Tables-- 
		
		public void goToTables() {
			page.navigate(TABLES_URL);
		}
	
	public String getCellValues(int row, int col) {
		String locator = "#table1 tbody tr:nth-child(" 
			    + row + ") td:nth-child(" + col + ")";
			return page.locator(locator).first().innerText();
	}
	
}
