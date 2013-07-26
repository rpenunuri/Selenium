package gmail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends PageBase {
	
	private String pageTitle = "Gmail: Email from Google";
	private String pageURL = "http://www.gmail.com";
	
	@FindBy(id = "Email")
	private WebElement webUserName;
	
	@FindBy(id = "Passwd")
	private WebElement webPassword;		
	
	@FindBy(id = "signIn")
	private WebElement webSignIn;
			
	public LoginPage(WebDriver driver)
	{
			super(driver);
			openPage(this.pageURL);
			if (!isExpectedPage(this.pageTitle))
			{
				throw new IllegalStateException("This is not the Gmail's login page");
			}
			//loadWebElements();			
	}
	
	//@Override
	/*protected void loadWebElements()
	{
		webUserName = driver.findElement(By.id("Email"));
		webPassword = driver.findElement(By.id("Passwd"));
		webSignIn = driver.findElement(By.id("signIn"));
	}
	*/
	public LoginPage enterUserName(String userName)
	{
		webUserName.sendKeys(userName);
		return this;
	}
	
	public LoginPage enterPassword(String password)
	{
		webPassword.sendKeys(password);
		return this;
	}
	
	public InboxPage clickSignIn()
	{
		webSignIn.click();	
		InboxPage inboxPage = PageFactory.initElements((driver), InboxPage.class);
		return inboxPage;
	}
	
	public InboxPage signIn(String userName, String password)
	{			
		enterUserName(userName);
		enterPassword(password);
		return clickSignIn();				
	}
}
