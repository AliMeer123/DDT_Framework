package testcases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.TestUtil;

public class ValidateLogin extends BaseTest{
	
	//@Parameters({"username","password"})
	@Test(dataProviderClass = TestUtil.class, dataProvider = "getData")
	public void doLogin(String username, String password) {
		
		type("username_ID", username);
		type("password_ID", password);
		click("signIn_Btn_XPATH");
		Assert.assertEquals(isElementPresent("mandatory_Msg1"), true);
		Assert.assertEquals(isElementPresent("mandatory_Msg2"), false);
		
		
		
	}
	
	
	
	
	/*
	 * @Test(dataProviderClass = TestUtil.class,dataProvider = "getData") public
	 * void doLogin(String username, String password) {
	 * 
	 * type("username_ID", username); type("password_ID", password);
	 * click("signIn_Btn_XPATH");
	 * 
	 * }
	 */

}
