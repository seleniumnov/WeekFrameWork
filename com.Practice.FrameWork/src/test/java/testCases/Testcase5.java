package testCases;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testcase5 {

	@Test
	public static void getBlog() throws Exception {

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

			String homepage = driver.getWindowHandle();
			System.out.println(homepage);

			List<WebElement> searchelements = driver.findElements(By.xpath("//div[@id='leftMenuContainer']/div/ul/li"));

			for (int i = 0; i < searchelements.size(); i++) {
				String txt = searchelements.get(i).getText();
				System.out.println(txt);
				if (txt.equals("Blog")) {
					searchelements.get(i).click();
					break;
				}

			}
			Thread.sleep(5000);

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			Set<String> windows = driver.getWindowHandles();
			System.out.println(windows.size());
			Iterator iterator = windows.iterator();
			String currentwindowid;
			while (iterator.hasNext()) {
				currentwindowid = iterator.next().toString();
				if (!currentwindowid.equals(homepage)) {
					driver.switchTo().window(currentwindowid);
					System.out.println(currentwindowid);

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					Thread.sleep(5000);
					List<WebElement> menuItems = driver.findElements(By.xpath("//div[@id='post-listing']/article/h3"));
					for (int j = 0; j < menuItems.size(); j++) {

						String txt = menuItems.get(j).getText();
						System.out.println(txt);
						if (txt.equals("Do not share logins!")) {
							menuItems.get(j).click();
							// String txt1=
							// driver.findElement(By.xpath("//*[contains(text(),'"+menuItems.get(j)+"')]/following::p[1]")).getText();
							String txt1 = driver
									.findElement(By.xpath("//*[contains(text(),'" + txt + "')]/following::p[1]"))
									.getText();
							System.out.println(txt1);
							break;

						}
						driver.close();
					}
				}

			}

		} catch (Exception ex) {
		}

	}

}
