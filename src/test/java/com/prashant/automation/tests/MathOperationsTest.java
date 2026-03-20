package com.prashant.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MathOperationsTest {

	@Test
	public void verifySubtraction() {
	int result = 10 - 3;
	Assert.assertEquals(result, 7);
	}

	@Test
	public void verifyMultiplication() {
	    int result = 4 * 5;
	    Assert.assertEquals(result, 20);
	}

	@Test
	public void verifyDivision() {
	    int result = 20 / 4;
	    Assert.assertEquals(result, 5);
	}
	
}

	
