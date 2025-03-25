package com.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import com.constants.Env;
import com.utility.BrowserUtilitiy;
import com.utility.JSONUtitlity;
import com.utility.LoggerUtility;

public final class HomePage extends BrowserUtilitiy {
	Logger logger=LoggerUtility.getLogger(this.getClass());
	
	
	public HomePage(WebDriver driverLambda) {
		super(driverLambda);
		//goToWebsit(PropertiesUtil.readProperty(Env.QA, "URL"));
		 logger.info("Navigating to website: {}",JSONUtitlity.readJSON(Env.QA).getUrl() );
		goToWebsit(JSONUtitlity.readJSON(Env.QA).getUrl());
	
	
	}
	
	
	public HomePage(Browser browserName,boolean headless) {
		super(browserName,headless);
		//goToWebsit(PropertiesUtil.readProperty(Env.QA, "URL"));
		 logger.info("Navigating to website: {}",JSONUtitlity.readJSON(Env.QA).getUrl() );
		goToWebsit(JSONUtitlity.readJSON(Env.QA).getUrl());
	
	
	}

	private static final By MY_ACCOUNT_LINK_lOCATOR =By.linkText("My Account");
	private static final By LOGIN_LINK_LOCATOR =By.linkText("Login");
	
	public LoginPage goToLoginPage() {
		
		maximizeWindow();
		clickOn(MY_ACCOUNT_LINK_lOCATOR);
		clickOn(LOGIN_LINK_LOCATOR);
		return new LoginPage(getDriver());
	}

}
