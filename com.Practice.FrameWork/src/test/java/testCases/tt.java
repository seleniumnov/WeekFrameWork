package testCases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class tt {

	@Test
	public static void newCompany() throws Exception {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.freecrm.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		driver.manage().window().maximize();
		
		WebElement username=driver.findElement(By.xpath("//input[@name='username']"));
		username.sendKeys("jagadeesh232708");
		WebElement password=driver.findElement(By.xpath("//input[@name='password']"));
		password.sendKeys("jagadeeshjaggu");
		WebElement login=driver.findElement(By.xpath("//input[@value='Login']"));
		login.click();
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='mainpanel']")));
		String homepage=driver.getWindowHandle();
		System.out.println(homepage);
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.xpath("//a[@title='Companies']"));
		action.moveToElement(we).build().perform();
		List<WebElement>  searchelements=driver.findElements(By.xpath("//div[@id='navmenu']/ul/li[3]/ul/li"));
		
		for(int i=0;i<searchelements.size();i++)
		{
			String txt = searchelements.get(i).getText();
			 System.out.println(txt);
			 if(txt.equals("New Company"))
			 {
				 searchelements.get(i).click();
				 break;
			 }
			 	
		}

	}

}
