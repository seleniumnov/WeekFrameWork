package testCases;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testcase4 {
	static WebDriver driver=null;
	public static void captureScreen() {

		
		try {
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter def = DateTimeFormatter.ofPattern("ddmmyyhhmmss");
			String timeStamp = def.format(now);
			
			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(src, new File("./ScreenShots/"+timeStamp+".png"));
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	@Test
	public  void screenshot() throws Exception {
		WebDriverManager.chromedriver().setup();
		 driver=new ChromeDriver();
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
		
		
		List<WebElement>  searchelements=driver.findElements(By.xpath("//div[@id='navmenu']/ul/li"));
		
		for(int i=1;i<searchelements.size();i++)
		{
			String txt = searchelements.get(i).getText();
			System.out.println(txt);
			Actions action = new Actions(driver);
			action.moveToElement(searchelements.get(i)).build().perform();			
			//searchelements.get(i).click();
			captureScreen();
			if (txt.equals("Reports")) 
			{
				break;
			}
			 	
		}
		
		
	
	}

}
