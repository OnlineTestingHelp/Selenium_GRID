package com.qa.orangeHRM.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.orangeHRM.config.gblConstants;

public class util {

	public static String getProperty(String propertyName) throws IOException {
		
		File file=new File(gblConstants.configFile);
		FileInputStream fis = new FileInputStream(file);
		
		Properties props = new Properties();
		props.load(fis);	
		
		return props.getProperty(propertyName);
		
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
