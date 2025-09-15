
package com.ninza.crm.campaigntestpractice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import org.testng.annotations.Test;

import com.ninza.crm.baseclass.BaseClass;
import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.generic.webdriverutility.JavaUtility;
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;
import com.ninza.crm.objectrepository.CampaignPage;
import com.ninza.crm.objectrepository.CreateCampaignPage;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;

//@Listeners(com.ninza.crm.listenerutility.ListenerImplementation.class)
public class CreateCampaign_OLDTest {
	WebDriver driver;

	@Test(groups = "Smoke")
	public void createCampaignWithMandatoryFields() throws IOException, InterruptedException {

		ExcelFileUtility elib = new ExcelFileUtility();
		WebDriverUtility wlib = new WebDriverUtility();
		PropertyFileUtility pLib = new PropertyFileUtility();

		// get the values from property file

		// Moved this code piece to BaseClass

		String BROWSER = pLib.getPropertyValue("browser");
		String URL = pLib.getPropertyValue("url");
		String USERNAME = pLib.getPropertyValue("username");
		String PWD = pLib.getPropertyValue("password");

		// get the value from Excel file

		String compaignName = elib.getDataFromExcelSheet("campaign", 1, 1);
		String targetSize = elib.getDataFromExcelSheet("campaign", 1, 2);

		// Moved this code piece to BaseClass - beforeClass()

		WebDriver driver = null;
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

		// Moved this code piece to BaseClass - beforeMethod()

		LoginPage lp = new LoginPage(driver);
		lp.loginIntoApp(URL, USERNAME, PWD); //
		Thread.sleep(6000);

		// Navigating to Compaign Page
		HomePage hp = new HomePage(driver);
		hp.getCampaignLink().click();
		// Navigation to Create Campaign Page by clicking on "Create Campaign" link in
		// Campaign Page using POM class
		CampaignPage cp = new CampaignPage(driver);
		cp.getCreateCampaign().click();
		Thread.sleep(6000);

		// Filling the date fetched from Excel sheet by using POM class
		CreateCampaignPage ccp = new CreateCampaignPage(driver);
		ccp.getCampName().sendKeys(compaignName);
		ccp.getTargSize().sendKeys(targetSize);
		ccp.getCreateCampaignButton().click();

		// Capturing toastMsg
		WebElement element = hp.getToastMsg();

		wlib.waitForVisiblityOfElement(driver, element);

		String msg = element.getText();

		System.out.println("toastmsg =====> " + msg);

		System.out.println(msg);

		if (msg.contains(compaignName))

			System.out.println("compaign verified and created");
		else
			System.out.println("compaign not created");
		hp.getCrossBtnToastMsg().click();

		// Mouse over on User icon
		WebElement icon = hp.getUserIcon();
		wlib.mouseHoverOnWebElement(driver, icon);
		hp.getLogoutBtn().click();

		// hp.logout();

		// clicking on Logout

	}

	@Test(groups = { "Smoke", "Regression" })
	public void createCampaignWithExpectedDate() throws InterruptedException, IOException {
		PropertyFileUtility plib = new PropertyFileUtility();
		ExcelFileUtility elib = new ExcelFileUtility();

		WebDriverUtility wlib = new WebDriverUtility();
		JavaUtility jLib = new JavaUtility();

		// get the values from property file
		String BROWSER = plib.getPropertyValue("browser");
		String URL = plib.getPropertyValue("url");
		String USERNAME = plib.getPropertyValue("username");
		String PWD = plib.getPropertyValue("password");

		// get the value from Excel file

		String compaignName = elib.getDataFromExcelSheet("campaign", 1, 1);
		String targetSize = elib.getDataFromExcelSheet("campaign", 1, 2);

		WebDriver driver = null;

		// Handling pop up in Chrome Browser
		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);

