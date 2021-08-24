package com.qa.orangeHRM.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.qa.orangeHRM.common.base;
import com.qa.orangeHRM.pages.JobTitles;
import com.qa.orangeHRM.pages.LoginPage;
import com.qa.orangeHRM.pages.PayGrades;

public class AddingPayGrades extends base{
	
	LoginPage loginpage;
	PayGrades pg;
	
	@Test
	public void addingpayGrades() throws IOException {
	
	
	try {
		//Login into the application
		loginpage = new LoginPage(driver,test);

		try {
			loginpage.Login();
			loginpage.VerifyHomePage();
		}catch(Exception e) {
			loginpage.reportStatus("FAIL", "User is not logged in");
		}	

		try {	
			//Navigate to Admin --> Job --> Job title
			pg= new PayGrades(driver,test);
			pg.NavigatetoPayGrades();
			pg.clickOnAddButton();
			pg.VerifyAddPayGradePage();
			pg.Enter_PayGrade_Name("Grade12");
			pg.ClickOnSaveBtn();
			pg.EditPayGradePage();
			pg.add_Currency();
			pg.Verify_Add_Currency_Page();
			pg.addCurrency("INR");
			pg.enter_Minimum_salary("10000");
			pg.enter_Maximum_salary("20000");
			pg.click_SaveCurrency();

		}catch(Exception e1) {
			pg.reportStatus("FAIL", "User is unable to add payGrade");
		}

	}catch(Exception e) {
		loginpage.reportStatus("FAIL", "User is either not able to login or unable to add PayGrade");
	}
	}
}
