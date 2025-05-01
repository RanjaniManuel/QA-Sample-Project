package com.test;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.pages.HomePage;
import com.utility.BrowserUtilitiy;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;

public class TestBase {
	protected HomePage homePage;
	protected Logger logger = LoggerUtility.getLogger(this.getClass());
	private boolean isLambdaTest ;


	@Parameters({"browser","isLambdaTest","isHeadless"})
	@BeforeMethod(description = "Load the home page of the website")
	public void setUp( 
			@Optional("chrome") String browser,
			@Optional("false") boolean isLambdaTest,
			@Optional("false") boolean isHeadless,  
			ITestResult result) {
		
		WebDriver driverLambda = null;
		this.isLambdaTest=isHeadless;
		if (isLambdaTest) {
			logger.info("Initializing test setup...");
			driverLambda = LambdaTestUtility.initialiseLambdaTestSession(browser, result.getMethod().getMethodName());
			homePage = new HomePage(driverLambda);
		}

		else {
			logger.info("Initializing test setup...");
			homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);
			logger.info("Test setup completed successfully.");
		}
	}

	public BrowserUtilitiy getInstance() {
		return homePage;
	}

	@AfterMethod
	public void tearDown() {
		if (isLambdaTest) {
			LambdaTestUtility.quitSession();
		} else {
			homePage.getDriver().quit();
		}
	}

}
