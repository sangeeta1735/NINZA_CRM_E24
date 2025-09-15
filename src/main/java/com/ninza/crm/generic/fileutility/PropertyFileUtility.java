package com.ninza.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtility 
{ 
	public String getPropertyValue(String key) throws IOException 
	{ 
		FileInputStream fis = new FileInputStream("./src/test/resources/commondata.properties"); 
		Properties ps = new Properties(); 
		ps.load(fis);
		String value = ps.getProperty(key); 
		
		return value; 
		
	}

} 



