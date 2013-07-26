package gmail.tests;

import gmail.pages.ComposePage;
import gmail.pages.InboxPage;
import gmail.pages.LoginPage;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeSuite;

public class TestGmailSpellCheck_Chrome {
	
	private static WebDriver driver;
	private String username = "youremail@gmail.com";
	private String password = "password";
	
	@BeforeSuite(alwaysRun=true)
	public void beforeSuite() {
		File file = new File("C:/SeleniumChromeDriver/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();		
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
		composePage.clearMessageBody();
		composePage.setSpellCheckLanguage("Español");
		composePage.checkSpelling("Esta es una pryeba");
		Assert.assertEquals(composePage.getAmountOfHighlightedWords(), 1);		
	}
	
	@AfterClass
	public static void tearDown()
	{	
		driver.quit();
	}
	
}
