package utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.Status;

import report.ExtentReports;

public class GenericMethods extends ExtentReports {
	
	
	@BeforeTest
	
	
	
	public void selectBrowser(String name) {		
		switch (name) {
		case "chrome":
			System.setProperty(Contants.chromeKey, Contants.chromeExe);
			driver=new ChromeDriver();
			break;
		case "firefox":
			break;
		case "ie" :
			break;
		default:
			break;
		}
	}
	
	public void initApplication() {
		
		
	}
		
	public void input(WebElement ele, String data, String fieldName) {		
		try {
			if (ele.isDisplayed()) {			
				ele.clear();
				ele.sendKeys(data);
				logger.log(Status.INFO, "Entered "+data+"in "+fieldName);
			}
		} catch (Exception e) {
			logger.log(Status.FAIL, "Element is Not Displaye at this point of time");
			Assert.fail("Element is Not Displaye at this point of time "+e.getMessage());
		}		
	}
	
	public void click(WebElement ele, String fieldname) {
		
		try {
			if (ele.isDisplayed() || ele.isEnabled()) {
				
				ele.click();
				logger.log(Status.INFO, "Click on "+fieldname);
			}
		} catch (Exception e) {
			logger.log(Status.FAIL, "Element is Not Displaye at this point of time");
			Assert.fail("Element is Not Displaye at this point of time "+e.getMessage());
		}
	}
	
	public void selectVisibleText(WebElement ele, String data, String fieldName) {		
		try {
			if (ele.isDisplayed()) {
				new Select(ele).selectByVisibleText(data);
				logger.log(Status.INFO, "Select "+data+" in "+fieldName);
			}
			
		} catch (Exception e) {
			logger.log(Status.FAIL, "Unable to Select Value at this point of time");
			Assert.fail("Unable to Select Value at this point of time "+e.getMessage());
		}
	}
	
	public String getText(WebElement ele) {
		String data = null;
		try {
			data = ele.getText();
		} catch (Exception e) {
			logger.log(Status.FAIL, "Unable to Fetch the data");
			Assert.fail("Unable to Fetch the data "+e.getMessage());
		}
		return data;
	}
	
	
	public String getAttributeValue(WebElement ele) {
		String data = null;
		try {
			data = ele.getAttribute("value");
		} catch (Exception e) {
			logger.log(Status.FAIL, "Unable to Fetch the data");
			Assert.fail("Unable to Fetch the data "+e.getMessage());
		}
		return data;
	}
	
	public String getAttributeInnerText(WebElement ele) {
		String data = null;
		try {
			data = ele.getAttribute("innerHTML");
		} catch (Exception e) {
			logger.log(Status.FAIL, "Unable to Fetch the data");
			Assert.fail("Unable to Fetch the data "+e.getMessage());
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

}
