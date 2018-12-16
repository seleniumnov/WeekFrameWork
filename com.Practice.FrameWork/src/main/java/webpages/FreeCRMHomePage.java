package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;

import utilities.GenericMethods;

public class FreeCRMHomePage extends GenericMethods {
	
	public FreeCRMHomePage(WebDriver driver) {
		
		this.driver =driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(name="username")
	public WebElement userName;
	
	
	@FindBy(how=How.NAME,using="password")
	public WebElement passWord;

	@FindBy(how=How.XPATH,using="//input[@value='Login']")
	public WebElement login;

}
