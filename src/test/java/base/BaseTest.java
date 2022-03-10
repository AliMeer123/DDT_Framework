package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.DbManager;
import utilities.ExcelReader;
import utilities.MonitoringMail;

public class BaseTest {

	/*
	 * 
	 * WebDriver, - done Implicit vs ExplicitWait Keywords Properties - done Logs -
	 * done Mail - done Database TestNG Paramerterization ReportNG isElementPresent
	 * captureScreenshot Excel - done
	 * 
	 */

	public static WebDriver driver;
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader("./src/test/resources/excel/testdata.xlsx");
	public static MonitoringMail mail = new MonitoringMail();
	//public static Logger log = Logger.getLogger(BaseTest.class.getSimpleName());
	public static WebDriverWait wait;
	public static WebElement dropdown;
	
	public static boolean isElementPresent(String key) {

		try {
		if (key.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(key)));
		} else if (key.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(key)));
		} else if (key.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(key)));
		}
		//log.info("Finding an Element : " + key);
		return true;
		}catch(Throwable t) {
			
		//	log.info("Error while finding an Element : "+key+" error message is: "+t.getMessage());
			return false;
		}
	}


	public static void click(String key) {

		try {
		if (key.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(key))).click();
		} else if (key.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(key))).click();
		} else if (key.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(key))).click();
		}
	//	log.info("Clicking on an Element : " + key);
		}catch(Throwable t) {
			
	//		log.info("Error while clicking on an Element : "+key+" error message is: "+t.getMessage());
			Assert.fail(t.getMessage());
		}
	}

	
	
	public static void select(String key, String value) {

		try {
		if (key.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(key)));
		} else if (key.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(key)));
		} else if (key.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(key)));
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
	//	log.info("Selecting an Element : " + key+"  selected value as : "+value);
		}catch(Throwable t) {
			
	//		log.info("Error while selecting on an Element : "+key+" error message is: "+t.getMessage());
			Assert.fail(t.getMessage());
		
		}
	}

	
	
	
	
	
	
	
	public static void type(String key, String value) {

		try {
			if (key.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(key))).sendKeys(value);
			} else if (key.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(key))).sendKeys(value);
			} else if (key.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(key))).sendKeys(value);
			}
		//	log.info("Typing in an Element : " + key+"  entered the value as : "+value);
			}catch(Throwable t) {
				
		//		log.info("Error while typing in an Element : "+key+" error message is: "+t.getMessage());
				Assert.fail(t.getMessage());
			}
	}

	@BeforeSuite
	public void setUp() {

		if (driver == null) {

		//	PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");

			try {
				fis = new FileInputStream("./src/test/resources/properties/Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Config.load(fis);
//				log.info("Config properties file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream("./src/test/resources/properties/OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
		//		log.info("OR properties file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (Config.getProperty("browser").equals("chrome")) {

				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
		//		log.info("Chrome browser launched !!!");
			} else if (Config.getProperty("browser").equals("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
	//			log.info("Firefox browser launched !!!");
			}

			driver.get(Config.getProperty("testsiteurl"));
	//		log.info("Navigated to : " + Config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, Integer.parseInt(Config.getProperty("explicit.wait")));
			try {
				DbManager.setMysqlDbConnection();
	//			log.info("Db Connection established !!!");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@AfterSuite
	public void tearDown() {

		driver.quit();
	//	log.info("Test Execution completed !!!");
	}

}
