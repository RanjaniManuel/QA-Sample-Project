package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtilitiy;

public final class MyAccount extends BrowserUtilitiy {
	
	
	private static final By ACCOUNT_BREADCRUMB=By.linkText("Account");

	public MyAccount(WebDriver driver) {
		super(driver);
		
	}
	public String getAccountBreadCrumbText() {
		return getVisibleText(ACCOUNT_BREADCRUMB);
	}

}
