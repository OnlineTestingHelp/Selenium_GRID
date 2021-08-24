package com.qa.orangeHRM.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.orangeHRM.util.util;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;



public class base {


	public WebDriver driver;
	Logger log = LogManager.getLogger(base.class);
	public ExtentTest test;
	public static ExtentReports report;
	public String hub_URL;
	
	@BeforeMethod
	@Parameters(value = {"BrowserName"})
	public void beforeMethod(String browser, Method method) throws IOException {
		
		test = report.startTest(method.getName());
		
//		System.out.println("Browser name is "+ browser);
//		System.out.println("Wait time is "+ wait);
		log.info("Before Method is started");
		if(browser.contains("Chrome")) {
			log.info("Executing on browser "+ browser);
			//System.setProperty("webdriver.chrome.driver", "F:\\Selenium-Batch-58\\Soft&Jars\\chromedriver_win32\\chromedriver.exe");
			
			//WebDriverManager.chromedriver().setup();
			
			
			hub_URL = util.getProperty("HUB_URL");
			
			DesiredCapabilities capability = new DesiredCapabilities();
			capability.setBrowserName(browser.toLowerCase());
			capability.setPlatform(Platform.WINDOWS);

			//driver= new ChromeDriver();
			driver = new RemoteWebDriver(new URL(hub_URL),capability);
			
			
			
			
			//Need to create the object for chrome
			//driver= new ChromeDriver();
			driver.get(util.getProperty("URL"));
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			log.info("Browser is launched successfully");
			System.out.println("Title is "+ driver.getTitle());
		}else if(browser.contains("Firefox")) {
			log.info("Executing on browser "+ browser);
			System.setProperty("webdriver.gecko.driver", "F:\\Selenium-Batch-58\\Soft&Jars\\geckodriver-v0.29.0-win64\\geckodriver.exe");
			
			//Need to create the object for chrome
			driver= new FirefoxDriver();
			driver.get(util.getProperty("URL"));
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			System.out.println("Title is "+ driver.getTitle());
		}
		System.out.println("BeforeMethod - Test report inititalization");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("AfterMethod - Test Report will be closed");
		driver.quit();
		report.endTest(test);
	}
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("BeforeClass - Parent test report will be initialized");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("AfterClass - Parent test report will be closed");
	}
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("BeforeTest: Common code required will be executed");
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("AfterTest: closing all the connections");
	}
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("BeforeSuite: All initialization happen here");
		
		report = new ExtentReports("ExtentReports.html");
	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("AfterSuite: All connections will be closed here");
		
		report.flush();
	}
	
	public static String capture(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File("src/../Images/" + System.currentTimeMillis()
		+ ".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;
		}

	
}
