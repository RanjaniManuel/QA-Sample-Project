package com.listeners;

import java.util.Arrays;
import com.test.TestBase;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.utility.BrowserUtilitiy;
import com.utility.ExtentReportUtility;
import com.utility.LoggerUtility;

public class MyTestListener implements ITestListener {
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	/*
	 * ExtentSparkReporter extentSparkReporter; ExtentReports extentReports;
	 * ExtentTest extentTest;
	 */
	

	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info(Arrays.toString(result.getMethod().getGroups()));
		//extentTest = extentReports.createTest(result.getMethod().getMethodName());
		ExtentReportUtility.setExtentTest(result.getMethod().getMethodName());
	

	}

	public void onTestSuccess(ITestResult result) {
		logger.info(result.getMethod().getMethodName()+" PASSED");
		ExtentReportUtility.getExtentTest().log(Status.PASS, result.getMethod().getMethodName()+" PASSED");
	}

	public void onTestFailure(ITestResult result) {
		logger.error(result.getMethod().getMethodName()+" FAILED");
		logger.error(result.getThrowable().getMessage());
		ExtentReportUtility.getExtentTest().log(Status.FAIL, result.getMethod().getMethodName()+" FAILED");
		ExtentReportUtility.getExtentTest().log(Status.FAIL, result.getThrowable().getMessage());
		
		
		Object test = result.getInstance();
		BrowserUtilitiy instance = ((TestBase)test).getInstance();
		logger.info("Capturing failed test screenshot...");
		String screenShot = instance.getScreenShot(result.getMethod().getMethodName());
		logger.info("Attaching failed test screenshot in the Extent Report");
		ExtentReportUtility.getExtentTest().addScreenCaptureFromPath(screenShot);
		 		
		
	}

	public void onTestSkipped(ITestResult result) {
		
		logger.warn(result.getMethod().getMethodName()+" SKIPPED");
		ExtentReportUtility.getExtentTest().log(Status.SKIP, result.getMethod().getMethodName()+" SKIPPED");

	}

	public void onStart(ITestContext context) {

		logger.info("Test Suite Started");
		/*
		 * extentSparkReporter =new
		 * ExtentSparkReporter(System.getProperty("user.dir")+"//report.html");
		 * extentReports=new ExtentReports();
		 * extentReports.attachReporter(extentSparkReporter);
		 */
		ExtentReportUtility.setUpSparkReport("report.html");
	}

	public void onFinish(ITestContext context) {
		logger.info("Test Suite Completed");
		ExtentReportUtility.flushExtentReport();


	}

}
