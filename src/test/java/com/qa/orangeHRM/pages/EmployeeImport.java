package com.qa.orangeHRM.pages;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.orangeHRM.report.Report;
import com.relevantcodes.extentreports.ExtentTest;

public class EmployeeImport extends Report{
	public static Logger log=LogManager.getLogger(JobTitles.class);
	public WebDriver driver;
	public ExtentTest test;
	
	@FindBy(id="menu_pim_viewPimModule")
	WebElement PIM;
	
	@FindBy(id="menu_pim_Configuration")
	WebElement config;
	
	@FindBy(id="menu_admin_pimCsvImport")
	WebElement dataImport;
	
	@FindBy(id="pimCsvImport_csvFile")
	WebElement btnFileUpload;
	
	@FindBy(id="btnSave")
	WebElement btnSave;
	
	@FindBy(id="menu_pim_viewEmployeeList")
	WebElement EmpList;
	
	@FindBy(id="resultTable")
	WebElement Emptable;
	
	public EmployeeImport(WebDriver driver,ExtentTest test) throws IOException {
		setDriver(driver);
		setTest(test);
		setLog(log);
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	//Navigate to configuration page and click on data import
	public void NavigatetoconfigPage() {
		PIM.click();
		Actions a =new Actions(driver);
		a.moveToElement(config).pause(2000).moveToElement(dataImport).click().build().perform();
	}
	
	//Upload the file
	public void uploadEmpFile(String filePath) {
		btnFileUpload.sendKeys(filePath);
	}
	
	//Save button
	public void clickonUploadBtn() {
		btnSave.click();
	}
	
	//Navigate to EmpList page
	public void NavigateToEmpListPage() {
		EmpList.click();
	}
	
	public void verifyEmployee(String ExpectedEmpID) throws Exception {
		try {
			//rows
			int rowcount = Emptable.findElements(By.tagName("tr")).size();
			int colcount = Emptable.findElements(By.tagName("td")).size()/rowcount;

			for(int i=1; i<rowcount;i++) {
				String AcutalEmpID = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+ i +"]/td//a")).getText();

				if(AcutalEmpID.equalsIgnoreCase(ExpectedEmpID)) {
					reportStatus("PASS", "Emp is added successfully");
					
				}
			}
		}catch(Exception e) {
			reportStatus("FAIL", "Emp is NOT created");
			throw new Exception("Emp is not added");
		}
	}
	
}
