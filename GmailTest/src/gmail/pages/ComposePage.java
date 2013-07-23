package gmail.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class ComposePage extends PageBase{

	private String pageTitle = "Inbox";
	//private String pageURL = "https://mail.google.com/mail/u/0/#inbox?compose=new";
	
	@FindBy(css = "body")
	private WebElement webMessageBody;
	
	@FindBy(css = "img[class='Y1']")
	private WebElement webMoreOptions;
	
	@FindBy(css = "div[class='']") 
	private WebElement webCheckSpelling;
	
	public ComposePage(WebDriver driver)
	{
		super(driver);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(2,TimeUnit.SECONDS);
				
		//if(!wait.until(ExpectedConditions.titleContains(this.pageTitle)))
		//{
		//	throw new IllegalStateException("This is not the Gmail's inbox page");
		//}
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body")));
	}
	
	public ComposePage enterMessageBody(String messageBody)
	{
		webMessageBody.sendKeys(messageBody);
		return this;
	}
	
	public ComposePage clickMessageBody()
	{
		webMessageBody.click();
		return this;
	}
	
	public ComposePage clickMoreOptions()
	{
		webMoreOptions.click();
		return this;
	}
	
	public ComposePage clickCheckSpelling()
	{
		webCheckSpelling.click();
		return this;
	}	
}
