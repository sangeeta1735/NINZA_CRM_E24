package com.ninza.crm.campaigntestpractice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Demo 
{ 
	@Test(retryAnalyzer = com.ninza.crm.listenerutility.IRetryAnalyzerImplementation.class) 
	public void add() 
	{
		System.out.println("Hi"); 
		Assert.assertEquals("hdfc", "hfdc"); 
		
	}

}
