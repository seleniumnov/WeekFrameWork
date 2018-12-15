package report;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import utilities.Contants;

public class ExtentReports {

	public ExtentHtmlReporter html;
	public com.aventstack.extentreports.ExtentReports report;
	public ExtentTest logger;
	public static WebDriver driver = null;
	public WebDriverWait wait;

	@BeforeSuite
	public void startReport() {
		html = new ExtentHtmlReporter(Contants.reportPath);
		html.loadXMLConfig(Contants.reportXML);		
		report = new com.aventstack.extentreports.ExtentReports();		
		report.attachReporter(html);
	}
	
	@AfterSuite
	public void endReport() {		
		report.flush();
	}

}
