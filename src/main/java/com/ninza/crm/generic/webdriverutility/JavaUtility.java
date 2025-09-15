package com.ninza.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility 
{ 
	
	public int getRandomNumber() 
	{ 
		Random rd = new Random(); 
		int randumNum = rd.nextInt(1000); 
		return randumNum; 
		
	} 
	
	public String getCurrentDate() 
	{ 
		Date date = new Date(); //import from java.util.*; package 
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy"); 
		String currentDate = sim.format(date); 
		return currentDate; 
		
		
	}
	
	public String getRequireDate(int exDate) 
	{ 
		Date date = new Date(); 
		
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy"); 
		sim.format(date); 
		Calendar cal = sim.getCalendar(); 
		cal.add(Calendar.DAY_OF_MONTH, exDate); 
		String expectedDate = sim.format(cal.getTime()); 
		return expectedDate; 
		
	}


}
