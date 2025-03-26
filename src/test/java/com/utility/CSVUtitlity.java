package com.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.pojos.User;

public class CSVUtitlity {
	public static Iterator<User> readCSVFile(String fileName) {

		System.out.println("From csv file");
		
		File fileCSV = new File(".//TestData//"+fileName+".csv");
		FileReader fillReader = null;
		CSVReader csvReader;
		List<User> userListToReturn = new ArrayList<>();
		try {
			String[] line;
			fillReader = new FileReader(fileCSV);
			csvReader = new CSVReader(fillReader);
			csvReader.readNext(); // skipping first row of csv file

			while ((line = csvReader.readNext()) != null) {
				userListToReturn.add(new User(line[0], line[1]));
				
			}

		} catch (CsvValidationException | IOException e) {

			e.printStackTrace();
		}
		
		System.out.println("From csv file");
		return userListToReturn.iterator();

	}
}
