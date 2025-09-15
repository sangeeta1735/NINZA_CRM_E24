package com.ninza.crm.campaigntestpractice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.generic.webdriverutility.JavaUtility;
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;
import com.ninza.crm.objectrepository.CampaignPage;
import com.ninza.crm.objectrepository.CreateCampaignPage;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;



public class CreateCampaignWithMandatoryFiledsAndStatusUsingPOMTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		PropertyFileUtility plib = new PropertyFileUtility(); 
		ExcelFileUtility elib = new ExcelFileUtility(); 
		JavaUtility jlib = new JavaUtility(); 
		
		WebDriverUtility wlib = new WebDriverUtility(); 
		
		//get the values from property file 
		String BROWSER = plib.getPropertyValue("browser"); 
		String URL = plib.getPropertyValue("url"); 
		String USERNAME = plib.getPropertyValue("username"); 
		String PWD = plib.getPropertyValue("password"); 
		
		//get the value from Excel file 
		
		String compaignName = elib.getDataFromExcelSheet("compaign ", 1, 1); 
		String targetSize = elib.getDataFromExcelSheet("compaign ", 1, 2); 
		String status = elib.getDataFromExcelSheet("compaign ", 1, 3); 
		
		WebDriver driver = null; 
		
			if (BROWSER.equals("edge")) 
			{ 
				driver = new EdgeDriver(); 
				
			} else if (BROWSER.equals("chrome") ) 
				{ 
				driver = new ChromeDriver(); 
				
				} else if (BROWSER.equals("firefox")) 
					{ 
						driver = new FirefoxDriver();
					} 
			 
			LoginPage lp = new LoginPage(driver); 
			lp.loginIntoApp(URL,USERNAME , PWD); 
			
			/* 
			driver.manage().window().maximize(); 
			wlib.waitforPageToLoad(driver); 
			driver.get(URL); 
			
		driver.findElement(By.id("username")).sendKeys(USERNAME); 
		driver.findElement(By.id("inputPassword")).sendKeys(PWD); 
		driver.findElement(By.xpath("//button[@type='submit']")).click(); 
		*/ 
		
		//Navigating to Compaign Page 
		HomePage hp = new HomePage(driver); 
		hp.getCampaignLink().click(); 
		Thread.sleep(6000); 
		
		//driver.findElement(By.linkText("Campaigns")); 
		
		//clicking on Create Campaign -- Navigating to Create Compaign Page using POM Class 
		
		CampaignPage cp = new CampaignPage(driver); 
		cp.getCreateCampaign().click(); 
		 
		//Filling the mandatory fields value and status by using CreateCamapignPage POM Class 
		CreateCampaignPage ccp = new CreateCampaignPage(driver); 
		ccp.getCampName().sendKeys(compaignName); 
		ccp.getTargSize().sendKeys(targetSize); 
		ccp.getCampStatus().sendKeys(status); 
		WebElement expClsDate = ccp.getExpCloseDate(); 
		String reqDate = jlib.getRequireDate(30); 
		
		wlib.passInput(driver, expClsDate, reqDate);
		Thread.sleep(6000); 
		
		ccp.getCreateCampaignButton().click(); 
		
		//fetching toastmsg by using CampaignPage POM Class 
		WebElement toastmsg = hp.getToastMsg(); 
		System.out.println("toastmsg before ExplicitWait ==>"+(toastmsg.getText())); 
		wlib.waitForVisiblityOfElement(driver, toastmsg); 
		System.out.println("toastmsg After ExplicitWait ==>"+(toastmsg.getText()));
		
		String msg = toastmsg.getText(); 
		if (msg.contains(compaignName)) 
		{ 
			System.out.println("Campaign verified and created"); 
		} else 
			System.out.println("Campaign not created");
		
		
		//closing the toast message 
		hp.getCrossBtnToastMsg().click(); 
		Thread.sleep(6000); 
		
		//Mouse over on user-icon 
		WebElement icon = hp.getUserIcon(); 
		wlib.mouseHoverOnWebElement(driver, icon); 
		Thread.sleep(6000); 
		
		//logout from application 
		hp.getLogoutBtn().click(); 
		
		//By Hardcoding WebElement 
		/*  
		//driver.findElement(By.xpath("//span[text()='Create Campaign']")).click(); 
		
		driver.findElement(By.name("campaignName")).sendKeys(compaignName); 
		driver.findElement(By.name("targetSize")).sendKeys(targetSize); 
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click(); 
		
		//
		WebElement element = driver.findElement(By.xpath("//div[@role='alert']")); 
		
		wlib.waitForVisiblityOfElement(driver, element); 
		String msg = element.getText(); 
		System.out.println(msg); 
		
		if (msg.contains(compaignName)) 
		 
			System.out.println("compaign verified and created"); 
		else 
			System.out.println("compaign not created");
		
		//closing the toast message 
		driver.findElement(By.xpath("//button[@aria-label='close']")).click(); 
		
		// 
		 WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']")); 
		 wlib.mouseHoverOnWebElement(driver, icon); 
		 //clicking on Logout 
		 driver.findElement(By.xpath("//div[text()='Logout ']")).click(); 
		 
		*/ 
		
	}
		

}
