package com.ui.dataprovider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.pojos.Data;
import com.pojos.User;
import com.utility.CSVUtitlity;
import com.utility.ExcelUtitlity;

public class LoginDataProvider {
	@DataProvider(name = "LoginData")
	public Iterator<Object[]> loginData() {
		Gson gson = new Gson();
		File fileJson = new File(System.getProperty("user.dir") + "\\TestData\\LoginData.json");
		FileReader jsonFileReader = null;
		List<Object[]> dataToReturn = null;
		try {
			jsonFileReader = new FileReader(fileJson);

			Data data = gson.fromJson(jsonFileReader, Data.class);// deserialization
			dataToReturn = new ArrayList<Object[]>();
			for (User user : data.getData()) {
				dataToReturn.add(new Object[] { user });
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return dataToReturn.iterator();
	}
	
	
	@DataProvider(name="loginCSVDataProvider")
	public Iterator<User> loginCSVDataProvider() {

		System.out.println("From Data Provider");
		return CSVUtitlity.readCSVFile("loginnData");
	}
	@DataProvider(name="loginExcelDataProvider")
	public Iterator<User> loginExcelDataProvider() {
		
		return ExcelUtitlity.readExcelFile("loginData.xlsx");
	}
}
