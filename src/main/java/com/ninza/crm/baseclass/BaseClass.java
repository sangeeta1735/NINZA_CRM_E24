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
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseClass {
  
	public WebDriver driver; 
	public PropertyFileUtility pLib = new PropertyFileUtility();
	public static WebDriver sdriver = null; 
	
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Connect to Database");
	}


	@BeforeTest
	public void beforeTest() {
		System.out.println("pre-condition");

	}


	@BeforeClass
	public void beforeClass() throws IOException {
		System.out.println("Launch the browser");
		String browser = pLib.getPropertyValue("browser");
		// Handling pop up in Chrome Browser
		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);

		if (browser.equalsIgnoreCase("CHROME"))
			driver = new ChromeDriver(settings);
		else if (browser.equalsIgnoreCase("Edge"))
			driver = new EdgeDriver();
		else if (browser.equalsIgnoreCase("Firefox"))
			driver = new FirefoxDriver();
		else if (browser.equalsIgnoreCase("Safari"))
			driver = new SafariDriver(); 
			//sdriver = driver
	}

  @BeforeMethod
  public void beforeMethod() throws IOException { 
	  String url = pLib.getPropertyValue("url"); 
	  String username = pLib.getPropertyValue("username"); 
	  String password = pLib.getPropertyValue("password"); 
	      LoginPage lp = new LoginPage(driver); 
	  lp.loginIntoApp(url, username, password); 
	  System.out.println("Login"); 
	  
  }

  @AfterMethod
  public void afterMethod()  { 
	  HomePage hp = new HomePage(driver); 
	  hp.logout(); 
	  System.out.println("Logout"); 
	  
  }

  @AfterClass
  public void afterClass() { 
	  driver.quit(); 
	  System.out.println("Close the browser"); 
	  
  }

  @AfterTest
  public void afterTest() { 
	  System.out.println("Post condition"); 
	  
  }

 
  @AfterSuite
  public void afterSuite() { 
	  System.out.println("Disconnect to Database"); 
	  
  }

}
