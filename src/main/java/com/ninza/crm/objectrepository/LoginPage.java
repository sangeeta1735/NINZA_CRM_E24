package com.ninza.crm.objectrepository;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{ 
	
	WebDriver driver; 
	public LoginPage(WebDriver driver ) 
		{ 
			this.driver = driver; 
			PageFactory.initElements(driver, this);
		}
	
	@FindBy(id = "username") 
	private WebElement username; 
	
	public WebElement getUsername() {
		return username;
	}

	
	public WebElement getPassword() {
		return password;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	@FindBy(id = "inputPassword") 
	private WebElement password; 
	
	@FindBy(xpath = "//button[@type='submit']") 
	private WebElement loginButton; 
	
	public void loginIntoApp(String url,String un , String pwd) 
	{	
		
		driver.manage().window().maximize(); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); 
		driver.get(url); 
		
		getUsername().sendKeys(un); 
		getPassword().sendKeys(pwd); 
		getLoginButton().click(); 
		
		
	}

}
