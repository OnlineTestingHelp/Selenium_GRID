package com.qa.orangeHRM.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.qa.orangeHRM.common.base;
import com.qa.orangeHRM.pages.EmployeeImport;
import com.qa.orangeHRM.pages.LoginPage;
import com.qa.orangeHRM.pages.PayGrades;

public class EmpFileUpload extends base {
	LoginPage loginpage;
	EmployeeImport ei;
	
	@Test
	public void EmpFileUpload() throws IOException {
	
	
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
			ei= new EmployeeImport(driver,test);
			ei.NavigatetoconfigPage();
			ei.uploadEmpFile("C:\\Users\\lenovo\\Downloads\\importData.csv");
			ei.clickonUploadBtn();
			ei.NavigateToEmpListPage();
			ei.verifyEmployee("1003");
			
		}catch(Exception e1) {
			ei.reportStatus("FAIL", "User is unable to upload File");
		}

	}catch(Exception e) {
		ei.reportStatus("FAIL", "User is either not able to login or unable to upload File");
	}
	}
}
