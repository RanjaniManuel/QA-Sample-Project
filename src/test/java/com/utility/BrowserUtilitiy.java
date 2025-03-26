package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;

public class BrowserUtilitiy {
	Logger logger = LoggerUtility.getLogger(this.getClass());

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public BrowserUtilitiy(WebDriver driver) {
		super();
		this.driver.set(driver);
	}
	public WebDriver getDriver() {
		if (driver.get()== null) {
			logger.warn("WebDriver instance is null. Ensure the browser is initialized before calling getDriver().");
		} else {
			logger.info("Returning WebDriver instance.");			
		}
		return driver.get();
	}

	public BrowserUtilitiy(String browserName) {
		logger.info("Initializing browser: {}", browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
			logger.info("Chrome browser launched successfully.");
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver.set(new FirefoxDriver());
			logger.info("Firefox browser launched successfully.");
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver.set(new EdgeDriver());
			logger.info("Edge browser launched successfully.");
		} else {
			logger.error("Invalid Browser Name ........");
			System.err.println("Invalid Browser Name ........");
		}
	}

	public BrowserUtilitiy(Browser browserName) {
		logger.info("Initializing browser: {}", browserName);

		if (browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());
			logger.info("Chrome browser launched successfully.");
		} else if (browserName == Browser.FIREFOX) {
			driver.set(new FirefoxDriver());
			logger.info("Firefox browser launched successfully.");
		} else if (browserName == Browser.EDGE) {
			driver.set(new EdgeDriver());
			logger.info("Edge browser launched successfully.");
		} else {
			logger.error("Invalid Browser Name ........");
			System.err.println("Invalid Browser Name ........");
		}
	}

	public BrowserUtilitiy(Browser browserName, boolean isHeadless) {
		logger.info("Initializing browser: {}", browserName);
		if (isHeadless) {
			if (browserName == Browser.CHROME) {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless=new");
				//chromeOptions.addArguments("--disable-gpu"); // Recommended for headless mode
				chromeOptions.addArguments("--window-size=1920,1080"); // Set a default window size
				logger.info("Chrome browser is launching headless mode");
				driver.set(new ChromeDriver(chromeOptions));
				logger.info("launched successfully.");
			} else if (browserName == Browser.FIREFOX) {
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addArguments("--headless");
				
				logger.info("Firefox browser is launching headless mode ");
				driver.set(new FirefoxDriver(firefoxOptions));
				logger.info("Firefox browser launched successfully.");
			} else if (browserName == Browser.EDGE) {
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.addArguments("--headless");
				edgeOptions.addArguments("--disable-gpu"); // Recommended for headless mode
				logger.info("Edge browser is launching headless mode ");
				driver.set(new EdgeDriver(edgeOptions));
				logger.info("Edge browser launched successfully.");
			}
			else {
				logger.error("Invalid Browser Name ........");
				System.err.println("Invalid Browser Name ........");
			}

		} 
		else {
			if (browserName == Browser.CHROME) {
				driver.set(new ChromeDriver());
				logger.info("Chrome browser launched successfully.");
			} else if (browserName == Browser.FIREFOX) {
				driver.set(new FirefoxDriver());
				logger.info("Firefox browser launched successfully.");
			} else if (browserName == Browser.EDGE) {
				driver.set(new EdgeDriver());
				logger.info("Edge browser launched successfully.");
			}

			else {
				logger.error("Invalid Browser Name ........");
				System.err.println("Invalid Browser Name ........");
			}
		}
	}

	

	public void goToWebsit(String url) {
		getDriver().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maximizing the browser window...");
		getDriver().manage().window().maximize();
		logger.info("Browser window maximized successfully.");
	}

	public void clickOn(By locator) {
		logger.info("Finding the element and try to click {}", locator);
		getDriver().findElement(locator).click();
		logger.info("Found and Clicked the element {}", locator);
	}

	public void enterText(By locator, String text) {
		logger.info("Finding the element {} and trying to type the text {}", locator, text);
		getDriver().findElement(locator).sendKeys(text);
	}

	public String getVisibleText(By locator) {
		logger.info("Found the element {} and  got the text {}", locator, driver.get().findElement(locator).getText());
		return getDriver().findElement(locator).getText();

	}

	public String getScreenShot(String fileName) {
		TakesScreenshot takeScreenShot = (TakesScreenshot) getDriver();
		File screenshotAs = takeScreenShot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String tuneSttamp = format.format(date);
		String path =  "./ScreanShots/" + fileName + "-" + tuneSttamp + ".png";
		File targetPath = new File(path);
		try {
			FileUtils.copyFile(screenshotAs, targetPath);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return path;

	}

}
