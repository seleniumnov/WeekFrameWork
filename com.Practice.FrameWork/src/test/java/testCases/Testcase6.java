package testCases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testcase6 {
	
	@Test
	public static void auditTrail() throws Exception {

		try {
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			driver.get("https://www.freecrm.com");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			driver.manage().window().maximize();

			// HomePage home = PageFactory.initElements(driver, HomePage.class);
			// FBHomePage fbHome=PageFactory.initElements(driver, FBHomePage.class);
			// fbHome.getUsername().sendKeys("test");
			// home.userName.sendKeys("jagadeesh232708");
			// home.passWord.sendKeys("jagadeeshjaggu");
			// home.login.click();

			WebElement username = driver.findElement(By.xpath("//input[@name='username']"));
			username.sendKeys("jagadeesh232708");
			WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
			password.sendKeys("jagadeeshjaggu");
			WebElement login = driver.findElement(By.xpath("//input[@value='Login']"));
			login.click();
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='mainpanel']")));
			List<WebElement> searchelements = driver.findElements(By.xpath("//div[@id='leftMenuContainer']/div/ul/li"));

			for (int i = 0; i < searchelements.size(); i++) {
				String txt = searchelements.get(i).getText();
				System.out.println(txt);
				if (txt.equals("Audit Trail")) // geting text from properties file
				{
					searchelements.get(i).click();
					break;
				}

			}

			Thread.sleep(30000);
			// gettable count
			List<WebElement> TableElements = driver
					.findElements(By.xpath("//div[@id='dispAlertMessage']/following::table[7]/tbody/tr"));
			int count = TableElements.size();
			System.out.println("ROW COUNT : " + count);
			int actualtablecount = count - 2;
			String str = Integer.toString(actualtablecount);

			// dropdown value getting code
			Select archiveList = new Select(driver.findElement(By.xpath("//select[@name='auditSize']")));
			// no need of below statement.
			// archiveList.selectByVisibleText("30");
			String selectedValue = archiveList.getFirstSelectedOption().getText();
			System.out.println(selectedValue);

			if (str.equals(selectedValue)) {
				System.out.println("Success");
				for (WebElement webElement : driver
						.findElements(By.xpath("//div[@id='dispAlertMessage']/following::table[7]/tbody/tr/td[3]"))) {
					System.out.println(webElement.getText());
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
