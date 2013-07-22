package gmail.tests;

import gmail.pages.ComposePage;
import gmail.pages.InboxPage;
import gmail.pages.LoginPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestGmailSpellCheck {
	
	private static WebDriver driver;
	private String username = "yourgmail@gmail.com";
	private String password = "yourpassword";
	
	@BeforeSuite(alwaysRun=true)
	public void initializeClass()
	{
		driver = new FirefoxDriver();		
	}	
	
	@BeforeTest
	public void setupTest()
	{
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void testSpellCheckFindsErrors()
	{
		LoginPage loginPage =  PageFactory.initElements((driver), LoginPage.class);			
		InboxPage inboxPage = loginPage.signIn(this.username, this.password);		
		ComposePage composePage = inboxPage.clickCompose();
		composePage.clickMessageBody();
		//composePage.enterMessageBody("Esta es una pryeba");
		//composePage.clickMoreOptions();
		//composePage.clickCheckSpelling();
	}
	
	@AfterClass
	public static void tearDown()
	{	
		driver.quit();
	}
}
