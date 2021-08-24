package com.qa.orangeHRM.pages;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

public class PayGrades extends Report{

	public static Logger log=LogManager.getLogger(JobTitles.class);
	public WebDriver driver;
	public ExtentTest test;

	@FindBy(id="menu_admin_viewAdminModule")
	WebElement AdminMenu;

	@FindBy(id="menu_admin_Job")
	WebElement JobMenu;

	@FindBy(id="menu_admin_viewPayGrades")
	WebElement payGrades;

	@FindBy(name="btnAdd")
	WebElement btnAdd;

	@FindBy(xpath="//*[text()='Add Pay Grade']")
	WebElement AddPayGradePage;

	@FindBy(id="payGrade_name")
	WebElement payGradeName;

	@FindBy(id="btnSave")
	WebElement btnSave;

	@FindBy(xpath="//*[text()='Edit Pay Grade']")
	WebElement editPayGradeHeader;

	@FindBy(id="btnAddCurrency")
	WebElement addCurrency;

	@FindBy(xpath="//*[text()='Add Currency']")
	WebElement AddCurrencyPage;


	@FindBy(id="payGradeCurrency_currencyName")
	WebElement payGradeCurrency;

	@FindBy(id="payGradeCurrency_minSalary")
	WebElement minSalary;

	@FindBy(id="payGradeCurrency_maxSalary")
	WebElement maxSalary;

	@FindBy(id="btnSaveCurrency")
	WebElement btnSaveCurrency;

	@FindBy(xpath="//div[@class='ac_results']/ul/li")
	List<WebElement> currencyTypes;
	
	@FindBy(id="payGradeCurrency_currencyName")
	WebElement currencyType;

	@FindBy(id="payGradeCurrency_minSalary")
	WebElement min_salary;
	
	@FindBy(id="payGradeCurrency_maxSalary")
	WebElement max_salary;
	
	@FindBy(id="btnSaveCurrency")
	WebElement saveCurrency;

	public PayGrades(WebDriver driver,ExtentTest test) throws IOException {
		setDriver(driver);
		setTest(test);
		setLog(log);
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	//Navigate to pay grades page
	public void NavigatetoPayGrades() {
		AdminMenu.click();
		Actions a =new Actions(driver);
		a.moveToElement(JobMenu).pause(2000).moveToElement(payGrades).click().build().perform();
	}

	public void clickOnAddButton() {
		btnAdd.click();
	}

	public void VerifyAddPayGradePage() throws Exception {
		if(AddPayGradePage.isDisplayed()) {
			reportStatus("PASS", "Add Pay Grade Page is opened successfully");
		}else {
			reportStatus("FAIL", "Add Pay Grade Page is NOT opened");
			throw new Exception("Add Pay Grade Page is NOT opened");
		}
	}

	//Enter PayGrade Name
	public void Enter_PayGrade_Name(String payName) {
		payGradeName.sendKeys(payName);
	}

	//Click on Save button
	public void ClickOnSaveBtn() {
		btnSave.click();
	}

	//Verify Edit Pay Grade Page
	public void EditPayGradePage() throws Exception {
		if(editPayGradeHeader.isDisplayed()) {
			reportStatus("PASS", "Edit Pay Grade Page is opened successfully");
		}else {
			reportStatus("FAIL", "Edit Pay Grade Page is NOT opened");
			throw new Exception("Edit Pay Grade Page is NOT opened");
		}
	}

	//Click on Add Currency
	public void add_Currency() {
		addCurrency.click();
	}

	//Verify Add Currency Page
	public void Verify_Add_Currency_Page() throws Exception {
		if(AddCurrencyPage.isDisplayed()) {
			reportStatus("PASS", "Add Currency Page is opened successfully");
		}else {
			reportStatus("FAIL", "Add Currency Page is NOT opened");
			throw new Exception("Add Currency Page is NOT opened");
		}

	}

	//Verify add the currency
	public void addCurrency(String currency) throws IOException {
		
		currencyType.sendKeys(currency);
		
		for(WebElement eachCurrency: currencyTypes) {
			String text = eachCurrency.findElement(By.tagName("strong")).getText();
			
			
			if(text.contains(currency)) {
				eachCurrency.click();
				reportStatus("PASS","Add Currency is selected successfully");
			}
		}
		
	}
	
	public void enter_Minimum_salary(String minSalary) {
		min_salary.sendKeys(minSalary);
	}

	public void enter_Maximum_salary(String maxSalary) {
		max_salary.sendKeys(maxSalary);
	}
	
	//Click on Save Button
	public void click_SaveCurrency() throws IOException {
		saveCurrency.click();
		reportStatus("PASS","Clicked on Save Currency successfully");
	}



}
