package com.qa.orangeHRM.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.orangeHRM.common.base;
import com.qa.orangeHRM.report.Report;
import com.qa.orangeHRM.util.util;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage extends Report {
	
	public WebDriver driver;
	public ExtentTest test;
	public Logger log=(Logger) LogManager.getLogger("LoginPage.class");

	@FindBy(id="txtUsername")
	WebElement UserName;
	
	@FindBy(name="txtPassword")
	WebElement Password;
	
	@FindBy(id="btnLogin")
	WebElement Loginbtn;
	
	@FindBy(xpath="//a[contains(text(),'Forgot your password?')]")
	WebElement forgotpassword;
	
	@FindBy(xpath="//input[@id='MP_link']")
	WebElement Marketplace;
	
	@SuppressWarnings("deprecation")
	public LoginPage(WebDriver driver,ExtentTest test) throws IOException {
		setDriver(driver);
		setTest(test);
		setLog(log);
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	//Enter UserName
	public void Login() throws Exception {
		try {
			UserName.sendKeys(util.getProperty("UserName"));
			Password.sendKeys(util.getProperty("Password"));
			Loginbtn.click();
		}
		catch(Exception e) {
			reportStatus("FAIL", "User is not able to login");
			throw new Exception("Credentials are not working");
		}

	}
	
	//Verify Home page is displayed
	public void VerifyHomePage() throws Exception {
		try {
		
				if(Marketplace.isDisplayed()) {
					reportStatus("PASS", "User is logged in successfully");
				}else {
					reportStatus("FAIL", "User is not logged in");
					throw new Exception("User is not logged in");
				}
				
		}
		catch(Exception e) {
			reportStatus("FAIL", "User is not logged in");
			throw new Exception("User is not logged in");
		}
	}
}