		if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();

		} else if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver(settings);

		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		LoginPage lp = new LoginPage(driver);
		lp.loginIntoApp(URL, USERNAME, PWD);
		// Navigating to Compaign Page
		HomePage hp = new HomePage(driver);
		hp.getCampaignLink().click();
		Thread.sleep(6000);
		// Navigation to Create Campaign Page by clicking on "Create Campaign" link in
		// Campaign Page using POM class
		CampaignPage cp = new CampaignPage(driver);
		cp.getCreateCampaign().click();
		Thread.sleep(6000);

		// Filling the date fetched from Excel sheet by using POM class
		CreateCampaignPage ccp = new CreateCampaignPage(driver);
		ccp.getCampName().sendKeys(compaignName);
		ccp.getTargSize().sendKeys(targetSize);

		WebElement expClsDate = ccp.getExpCloseDate();
		String reqDate = jLib.getRequireDate(30);

		Actions act = new Actions(driver);
		act.click(expClsDate).sendKeys(reqDate).perform();
		Thread.sleep(2000);

		/*
		 * public void passInput(WebDriver driver, WebElement element,String text) {
		 * Actions act = new Actions(driver);
		 * act.click(element).sendKeys(text).perform();
		 * 
		 */

		// wlib.passInput(driver, expClsDate, reqDate);
		Thread.sleep(6000);

		ccp.getCreateCampaignButton().click();

		// Capturing toastMsg
		WebElement element = hp.getToastMsg();

		wlib.waitForVisiblityOfElement(driver, element);

		String msg = element.getText();

		System.out.println("toastmsg =====> " + msg);

		System.out.println(msg);

		if (msg.contains(compaignName))

			System.out.println("compaign verified and created");
		else
			System.out.println("compaign not created");
		hp.getCrossBtnToastMsg().click();

		// Mouse over on User icon
		WebElement icon = hp.getUserIcon();
		wlib.mouseHoverOnWebElement(driver, icon);
		// clicking on Logout
		hp.getLogoutBtn().click();
	}

	@Test(groups = "Regression")
	public void createCampaignWithMandatoryAndStatus() throws InterruptedException, IOException {
		PropertyFileUtility plib = new PropertyFileUtility();
		ExcelFileUtility elib = new ExcelFileUtility();
		JavaUtility jlib = new JavaUtility();

		WebDriverUtility wlib = new WebDriverUtility();

		// get the values from property file
		String BROWSER = plib.getPropertyValue("browser");
		String URL = plib.getPropertyValue("url");
		String USERNAME = plib.getPropertyValue("username");
		String PWD = plib.getPropertyValue("password");

		// get the value from Excel file

		String compaignName = elib.getDataFromExcelSheet("campaign", 1, 1);
		String targetSize = elib.getDataFromExcelSheet("campaign", 1, 2);
		String status = elib.getDataFromExcelSheet("campaign", 1, 3);

		WebDriver driver = null;

		// Handling pop up in Chrome Browser
		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);

		if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();

		} else if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver(settings);

		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		LoginPage lp = new LoginPage(driver);
		lp.loginIntoApp(URL, USERNAME, PWD);

		// Navigating to Compaign Page
		HomePage hp = new HomePage(driver);
		hp.getCampaignLink().click();
		Thread.sleep(6000);
		// clicking on Create Campaign -- Navigating to Create Compaign Page using POM
		// Class

		CampaignPage cp = new CampaignPage(driver);
		cp.getCreateCampaign().click();

		// Filling the mandatory fields value and status by using CreateCamapignPage POM
		// Class
		CreateCampaignPage ccp = new CreateCampaignPage(driver);
		ccp.getCampName().sendKeys(compaignName);
		ccp.getTargSize().sendKeys(targetSize);
		ccp.getCampStatus().sendKeys(status);
		WebElement expClsDate = ccp.getExpCloseDate();
		String reqDate = jlib.getRequireDate(30);

		wlib.passInput(driver, expClsDate, reqDate);
		Thread.sleep(6000);

		ccp.getCreateCampaignButton().click();

		// fetching toastmsg by using CampaignPage POM Class
		WebElement toastmsg = hp.getToastMsg();

		System.out.println("toastmsg before ExplicitWait ==>" + (toastmsg.getText()));
		wlib.waitForVisiblityOfElement(driver, toastmsg);
		System.out.println("toastmsg After ExplicitWait ==>" + (toastmsg.getText()));

		String msg = toastmsg.getText();
		if (msg.contains(compaignName)) {
			System.out.println("Campaign verified and created");
		} else
			System.out.println("Campaign not created");

		// closing the toast message
		hp.getCrossBtnToastMsg().click();

		Thread.sleep(6000);

		// Mouse over on user-icon
		WebElement icon = hp.getUserIcon();
		wlib.mouseHoverOnWebElement(driver, icon);
		// logout from application
		hp.getLogoutBtn().click();

		
	}
}
