package gmail.pages;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;


public class ComposePage extends PageBase{

	@FindBy(css = "div[aria-label='Message Body']") // Compose reply  FireFox adds an iFrame into this div :(
	private WebElement webMessageBody;
	
	@FindBy(css = "div[data-tooltip^='More'][data-tooltip*='options']")
	private WebElement webMoreOptions;
	
	@FindBy(css = "div[class='SK AX'] div:nth-of-type(9)")	
	private WebElement webCheckSpelling;
	
	@FindBy(css = "span.J-JK9eJ-PJVNOc")
	private java.util.List<WebElement> highlightedElements;
	
	@FindBy(css = "div[role='button'][aria-expanded='false'][style^='-webkit-user-select:'][aria-haspopup='true'] img[class='aDy T-I-J3']")
	private WebElement webLanguageSelectButton;
	
	@FindBy(css = "div[class='J-M J-M-ayU ex'][role='menu']")
	private WebElement webLanguage;
	
	@FindBy(css = "div[class='J-M J-M-ayU ex'][role='menu'] div[role='menuitem']")
	private java.util.List<WebElement> webListOfLanguages;
	
	private HashMap<String, Integer> listOfLanguages;
	
	public ComposePage(WebDriver driver)
	{
		super(driver);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(2,TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[aria-label='Message Body']"))); //'Compose reply']")));
		loadLanguages();
	}
	
	/*
	 * Enters a message into email body
	 * */
	public ComposePage enterMessageBody(String messageBody)
	{			
		webMessageBody.sendKeys(messageBody);
		return this;
		
		//if(webMessageBody.findElement(By.tagName("iframe")))
		//webMessageBody.findElement(By.tagName("iframe")).sendKeys(messageBody);
	}
		
	/*
	 * Clears any text from email body
	 * */
	public ComposePage clearMessageBody()
	{
		webMessageBody.clear();
		return this;
	}
	
	/*
	 * Click on "More Options" button
	 * */
	public ComposePage clickMoreOptions()
	{	
		webMoreOptions.click();
		return this;
	}
	
	/*
	 * Click on "Check spelling" option from the "More options" menu
	 * */
	public ComposePage clickCheckSpelling()
	{
		webCheckSpelling.click();
		return this;
	}	
	
	/*
	 * Checks spelling for a given text
	 * */
	public ComposePage checkSpelling(String message)
	{
		enterMessageBody(message);
		clickMoreOptions();
		clickCheckSpelling();
		return this;
	}
	
	/*
	 * Performs a click on Languange selection button displayed in check spelling options menu
	 * */
	public ComposePage clickLanguageSelectButton()
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(4, TimeUnit.SECONDS)
				.pollingEvery(2,TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[role='button'][aria-expanded='false'][style^='-webkit-user-select:'][aria-haspopup='true'] img[class='aDy T-I-J3']")));//*[@id=":nz.ln"]
		webLanguageSelectButton.click();		
		return this;
		
		// to detect elements from list of languages wait.until(ExpectedConditions.textToBePresentInElement(locator, text) (By.cssSelector("div[class='T-I J-J5-Ji aDs T-I-Js-IF T-I-ax7 L3')")));
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img.aDy T-I-J3[src*='cleardot.gif']")));// visibilityOf(webLanguageSelect));	//visibilityOf(webLanguageSelect));// 		
	
	}
	
	/*
	 * Performs a click on desired laguage item from the list displayed in check spelling options menu 
	 * (NOTE: need to click Language Select Button for this menu to be displayed)
	 * */
	public ComposePage clickLanguage(String language)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(4, TimeUnit.SECONDS)
				.pollingEvery(2,TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='J-M J-M-ayU ex'][role='menu'] div[role='menuitem']")));
		webListOfLanguages.get(listOfLanguages.get(language)).click();
		return this;
	}	
	
	/*
	 * Sets the laguange to be used during spell check verification
	 * */	 	
	public ComposePage setSpellCheckLanguage(String language)
	{
		checkSpelling("");	// We need to perform a Check spelling action, so the language menu will be available on screen
		clickLanguageSelectButton();
		clickLanguage(language);
		return this;
	}
	
	/*
	 * Returns the amount of highlighted (yellow) words from email message body element
	 * */
	public int getAmountOfHighlightedWords()
	{	
		int returnValue = 0;
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(2,TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		
		// ***** To Do: catch case when there are no highlighted elements ********
		try{		
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.J-JK9eJ-PJVNOc")));			
			returnValue = highlightedElements.size();
		}
		catch(Exception ex)
		{			
			
		}				
		return returnValue;		
		//driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		//driver.switchTo().defaultContent();
		//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("span[style$=yellow]")));
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")).findElements(By.cssSelector("span.J-JK9eJ-PJVNOc"));		
		//java.util.List<WebElement> highlightedElements = driver.findElements(By.cssSelector("span"));
		//java.util.List<WebElement> highlightedElements = driver.findElements(By.cssSelector("span.J-JK9eJ-PJVNOc"));		
		//webMessageBody.findElement(By.tagName("iframe")).sendKeys("me carga el payasooo");				
	}
	
	private void loadLanguages()
	{
		listOfLanguages = new HashMap<String, Integer>();		
		listOfLanguages.put("English (US)", 9);
		listOfLanguages.put("Español", 10);
	}
}
