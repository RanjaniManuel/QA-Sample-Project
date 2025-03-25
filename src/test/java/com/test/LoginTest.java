package com.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pojos.User;
@Listeners({com.listeners.MyTestListener.class})
public class LoginTest extends TestBase {

	/*
	  1.Test script should be simple; 
	  2.Test script should not have while loop, for
	  					loop and condn statement;
	  3.Test script should not have local variable;
	  4.Each Test script have Assertion.
	 */





	@Test(description = "verify with valid user is able to login into the application",enabled = false, groups = { "e2e", "sanity" })
	public void loginTest() {

		/*
		 * homePage.goToWebsit("https://tutorialsninja.com/demo/"); LoginPage loginPage
		 * = homePage.goToLoginPage(); loginPage.doLoginWith("ranjani@gmail.com",
		 * "12345");
		 */

		assertEquals(homePage.goToLoginPage().doLoginWith("ranjani@gmail.com", "12345").getAccountBreadCrumbText(),
				"Account");

	}

	@Test(description = "Verify the test using set of user  is able to login into the application",enabled = true,
			groups = { "e2e","sanity","regression" }, 
			dataProviderClass = com.ui.dataprovider.LoginDataProvider.class, 
			dataProvider = "LoginData")
	public void loginTestJsonDataProvider(User user) {

		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmail(), user.getPassword()).getAccountBreadCrumbText(),
				"Account");

	}
	@Test(description="verify the test using set of user from csv file  is able to login into the application",
			groups = { "e2e","sanity","regression" }, enabled = true,
			dataProviderClass = com.ui.dataprovider.LoginDataProvider.class,
			dataProvider = "loginCSVDataProvider")
	public void loginTestCSVDataProvider(User user) {
		logger.info("Logging test started");
		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmail(), user.getPassword()).getAccountBreadCrumbText(),
				"Account");

	}
	@Test(description="verify the test using set of user from excel file  is able to login into the application",
			groups = { "e2e","sanity","regression" },
			dataProviderClass = com.ui.dataprovider.LoginDataProvider.class,
			dataProvider = "loginExcelDataProvider",
			retryAnalyzer = com.listeners.MyRetryAnalyzer.class
			
			)
	public void loginTestExcekDataProvider(User user) {
		

		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmail(), user.getPassword()).getAccountBreadCrumbText(),
				"Account1","Account is not displayed");



	}
	
}
