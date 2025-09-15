package com.ninza.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.ninza.crm.baseclass.BaseClass;

public class ListenerImplementation implements ITestListener ,ISuiteListener{

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report Back");
	}

	@Override
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName(); 
		
		System.out.println(methodName+"I will hit when test case execution start");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName(); 
		
		System.out.println(methodName+"I will hit when test case execution success start");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName(); 
		System.out.println(methodName+"Execution FAiled"); 
		
		System.out.println("It will hit when test case execution fail start"); 
		Date date = new Date(); 
		String d = date.toString().replace(" ", "_").replace(":", "_"); 
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver; 
		
		File src = ts.getScreenshotAs(OutputType.FILE); 
		File dest = new File("./Screenshots/WorkingWithTakesScreenshot"+d+".png"); 
		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName(); 
		
		System.out.println(methodName+"It will hit when test case execution skipped start");
	}

	
}
