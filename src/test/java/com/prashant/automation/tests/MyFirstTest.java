package com.prashant.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

public class MyFirstTest {
	
	@BeforeMethod
	public void setUp() {
		System.out.println("--- Test Starting ---");
		
	}
	
	@Test 
	public void verifyAddition() {
		System.out.println("Running: verifyAddition");
		int result = 2 + 2;
		Assert.assertEquals(result, 4, "Addition Should be 4");
		
	}
	
	@Test
	public void verifyMyName() {
		System.out.println("Running: Verify MyName");
		String name = "Prashant";
		Assert.assertEquals(name,  "Prashant", "Name should match");
		
	}
	
	@Test 
	public void tearDown() {
		System.out.println("--- Test Finished ---");
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
