package testCases;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import functionalLibs.ResuableFun;
import testData.ReadExcel;
import utilities.Constants;
import utilities.GenericMethods;

public class TestCase2 extends GenericMethods {
	
	Map<String, String> testData = ReadExcel.readData(Constants.excelPath, "login", "CRMLogin");
	

	@Test(priority=0)
	public void login() {
		
		input(crmHome.userName, testData.get("UserName"), "User Name");
		input(crmHome.passWord, testData.get("Password"), "Password");
		click(crmHome.login,"Login");
	}
	
	@Test(priority=1)
	public void skipTest() {
		
		new SkipException("Skipping Test Case");
	}
	
	
	@Test(priority=2)
	public void CRMTestCase() {
		
		ResuableFun.switchToFrame();
		logger.log(Status.INFO, "Switch to Frame success");
		Assert.fail("Failing for Report");
		String homepage = driver.getWindowHandle();
		System.out.println(homepage);
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.xpath("//a[@title='Companies']"));
		action.moveToElement(we).build().perform();

		List<WebElement> searchelements = driver.findElements(By.xpath("//div[@id='navmenu']/ul/li[3]/ul/li"));

		for (int i = 0; i < searchelements.size(); i++) {
			String txt = searchelements.get(i).getText();
			System.out.println(txt);
			if (txt.equals(readValidations("home.company"))) {
				searchelements.get(i).click();
				break;
			}
		}
		Set<String> windows = driver.getWindowHandles();
		System.out.println(windows.size());
		Iterator iterator = windows.iterator();
		String currentwindowid;
		while (iterator.hasNext()) {
			currentwindowid = iterator.next().toString();
			if (!currentwindowid.equals(homepage)) {
				driver.switchTo().window(currentwindowid);
				System.out.println(currentwindowid);

				
				
				List<WebElement> menuItems = driver.findElements(By.xpath("//input[@value='?']"));
				for (int j = 0; j < menuItems.size(); j++) {

					menuItems.get(j).click();
					String title = driver.getTitle();
					System.out.println(title);
					String txt = menuItems.get(j).getText();
					System.out.println(txt);
					if (txt.equals("Setup - Fields")) {
						menuItems.get(j).click();
						// String txt1=
						// driver.findElement(By.xpath("//*[contains(text(),'"+menuItems.get(j)+"')]/following::p[1]")).getText();
						String txt1 = driver
								.findElement(By.xpath("//*[contains(text(),'\"+txt1+\"')]/following::td[2]")).getText();
						System.out.println(txt1);
						break;

					}
					driver.close();
				}
			}

		}

	}

}
