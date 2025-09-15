package com.ninza.crm.baseclass;

import org.testng.annotations.Test;

import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseClass_OLD {

	WebDriver driver;
	PropertyFileUtility pLib = new PropertyFileUtility();

	@BeforeMethod
	public void beforeMethod() throws IOException {
		System.out.println("Login to Ninza Application"); 
		String URL = pLib.getPropertyValue("url"); 
		String USERNAME = pLib.getPropertyValue("username"); 
		String PWD = pLib.getPropertyValue("password"); 
		
		LoginPage lp = new LoginPage(driver);
		lp.loginIntoApp(URL, USERNAME, PWD);
			
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("Logout");
		
		  HomePage hp=new HomePage(driver); 
		  hp.logout();
		 
		
	}

	@BeforeClass
	public void beforeClass() throws IOException {
		System.out.println("Launch the browser");
		String BROWSER = pLib.getPropertyValue("browser");

		// Handling pop up in Chrome Browser
		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);

		if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(settings);
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
	}

	@AfterClass
	public void afterClass() {
		System.out.println("Close the browser");
		driver.quit();

	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("Pre-codiotions for parallel executions");

	}

	@AfterTest
	public void afterTest() {
		System.out.println("Post-codiotions for parallel executions");

	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Connect to Database");

	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("Disconnect to Database");

	}

}
