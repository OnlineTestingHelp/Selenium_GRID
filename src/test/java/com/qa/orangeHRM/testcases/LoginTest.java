package com.qa.orangeHRM.testcases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.annotations.Test;


import com.qa.orangeHRM.common.base;
import com.qa.orangeHRM.pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTest extends base {
	Logger log=(Logger) LogManager.getLogger(LoginTest.class);
	
	@Test
	public void VerifyLoginFunctionality() throws IOException {
		
		LoginPage loginpage = new LoginPage(driver,test);
		try {
			loginpage.Login();
			loginpage.VerifyHomePage();
		} catch (Exception e) {
			loginpage.reportStatus("FAIL", "User is not logged in");
		}
		
	}

}
