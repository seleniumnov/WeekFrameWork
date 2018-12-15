package functionalLibs;

import org.openqa.selenium.By;
import org.testng.Assert;

import utilities.GenericMethods;

public class ResuableFun extends GenericMethods {
	
	public static void switchToFrame() {
		
		try {
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='mainpanel']")));
		} catch (Exception e) {
			Assert.fail("Unable to Switch the Frame "+e.getMessage());
		}
	}

}
