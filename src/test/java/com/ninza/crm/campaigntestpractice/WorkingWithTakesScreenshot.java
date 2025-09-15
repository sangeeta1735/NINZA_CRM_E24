package com.ninza.crm.campaigntestpractice;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date; 

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;



public class WorkingWithTakesScreenshot {

	public static void main(String[] args) throws IOException {
		WebDriver driver = new ChromeDriver(); 
		driver.manage().window().maximize(); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); 
		Date date = new Date(); 
		String d = date.toString().replace(" ", "_").replace(":", "_"); 
		System.out.println(d); 
		
		driver.get("https://www.facebook.com/"); 
		
		TakesScreenshot ts = (TakesScreenshot) driver; 
		File src = ts.getScreenshotAs(OutputType.FILE); 
		File dest = new File("./Screenshots/WorkingWithTakesScreenshot"+d+".png"); 
		FileHandler.copy(src, dest); 
		
		
		

	}

}
