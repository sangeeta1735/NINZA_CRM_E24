package com.ninza.crm.producttestpractice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.generic.webdriverutility.JavaUtility;
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;
import com.ninza.crm.objectrepository.CreateProductsPage;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;
import com.ninza.crm.objectrepository.ProductsPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProductWithMandatoryFiledsUsingPOMRepositoryTest {

	@Test(groups = "Regression")
	public void createProductWithMandatoryFiledsUsingPOMRepositoryTest() throws IOException, InterruptedException {
		// getting common data from properties file to Login to the NinzaCRM application

		PropertyFileUtility pf = new PropertyFileUtility();
		String BROWSER = pf.getPropertyValue("browser");
		String URL = pf.getPropertyValue("url");
		String USERNAME = pf.getPropertyValue("username");
		String PWD = pf.getPropertyValue("password");

		// getting data from Excel sheet for Product

		ExcelFileUtility ef = new ExcelFileUtility();

		String productName = ef.getDataFromExcelSheet("Product", 1, 0);

		String quantity = ef.getDataFromExcelSheet("Product", 1, 1);

		String pricePerUnit = ef.getDataFromExcelSheet("Product", 1, 2);

		WebDriverUtility wdu = new WebDriverUtility();
		JavaUtility jUtility = new JavaUtility();

		// launching the browser

		ChromeOptions settings = new ChromeOptions();

		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);

		WebDriver driver = null;
		if (BROWSER.equalsIgnoreCase("edge")) {
			{ 
				//System.setProperty("webdriver.edge.driver", "C:\\path\\to\\msedgedriver.exe"); 
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver(); 
			} 
			

		} else if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(settings);

		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		// Login to NinzaCRM application by calling loginIntoApp() method from LoginPage
		// POM Class
		LoginPage lp = new LoginPage(driver);
		lp.loginIntoApp(URL, USERNAME, PWD);

		Thread.sleep(6000);

		// Navigating to Product Page by using HomePage POM Class
		HomePage hp = new HomePage(driver);
		hp.getProductsLink().click();
		Thread.sleep(6000);

		// driver.findElement(By.linkText("Products")).click();

		// click on Add Product by using ProductsPage Class
		ProductsPage pp = new ProductsPage(driver);
		pp.getAddPRoduct().click();

		// filling Mandatory Fields with values fetched from Excel sheet by using POM
		// Class
		CreateProductsPage cpp = new CreateProductsPage(driver);
		int randomNumber = jUtility.getRandomNumber();

		cpp.getPoductName().sendKeys(productName + randomNumber);
		wdu.select(cpp.getProductCategory(), 2);
		cpp.getQuantity().clear();
		cpp.getQuantity().sendKeys(quantity);
		cpp.getPrice().clear();
		cpp.getPrice().sendKeys(pricePerUnit);
		// wdu.select(cpp.getVendorId(), 1);
		wdu.select(cpp.getVendorId(), "VID_453");
		Thread.sleep(2000);

		// click on Add button to create Product
		cpp.getAddButton().click();

		// capturing toastmsg
		WebElement toastMSg = hp.getToastMsg();
		wdu.waitForVisiblityOfElement(driver, toastMSg);
		String toastMsg = toastMSg.getText();

		System.out.println("After Successfully Creation of Product toastMsg is ====>" + toastMsg);

		// verifying toastmsg
		if (toastMsg.contains(productName + randomNumber))

			System.out.println("Product verified and created successfully");

		else
			System.out.println("Product Not created successfully");
		// Closing the Cross button on toastMsg
		hp.getCrossBtnToastMsg().click();
		// logout the application
		hp.logout();

		// driver.findElement(By.xpath("//span[text()='Add Product']")).click();
		/*
		 * //filling mandatory fields with values fetched from Excel Sheet
		 * driver.findElement(By.name("productName")).sendKeys(productName); WebElement
		 * quant = driver.findElement(By.name("quantity")); quant.clear();
		 * quant.sendKeys(quantity); WebElement pricePerUNIT =
		 * driver.findElement(By.name("price")); pricePerUNIT.clear();
		 * pricePerUNIT.sendKeys(pricePerUnit);
		 * 
		 * WebElement dropdown1 = driver.findElement(By.name("productCategory")); Select
		 * sel1 = new Select(dropdown1);
		 * 
		 * sel1.selectByValue("Electricals");
		 * 
		 * WebElement dropdown2 = driver.findElement(By.name("vendorId")); Select sel2 =
		 * new Select(dropdown2);
		 * sel2.selectByVisibleText("Vendor_50877 - (Electronics)");
		 * 
		 * //click on Add button to create Product
		 * driver.findElement(By.xpath("//button[@type='submit']")).click();
		 * 
		 * 
		 * //driver.findElement(By.xpath("//button[text()='Add']")).click();
		 */

	}

}
