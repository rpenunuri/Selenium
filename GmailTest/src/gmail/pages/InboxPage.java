package gmail.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

/*
 * InboxPage object: provides access to Gmail's inbox page functionality 
 * */

public class InboxPage extends PageBase {

	private String pageTitle = "Inbox";
	//private String pageURL = "https://mail.google.com/mail/u/0/?shva=1#inbox";
	
	@FindBy(css = ".T-I-KE")
	private WebElement webCompose;	
	
	/*
	 * Class constructor
	 * Waits for the page title to be on screen and asserts that it is the expected one
	 * Then if this is the expected page, it waits for the Compose button to be "clickable"
	 * */
	public InboxPage(WebDriver driver)
	{		
		super(driver);
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			.withTimeout(30, TimeUnit.SECONDS)
			.pollingEvery(2,TimeUnit.SECONDS);
		if(!wait.until(ExpectedConditions.titleContains(this.pageTitle)))
		{
			throw new IllegalStateException("This is not the Gmail's inbox page");
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".T-I-KE")));							
	}
	
	/*
	 * Performs a click over the Compose button in Gmail's inbox page
	 * */
	public ComposePage clickCompose()
	{	
		this.webCompose.click();		
		ComposePage composePage = PageFactory.initElements((driver), ComposePage.class);
		return composePage;		
	}	
	
	
	/*
	 * Determines whether or not the expected page is loaded on screen
	 * To Do: Eliminate this method and make public the one from PageBase (will need to pass this.pagetitle on constructor as well)
	 * */
	public boolean isPageLoaded()
	{
		return isExpectedPage(this.pageTitle);
	}
}
