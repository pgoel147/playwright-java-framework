package com.prashant.automation.pages;

import com.microsoft.playwright.Page;

public class SecureAreaPage {

    private Page page;

    // Locators
    private static final String HEADER     = "h2";
    private static final String LOGOUT_BTN = "a[href='/logout']";
    private static final String FLASH_MSG  = ".flash";

    public SecureAreaPage(Page page) {
        this.page = page;
    }

    public String getHeaderText() {
        return page.locator(HEADER).innerText();
    }

    public void clickLogout() {
        page.click(LOGOUT_BTN);
        System.out.println("Clicked logout button");
    }

    public String getFlashMessage() {
        return page.locator(FLASH_MSG).innerText();
    }

}
