package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtilitiy;

public class LoginPage extends BrowserUtilitiy {
	
	private static final By EMAIL_TEXTBOX_LOCATOR =By.id("input-email");
	private static final By PASSWORD_TEXTBOX_LOCATOR =By.id("input-password");
	private static final By LOGIN_BTN_LOCATOR =By.xpath("//input[@value='Login']");

	public LoginPage(WebDriver driver) {
		super(driver);

	}
	public MyAccount doLoginWith(String username, String password) {
		enterText(EMAIL_TEXTBOX_LOCATOR, username);
		enterText(PASSWORD_TEXTBOX_LOCATOR, password);
		clickOn(LOGIN_BTN_LOCATOR);
		return new MyAccount(getDriver());
		
	}


	

}
