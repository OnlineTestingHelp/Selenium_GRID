package com.qa.orangeHRM.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.qa.orangeHRM.common.base;
import com.qa.orangeHRM.pages.JobTitles;
import com.qa.orangeHRM.pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;

public class AddingJobTitleTest extends base {

	@Test
	public void VerifyAddingJobTitle() throws IOException {
		LoginPage loginpage = new LoginPage(driver,test);

		try {
			//Login into the application

			try {
				loginpage.Login();
				loginpage.VerifyHomePage();
			}catch(Exception e) {
				loginpage.reportStatus("FAIL", "User is not logged in");
			}	

			try {	
				//Navigate to Admin --> Job --> Job title
				JobTitles jt = new JobTitles(driver,test);
				jt.NavigateToJobTitlesPage();
				jt.clickOnAddBtn();
				jt.enterJobTitle("Senior Developer3");
				jt.clickOnSave();
				jt.verifyJobTitle();
			}catch(Exception e1) {
				loginpage.reportStatus("FAIL", "User is unable to add Job Title");
			}
		
	}catch(Exception e) {
		loginpage.reportStatus("FAIL", "User is either not able to login or unable to add Job Title");
	}
}
}
