package com.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtility {
	private static ExtentReports extentReports;
	private static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	
	public static void setUpSparkReport(String fileName) {
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("./"+fileName);
		extentReports=new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
	}
	public static void setExtentTest(String testName) {
		ExtentTest test=extentReports.createTest(testName);
		extentTest.set(test);
	}
	public static ExtentTest getExtentTest() {
		return extentTest.get();
	}
	public static void flushExtentReport() {
		extentReports.flush();
	}

}
