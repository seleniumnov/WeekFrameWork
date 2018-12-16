package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.Status;

import report.ExtentReports;
import webpages.FreeCRMHomePage;

public class GenericMethods extends ExtentReports {

	public FreeCRMHomePage crmHome = null;
	public static String timeStamp = null;
	public Properties prop;
	
	
	@BeforeClass(alwaysRun=true)
	@Parameters({ "browser", "app" })
	public void setUp(String name, String url) {

		selectBrowser(name);
		initApplication(url);
	}

	@AfterClass(alwaysRun=true)
	public void tearDown() {

		driver.close();
	}

	@BeforeMethod(alwaysRun=true)
	public void initWebElements(Method name) {

		logger = report.createTest(name.getName());
		crmHome = new FreeCRMHomePage(driver);
	}

	public static void captureScreen() {

		try {
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter def = DateTimeFormatter.ofPattern("ddmmyyhhmmss");
			timeStamp = def.format(now);

			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(src, new File("./ScreenShots/" + timeStamp + ".png"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void getScreen(String path) {
		try {
			File destination = new File(path);
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File src = screenshot.getScreenshotAs(OutputType.FILE);
			FileHandler.copy(src, destination);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterMethod(alwaysRun=true)
	public void verifyStatus(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.SUCCESS) {

			logger.log(Status.PASS, result.getName() + "Successfully Executed");

		} else if (result.getStatus() == ITestResult.FAILURE) {

			logger.log(Status.FAIL, result.getName() + " is Failed due to ");
			logger.log(Status.FAIL, result.getThrowable());
			
			
			Timestamp time = new Timestamp(System.currentTimeMillis());
			
			String screenlocation = "./Screenshots/" + result.getName() + "" + time.getTime()
					+ ".png";
			
			getScreen("./ExtentReports/Screenshots/" + result.getName() + "" + time.getTime() + ".png");
			
			logger.addScreenCaptureFromPath(screenlocation);

		} else if (result.getStatus() == ITestResult.SKIP) {

			logger.log(Status.SKIP, result.getName() + " is not executed due to ");
			logger.log(Status.SKIP, result.getThrowable());
		}

	}

	public void selectBrowser(String name) {

		switch (name) {
		case "chrome":
			System.setProperty(Constants.chromeKey, Constants.chromeExe);
			driver = new ChromeDriver();
			break;
		case "firefox":
			break;
		case "ie":
			break;
		default:
			Assert.fail("Please select valid browser name");
			break;
		}
	}

	public void initApplication(String url) {
		switch (url) {
		case "freecrm":
			driver.get("https://www.freecrm.com/");
			break;
		case "amazon":
			driver.get("https://www.amazon.in/");
			break;

		default:
			Assert.fail("Please select valid Application");
			break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	public void input(WebElement ele, String data, String fieldName) {
		try {
			if (ele.isDisplayed()) {
				ele.clear();
				ele.sendKeys(data);
				logger.log(Status.INFO, "Entered " + data + " in " + fieldName);
			}
		} catch (Exception e) {
			// logger.log(Status.FAIL, "Element is Not Displaye at this point of time");
			Assert.fail("Element is Not Displaye at this point of time " + e.getMessage());
		}
	}

	public void click(WebElement ele, String fieldname) {

		try {
			if (ele.isDisplayed() || ele.isEnabled()) {

				ele.click();
				logger.log(Status.INFO, "Click on " + fieldname);
			}
		} catch (Exception e) {
			// logger.log(Status.FAIL, "Element is Not Displaye at this point of time");
			Assert.fail("Element is Not Displaye at this point of time " + e.getMessage());
		}
	}

	public void selectVisibleText(WebElement ele, String data, String fieldName) {
		try {
			if (ele.isDisplayed()) {
				new Select(ele).selectByVisibleText(data);
				logger.log(Status.INFO, "Select " + data + " in " + fieldName);
			}

		} catch (Exception e) {
			// logger.log(Status.FAIL, "Unable to Select Value at this point of time");
			Assert.fail("Unable to Select Value at this point of time " + e.getMessage());
		}
	}

	public String getText(WebElement ele) {
		String data = null;
		try {
			data = ele.getText();
		} catch (Exception e) {
			// logger.log(Status.FAIL, "Unable to Fetch the data");
			Assert.fail("Unable to Fetch the data " + e.getMessage());
		}
		return data;
	}

	public String getAttributeValue(WebElement ele) {
		String data = null;
		try {
			data = ele.getAttribute("value");
		} catch (Exception e) {
			// logger.log(Status.FAIL, "Unable to Fetch the data");
			Assert.fail("Unable to Fetch the data " + e.getMessage());
		}
		return data;
	}

	public String getAttributeInnerText(WebElement ele) {
		String data = null;
		try {
			data = ele.getAttribute("innerHTML");
		} catch (Exception e) {
			// logger.log(Status.FAIL, "Unable to Fetch the data");
			Assert.fail("Unable to Fetch the data " + e.getMessage());
		}
		return data;
	}

	public void visibilityOfElement(WebElement ele) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void elementToBeClickable(WebElement ele) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public String readValidations(String name) {
		
		try {
			File src = new File(Constants.propPath);
			FileInputStream fin = new FileInputStream(src);
			prop = new Properties();
			prop.load(fin);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return prop.getProperty(name);
		
	}

}
