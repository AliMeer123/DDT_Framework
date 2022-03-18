package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class ValidateLandingPage extends BaseTest{
	
	@Test
	public void testLandingPage() {
		
		Assert.assertEquals(isElementPresent("signU_btn_XPATH"),true);
		System.err.println(isElementPresent("signU_btn_XPATH"));
		click("signU_btn_XPATH");
	}

}
