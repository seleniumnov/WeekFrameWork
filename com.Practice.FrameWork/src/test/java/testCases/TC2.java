package testCases;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC2 {

	@Test
	public void testCase2() {

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

			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.xpath("//a[@title='Companies']"));
			action.moveToElement(we).build().perform();
			List<WebElement> searchelements = driver.findElements(By.xpath("//div[@id='navmenu']/ul/li[3]/ul/li"));
			for (WebElement webElement : searchelements) {
				String txt = webElement.getText();
				System.out.println(txt);
				if (txt.equals("New Company")) {
					webElement.click();
					break;
				}
			}
			List<WebElement> menuItems = driver
					.findElements(By.xpath("(//form[@id='companyForm']/table/tbody/tr/td//*[@value='?'])"));
			String homepage = driver.getWindowHandle();

			for (int i = 1; i <= menuItems.size(); i++) {
				Thread.sleep(5000);
				WebElement webElement = driver.findElement(
						By.xpath("(//form[@id='companyForm']/table/tbody/tr/td//*[@value='?'])[" + i + "]"));

				try {
					webElement.click();
					Set<String> windows = driver.getWindowHandles();
					System.out.println(windows.size());
					Iterator<String> iterator = windows.iterator();
					String currentwindowid;
					while (iterator.hasNext()) {
						currentwindowid = iterator.next().toString();
						if (!currentwindowid.equals(homepage)) {
							driver.switchTo().window(currentwindowid);
							System.out.println(currentwindowid);
							// driver.close();
							driver.manage().window().maximize();
							String title = driver.getTitle();
							System.out.println(title);
							String name = driver.findElement(By.xpath("//td[@class='datacardtitle']//span")).getText();
							String txt12 = driver
									.findElement(By.xpath("//*[contains(text(),'" + name + "')]/following::td[2]"))
									.getText();
							System.out.println(txt12);
							driver.close();
						}
					}
					driver.switchTo().window(homepage);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
