package com.ninza.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCampaignPage 
{ 
	WebDriver driver; 
	
	public CreateCampaignPage(WebDriver driver) 
	{ 
		this.driver = driver; 
		PageFactory.initElements(driver, this); 
		
	}
	
	@FindBy(name="campaignName") 
	private WebElement campName; 
	
	@FindBy(name="campaignStatus") 
	private WebElement campStatus; 
	
	@FindBy(name="targetSize") 
	private WebElement targSize; 
	
	@FindBy(name="expectedCloseDate") 
	private WebElement expCloseDate;

	@FindBy(xpath="//button[text()='Create Campaign']") 
	private WebElement createCampaignButton; 
	
	public WebElement getCreateCampaignButton() {
		return createCampaignButton;
	}

	public WebElement getCampName() {
		return campName;
	}

	public WebElement getCampStatus() {
		return campStatus;
	}

	public WebElement getTargSize() {
		return targSize;
	}

	public WebElement getExpCloseDate() {
		return expCloseDate;
	} 
	
	 public void createCampaign(String campaignName, String targetSize) { 
			CampaignPage cp = new CampaignPage(driver);
			cp.getCreateCampaign().click();
			getCampName().sendKeys(campaignName);
			getTargSize().clear();
			getTargSize().sendKeys(targetSize);
		}


}
