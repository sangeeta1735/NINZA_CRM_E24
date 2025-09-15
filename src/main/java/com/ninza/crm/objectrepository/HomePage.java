package com.ninza.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage 
{ 
	WebDriver driver;
	public HomePage(WebDriver driver) 
	{ 
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(linkText = "Campaigns") 
	private WebElement campaignLink; 
	
	@FindBy(linkText = "Contacts") 
	private WebElement contactsLink; 
	
	@FindBy(linkText = "Leads") 
	private WebElement leadsLink; 
	
	@FindBy(linkText = "Opportunities") 
	private WebElement opportunitiesLink; 
	
	@FindBy(linkText = "Products") 
	private WebElement productsLink; 
	
	@FindBy(linkText = "Quotes") 
	private WebElement quotesLink; 
	 
	@FindBy(linkText = "Purchase Order") 
	private WebElement purchaseOrderLink; 
	
	 
	@FindBy(xpath="//div[@role='alert']") 
	private WebElement toastMsg; 
	
	public WebElement getToastMsg() {
		return toastMsg;
	} 


	public WebElement getCrossBtnToastMsg() {
		return crossBtnToastMsg;
	}


	public WebElement getUserIcon() {
		return userIcon;
	}


	public WebElement getLogoutBtn() {
		return logoutBtn;
	}

	 
	@FindBy(xpath="//button[@aria-label='close']") 
	private WebElement crossBtnToastMsg; 
	
	 
	@FindBy(xpath="//div[@class='user-icon']") 
	private WebElement userIcon; 
	
	 
	@FindBy(xpath="//div[text()='Logout ']") 
	private WebElement logoutBtn; 
	
	
	public WebDriver getDriver() {
		return driver;
	}

	
	public WebElement getCampaignLink() {
		return campaignLink;
	} 

	public WebElement getContactsLink() {
		return contactsLink;
	}

	public WebElement getLeadsLink() {
		return leadsLink;
	}

	public WebElement getOpportunitiesLink() {
		return opportunitiesLink;
	}

	public WebElement getProductsLink() {
		return productsLink;
	}

	public WebElement getQuotesLink() {
		return quotesLink;
	}

	public WebElement getPurchaseOrderLink() {
		return purchaseOrderLink;
	}
	
	public void logout() {
		Actions act = new Actions(driver); 
		act.moveToElement(userIcon).perform(); 
		act.moveToElement(logoutBtn).click().perform(); 
		
		
	}
	
	
	
	

}
