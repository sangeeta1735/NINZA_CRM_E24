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
	import com.ninza.crm.objectrepository.CampaignPage;
	import com.ninza.crm.objectrepository.CreateCampaignPage;
	import com.ninza.crm.objectrepository.HomePage;
	import com.ninza.crm.objectrepository.LoginPage;
	
	public class CreateCampaignWithMAndataryFieldsUsingPOMObject {
	
		public static void main(String[] args) throws IOException, InterruptedException {
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
					lp.loginIntoApp(URL, USERNAME, PWD);
				//Navigating to Compaign Page 
					HomePage hp = new HomePage(driver); 
					hp.getCampaignLink().click(); 
					Thread.sleep(6000); 
					
					//driver.findElement(By.linkText("Campaigns")); 
				
				/*Hardcoding of WebElement to navigate to Create Campaign page by clicking on "Create Campaign" link in Campaign Page 
				//clicking on Create Campaign -- Navigating to Create Compaign Page 
				
				//driver.findElement(By.xpath("//span[text()='Create Campaign']")).click(); 
				*/ 
				
				//Navigation to Create Campaign Page by clicking on "Create Campaign" link in Campaign Page using POM class 
				CampaignPage cp = new CampaignPage(driver); 
				cp.getCreateCampaign().click(); 
				Thread.sleep(6000); 
				
				//Filling the date fetched from Excel sheet by hardcoding WebElement 
				/* 
				driver.findElement(By.name("campaignName")).sendKeys(compaignName); 
				driver.findElement(By.name("targetSize")).sendKeys(targetSize); 
				driver.findElement(By.xpath("//button[text()='Create Campaign']")).click(); 
				*/ 
				
				//Filling the date fetched from Excel sheet by using POM class 
				CreateCampaignPage ccp = new CreateCampaignPage(driver); 
				ccp.getCampName().sendKeys(compaignName); 
				ccp.getTargSize().sendKeys(targetSize); 
				ccp.getCreateCampaignButton().click(); 
				
				//Capturing toastMsg 
				//WebElement element = driver.findElement(By.xpath("//div[@role='alert']")); 
				WebElement element = hp.getToastMsg(); 
				wlib.waitForVisiblityOfElement(driver, element); 
				
				String msg = element.getText(); 
				 
				
				System.out.println("toastmsg =====> "+msg); 
				
				 
				 
				System.out.println(msg); 
				
				if (msg.contains(compaignName)) 
				 
					System.out.println("compaign verified and created"); 
				else 
					System.out.println("compaign not created");
				
				//closing the toast message 
				hp.getCrossBtnToastMsg().click(); 
				
				//driver.findElement(By.xpath("//button[@aria-label='close']")).click(); 
				
				//Mouse over on User icon 
				//WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']")); 
				WebElement icon = hp.getUserIcon(); 
				
				wlib.mouseHoverOnWebElement(driver, icon); 
				//clicking on Logout 
				hp.getLogoutBtn().click(); 
				
				//driver.findElement(By.xpath("//div[text()='Logout ']")).click(); 
				 
	
			}
	
	
		}
	
	
