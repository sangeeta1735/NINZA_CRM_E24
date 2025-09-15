package com.ninza.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage 
{ 
WebDriver driver ; 
	
	public ProductsPage (WebDriver driver)  
	{ 
		this.driver = driver ; 
		PageFactory.initElements(driver , this );
	}
	

	@FindBy(xpath = "//span[text()='Add Product']") 
	private WebElement addPRoduct; 
	
	@FindBy(xpath="//select[@class='form-control']") 
	private WebElement searchByPRodId; 
	
	public WebElement getAddPRoduct() {
		return addPRoduct;
	}

	public WebElement getSearchByPRodId() {
		return searchByPRodId;
	}
	

}
