package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class placeOrder extends AbstractComponent{
	
	WebDriver driver;
	
	public placeOrder (WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div .user__address div .text-validated")
	WebElement countryTxt;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
		
	public confirmationPage provideOrderInfo(String countryName)
	{
//		Actions acn = new Actions(driver);
//		acn.sendKeys(country, "India").build().perform();
		
		waitForWebElementToAppear(countryTxt);
		countryTxt.click();
		
		System.out.println("Inside provideOrderInfo");
		
		Actions acn = new Actions(driver);
		
		acn.sendKeys(countryTxt, countryName).build().perform();
		
		country.click();
		submit.click();
		
		return new confirmationPage(driver);
	}

}





