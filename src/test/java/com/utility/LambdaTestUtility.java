package com.utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LambdaTestUtility {
	 private static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
	 private static ThreadLocal<WebDriver>  driverLocal=new ThreadLocal<WebDriver>();
	 private static ThreadLocal<DesiredCapabilities>  capabilitiesLocal=new ThreadLocal<DesiredCapabilities>();
	 
	 public static WebDriver initialiseLambdaTestSession(String browser,String testNamee) {
		 DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setCapability("browserName",browser );
	        capabilities.setCapability("browserVersion", "127");
	        Map<String, Object> ltOptions = new HashMap<>();
	        ltOptions.put("user", "ranjanimanuel");
	        ltOptions.put("accessKey", "PRL8ejzJCnt6VLjAw5zgL7UYqCreLinuhAlB4NjRyNWMue3uFm");
	        ltOptions.put("build", "Selenium 4");
	        ltOptions.put("name", testNamee);
	        ltOptions.put("platformName", "Windows 10");
	        ltOptions.put("seCdp", true);
	        ltOptions.put("selenium_version", "4.23.0");
	        capabilities.setCapability("LT:Options", ltOptions);
	        capabilitiesLocal.set(capabilities);

	        try {
				driverLocal.set( new RemoteWebDriver(new URL(HUB_URL), capabilitiesLocal.get()));
			} catch (MalformedURLException e) {
			
				e.printStackTrace();
			}
	        return driverLocal.get();
	 }
	 public static void quitSession() {
		 if(driverLocal.get()!=null)
			 driverLocal.get().quit();
	 }

}
