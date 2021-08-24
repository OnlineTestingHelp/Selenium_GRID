package com.qa.orangeHRM.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerMethods implements ITestListener {
		
    public void onFinish(ITestContext arg0) {					
        System.out.println("Class is finished");			
        		
    }		

	
    public void onStart(ITestContext arg0) {					
        System.out.println("Class is started");			
        		
    }		

		
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {					
        // TODO Auto-generated method stub				
        		
    }		
		
    public void onTestFailure(ITestResult arg0) {					
        System.out.println("Test case is failed");			
        		
    }		

		
    public void onTestSkipped(ITestResult arg0) {					
        // TODO Auto-generated method stub				
        		
    }		

		
    public void onTestStart(ITestResult arg0) {					
        System.out.println("Before test start");				
        		
    }		

		
    public void onTestSuccess(ITestResult arg0) {					
        System.out.println("Test case is passed");			
    } 		
}
