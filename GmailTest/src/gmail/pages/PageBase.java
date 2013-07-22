package gmail.pages;

import org.openqa.selenium.WebDriver;

public abstract class PageBase {

	protected WebDriver driver;
	//protected String pageTitle;
	//protected String pageURL;
	
	protected PageBase(WebDriver driver)
	{
		this.driver = driver;				
	}
	
	protected void openPage(String URL)
	{
		driver.get(URL);		
	}
	
	protected boolean isExpectedPage(String pageTitle)
	{
		return(driver.getTitle().contains(pageTitle));
	}	
	
	//protected abstract void loadWebElements();
}
