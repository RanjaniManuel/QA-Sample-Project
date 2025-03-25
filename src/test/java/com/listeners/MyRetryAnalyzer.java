package com.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Env;
import com.utility.JSONUtitlity;

public class MyRetryAnalyzer implements IRetryAnalyzer {

//	private static final int MAX_NUMBER_OF_ATTEMPTS= Integer.parseInt( PropertiesUtil.readProperty(Env.QA, "MAX_NUMBER_OF_ATTEMPTS"));
	private static final int MAX_NUMBER_OF_ATTEMPTS= JSONUtitlity.readJSON(Env.QA).getMAX_NUMBER_OF_ATTEMPTS();
	int current_Attemps=1;
	@Override
	public boolean retry(ITestResult result) {
		if(current_Attemps<=MAX_NUMBER_OF_ATTEMPTS) {
			current_Attemps++;
			return true;			
		}
		return false;
	}

}
