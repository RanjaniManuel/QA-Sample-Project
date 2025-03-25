package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.constants.Env;
import com.google.gson.Gson;
import com.pojos.Config;
import com.pojos.Environment;

public class JSONUtitlity {
	
	public static Environment readJSON(Env env) {
		Gson gson=new Gson();
		File fileJson=new File(System.getProperty("user.dir")+"\\config\\config.json");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(fileJson);
		} catch (FileNotFoundException e) {


			e.printStackTrace();
		}
		Config config=gson.fromJson(fileReader, Config.class);
		Environment environment=config.getEnvironment().get(env.toString());
		 return environment;
		
		
	}
}
