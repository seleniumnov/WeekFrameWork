package testCases;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.GenericMethods;

public class TestCase1 extends GenericMethods {

	@Test
	public void TC001() {

		try {
			
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

			for (int i = 0; i < searchelements.size(); i++) {
				String txt = searchelements.get(i).getText();
				System.out.println(txt);
				if (txt.equals("New Company")) {
					searchelements.get(i).click();
					break;
				}
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement Company = driver.findElement(By.xpath("//input[@id='company_name']"));
			Company.sendKeys("Company");
			WebElement Industry = driver.findElement(By.xpath("//input[@name='industry']"));
			Industry.sendKeys("xxx");
			WebElement Annual = driver.findElement(By.xpath("//input[@id='annual_revenue']"));
			Annual.sendKeys("yza");
			WebElement Employe = driver.findElement(By.xpath("//input[@id='num_of_employees']"));
			Employe.sendKeys("demo");
			WebElement phone = driver.findElement(By.xpath("//input[@id='phone']"));
			phone.sendKeys("8801636401");
			WebElement fax = driver.findElement(By.xpath("//input[@id='fax']"));
			fax.sendKeys("12345");
			String homepage = driver.getWindowHandle();
			System.out.println(homepage);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			WebElement Lookup = driver.findElement(By.xpath("//input[@value='Lookup']"));
			Lookup.click();
			Set<String> windows = driver.getWindowHandles();
			System.out.println(windows.size());
			Iterator iterator = windows.iterator();
			String currentwindowid;
			while (iterator.hasNext()) {
				currentwindowid = iterator.next().toString();
				if (!currentwindowid.equals(homepage)) {
					driver.switchTo().window(currentwindowid);
					System.out.println(currentwindowid);
					// driver.close();
					driver.manage().window().maximize();
					WebElement searchtxt = driver.findElement(By.xpath("//input[@id='search']"));
					// searchtxt.sendKeys("test");
					driver.findElement(By.xpath("//input[@value='Search']")).click();
					WebElement text = driver.findElement(By.xpath("//input[@value='Search']/following::td"));
					String txtmsg = text.getText();
					System.out.println(txtmsg);
					if (txtmsg.equals("No results were found. Please try another search term.")) {
					}
					// driver.switchTo().alert().accept();
					// driver.findElement(By.linkText("Continue to NetBanking")).click();
					driver.close();
					break;
				}
			}
			// Switching back to Parent Window
			driver.switchTo().window(homepage);

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Select dropdown = new Select(driver.findElement(
					By.xpath("//form[@id='companyForm']/table/tbody/tr[2]/td[1]/table/tbody/tr[5]/td[2]/select")));
			dropdown.selectByValue("Hot");

			Select dropdown1 = new Select(driver.findElement(
					By.xpath("//form[@id='companyForm']/table/tbody/tr[2]/td[1]/table/tbody/tr[6]/td[2]/select")));
			dropdown1.selectByValue("Client");

			Select dropdown2 = new Select(driver.findElement(
					By.xpath("//form[@id='companyForm']/table/tbody/tr[2]/td[1]/table/tbody/tr[7]/td[2]/select")));
			dropdown2.selectByValue("High");
			Select dropdown3 = new Select(driver.findElement(
					By.xpath("//form[@id='companyForm']/table/tbody/tr[2]/td[1]/table/tbody/tr[8]/td[2]/select")));
			dropdown3.selectByValue("Ad");
			WebElement website = driver.findElement(By.xpath("//input[@id='website']"));
			website.sendKeys("freecrm.com");
			WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
			email.sendKeys("test@gmail.com");

			WebElement symbol = driver.findElement(By.xpath("//input[@id='symbol']"));
			symbol.sendKeys("test");

			WebElement identifier = driver.findElement(By.xpath("//input[@name='identifier']"));
			identifier.sendKeys("test");

			WebElement vat_number = driver.findElement(By.xpath("//input[@id='vat_number']"));
			vat_number.sendKeys("88888");

			WebElement address_title = driver.findElement(By.xpath("//input[@name='address_title']"));
			address_title.sendKeys("test");

			WebElement city = driver.findElement(By.xpath("//input[@name='city']"));
			city.sendKeys("test1");
			WebElement address = driver.findElement(By.xpath("//textarea[@name='address']"));
			address.sendKeys("test1");

			WebElement state = driver.findElement(By.xpath("//input[@name='state']"));
			state.sendKeys("texas");

			WebElement postcode = driver.findElement(By.xpath("//input[@name='postcode']"));
			postcode.sendKeys("524004");

			WebElement country = driver.findElement(By.xpath("//input[@name='country']"));
			country.sendKeys("tx");

			WebElement tags = driver.findElement(By.xpath("//input[@id='tags']"));
			tags.sendKeys("tagsest");

			WebElement service = driver.findElement(By.xpath("//textarea[@id='service']"));
			service.sendKeys("test");
			// WebElement
			// savebutton=driver.findElement(By.xpath("//form[@id='companyForm']/table/tbody/tr[8]/td/input"));
			// savebutton.click();
			// form[@id='companyForm']/table/tbody/tr[2]/td[1]/table/tbody/tr/td/input
			/*
			 * List<WebElement> questionmarks=driver.findElements(By.xpath(
			 * "//form[@id='companyForm']/table/tbody/tr[2]/td[1]/table/tbody/tr/td/input"))
			 * ; for(int k=0;k<questionmarks.size();k++) { String txt =
			 * questionmarks.get(k).getText(); System.out.println(txt);
			 * if(txt.contains("button")) {
			 * 
			 * questionmarks.get(k).click(); break; }
			 * 
			 * }
			 */
			// form[@id='companyForm']/table/tbody/tr[2]/td[2]/table/tbody/tr[6]/td[2]/input[4]

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
