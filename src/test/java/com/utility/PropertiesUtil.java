package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.constants.Env;

public class PropertiesUtil {
	
	public static String readProperty(Env env, String propertyName) {

		File fileProp =new File("./config/"+env+".properties"); 
		FileReader fileReader = null;
		Properties prop=new Properties();
		try {
			fileReader = new FileReader(fileProp);
			prop.load(fileReader);
		} catch (FileNotFoundException e) {
	
			e.printStackTrace();
		}catch (IOException e) {

			e.printStackTrace();
		}
		return prop.getProperty(propertyName.toUpperCase());
	}

}
