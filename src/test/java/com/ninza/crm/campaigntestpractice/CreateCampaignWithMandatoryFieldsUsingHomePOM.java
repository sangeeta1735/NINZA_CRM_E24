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
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;

public class CreateCampaignWithMandatoryFieldsUsingHomePOM {

	
		public static void main(String[] args) throws IOException {
			// TODO Auto-generated method stub

			
				PropertyFileUtility plib = new PropertyFileUtility(); 
				ExcelFileUtility elib = new ExcelFileUtility(); 
				
				WebDriverUtility wlib = new WebDriverUtility(); 
				
				//get the values from property file 
				String BROWSER = plib.getPropertyValue("browser"); 
				String URL = plib.getPropertyValue("url"); 
				String USERNAME = plib.getPropertyValue("username"); 
				String PWD = plib.getPropertyValue("password"); 
				
				//get the value from Excel file 
				
				String compaignName = elib.getDataFromExcelSheet("compaign ", 1, 1); 
				String targetSize = elib.getDataFromExcelSheet("compaign ", 1, 2); 
				
				WebDriver driver = null; 
				
					if (BROWSER.equalsIgnoreCase("edge")) 
					{ 
						driver = new EdgeDriver(); 
						
					} else if (BROWSER.equalsIgnoreCase("chrome") ) 
						{ 
						driver = new ChromeDriver(); 
						
						} else if (BROWSER.equalsIgnoreCase("firefox")) 
							{ 
								driver = new FirefoxDriver();
							} 
					
					LoginPage lp = new LoginPage(driver); 
					lp.loginIntoApp(URL, USERNAME, PWD);
				//Navigating to Compaign Page 
					HomePage hp = new HomePage(driver); 
					hp.getCampaignLink().click(); 
				
					//driver.findElement(By.linkText("Campaigns")); 
				
				//clicking on Create Campaign -- Navigating to Create Compaign Page 
				
				driver.findElement(By.xpath("//span[text()='Create Campaign']")).click(); 
				
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
				
				//Mouse over on User icon 
				WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']")); 
				wlib.mouseHoverOnWebElement(driver, icon); 
				//clicking on Logout 
				driver.findElement(By.xpath("//div[text()='Logout ']")).click(); 
				 

			}


	}


