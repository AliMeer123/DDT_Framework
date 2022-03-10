package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import utilities.TestUtil;

public class CustomListeners implements ITestListener {

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
		/*// TODO Auto-generated method stub
		System.setProperty("org.uncommons.reportng.escape-output","false");

		Reporter.log("<a href=\"F:\\screenshot\\error.jpg\" target=\"_blank\">Screenshot link</a>");
		Reporter.log("<br>");
		Reporter.log("<a href=\"F:\\screenshot\\error.jpg\" target=\"_blank\"><img src=\"F:\\screenshot\\error.jpg\" height=200 width=200></a>");
*/
		
	}

	public void onTestFailure(ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-output","false");
		
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Reporter.log("<a href="+TestUtil.fileName+" target=\"_blank\">Screenshot link</a>");
		Reporter.log("<br>");
		Reporter.log("<a href="+TestUtil.fileName+" target=\"_blank\"><img src="+TestUtil.fileName+" height=200 width=200></a>");

		
	}

	public void onTestSkipped(ITestResult result) {

		
	/*	System.setProperty("org.uncommons.reportng.escape-output","false");
		
		Reporter.log("<a href=\"F:\\screenshot\\error.jpg\" target=\"_blank\">Screenshot link</a>");
		Reporter.log("<br>");
		Reporter.log("<a href=\"F:\\screenshot\\error.jpg\" target=\"_blank\"><img src=\"F:\\screenshot\\error.jpg\" height=200 width=200></a>");
*/
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
