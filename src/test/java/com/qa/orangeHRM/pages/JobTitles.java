package com.qa.orangeHRM.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.qa.orangeHRM.common.base;
import com.qa.orangeHRM.report.Report;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class JobTitles extends Report {

	public static Logger log=LogManager.getLogger(JobTitles.class);
	public WebDriver driver;
	public ExtentTest test;
	public String jobtitleText;

	@FindBy(id="menu_admin_viewAdminModule")
	WebElement AdminMenu;

	@FindBy(id="menu_admin_Job")
	WebElement JobMenu;

	@FindBy(id="menu_admin_viewJobTitleList")
	WebElement JobTitlesMenu;

	@FindBy(name="btnAdd")
	WebElement btnAdd;

	@FindBy(id="jobTitle_jobTitle")
	WebElement JobTitleTxt;

	@FindBy(id="btnSave")
	WebElement SaveBtn;

	@FindBy(id="resultTable")
	WebElement resultTable;

	public JobTitles(WebDriver driver,ExtentTest test) throws IOException {
		setDriver(driver);
		setTest(test);
		setLog(log);
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	//Click on Admin Module
	public void NavigateToJobTitlesPage() {
		AdminMenu.click();
		Actions a =new Actions(driver);
		a.moveToElement(JobMenu).pause(2000).moveToElement(JobTitlesMenu).click().build().perform();
	}

	public void clickOnAddBtn() {
		btnAdd.click();
	}

	public void enterJobTitle(String jobtitleText) {
		this.jobtitleText = jobtitleText;
		JobTitleTxt.sendKeys(jobtitleText);
	}

	public void clickOnSave() {
		SaveBtn.click();
	}

	public void verifyJobTitle() throws Exception {
		try {
			//rows
			int rowcount = resultTable.findElements(By.tagName("tr")).size();
			int colcount = resultTable.findElements(By.tagName("td")).size()/rowcount;

			for(int i=1; i<rowcount;i++) {
				String jobTitle = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+ i +"]/td//a")).getText();

				if(jobTitle.equalsIgnoreCase(jobtitleText)) {
					reportStatus("PASS", "Job Title is created successfully");
					
				}
			}
		}catch(Exception e) {
			reportStatus("FAIL", "Job Title is NOT created");
			throw new Exception("Job Title is not added");
		}
	}
}
