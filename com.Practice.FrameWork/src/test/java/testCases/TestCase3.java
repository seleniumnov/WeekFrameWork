package testCases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase3 {

	@Test
	public static void getQuickSearch() throws Exception {

		try {
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			driver.get("https://www.freecrm.com");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			driver.manage().window().maximize();

			WebElement username = driver.findElement(By.xpath("//input[@name='username']"));
			username.sendKeys("jagadeesh232708");
			WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
			password.sendKeys("jagadeeshjaggu");
			WebElement login = driver.findElement(By.xpath("//input[@value='Login']"));
			login.click();
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='mainpanel']")));

			List<WebElement> waterMark = driver.findElements(By.xpath("//input[@value='Quick Search']"));

			System.out.println(waterMark.size());
			for (WebElement ele : waterMark) {

				if (!ele.getAttribute("type").equals("hidden")) {

					if (ele.getAttribute("value").equals("Quick Search")) {

						String myText = ele.getText();
						System.out.println(myText);
						ele.sendKeys("Test");
						ele.sendKeys(Keys.ENTER);
					}
				}
			}

		} catch (Exception ex) {
		}
	}

}
