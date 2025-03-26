package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pojos.User;

public class ExcelUtitlity {
	
	
	@SuppressWarnings("null")
	public static Iterator<User> readExcelFile(String filename) {
		File file = new File("./TestData/"+filename);
		//File file = new File(System.getProperty("user.dir") + "\\TestData\\loginData.xlsx");

		XSSFSheet loginDetailsSheet;
		Iterator<Row> rows;
		User user;
		List<User> userList=new ArrayList<User>();
		
		Row row;
		Cell email;
		Cell password;
		try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {
			loginDetailsSheet = workbook.getSheet("loginDetails");
			rows = loginDetailsSheet.iterator();
			rows.next(); //exclude column names row 
			while (rows.hasNext()) {
				row = rows.next();
				email = row.getCell(0);
				password = row.getCell(1);
				user=new User(email.toString(),new DataFormatter().formatCellValue(password));
				userList.add(user);
				//System.out.println(email.toString()+"     :   "+new DataFormatter().formatCellValue(password));
			}
		} catch (IOException  | InvalidFormatException e) {

			e.printStackTrace();
		} 
		return userList.iterator();
				

	}
}
