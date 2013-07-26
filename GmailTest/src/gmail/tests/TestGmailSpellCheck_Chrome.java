package gmail.tests;

import gmail.pages.ComposePage;
import gmail.pages.InboxPage;
import gmail.pages.LoginPage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeSuite;

public class TestGmailSpellCheck_Chrome {
	
	private static WebDriver driver;
	private String username;
	private String password;
	
	@BeforeSuite(alwaysRun=true)
	public void beforeSuite() {
			
		Properties testProperties = new Properties();
		
		try {
			testProperties.load(getClass().getResourceAsStream("testProperties.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// get username and password to perform login into Gmail
		this.username = testProperties.getProperty("username");
		this.password = testProperties.getProperty("password");
		
		// validate the kind of driver to be used for this test
		if(testProperties.getProperty("driver").equals("Chrome"))
		{
			File file = new File(testProperties.getProperty("chromeDriverPath"));
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			driver = new ChromeDriver();
		} // To Do validate for other drivers
	}

	@BeforeTest(alwaysRun=true)
	public void setupTest()
	{		
		driver.manage().deleteAllCookies();
	}
	
	@Test(dataProvider = "spellCheckDP")
	public void testSpellCheckFindsErrors(String language, String text)
	{
		LoginPage loginPage =  PageFactory.initElements((driver), LoginPage.class);		
		InboxPage inboxPage = loginPage.signIn(this.username, this.password);			// Sign in on Gmail with provided username and password
		ComposePage composePage = inboxPage.clickCompose();								// Click on compose new email button
		composePage.clearMessageBody();													// Clear any existing text from new email body (i.e. user signature)
		composePage.setSpellCheckLanguage(language);									// Select specific language to peform spell checking
		composePage.checkSpelling(text);												// Perform spell checking for a given text
		Assert.assertEquals(composePage.getAmountOfHighlightedWords(), 1);				// Assert that spell checking feature has detected the expected amount of misspellings
	}
		
	@AfterClass
	public static void tearDown()
	{	
		driver.quit();
	}
	
	@DataProvider(name = "spellCheckDP")
	public String[][] spellCheckData() {
	    return new String[][]
	    	{ 
	    		{ "Español", "Esta es una pruyba" }//, 
	    		//{ "English", "This is a testt" }
	    	};
	}
	
}
