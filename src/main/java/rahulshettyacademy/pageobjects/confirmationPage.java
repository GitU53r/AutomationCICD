package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class confirmationPage extends AbstractComponent {
	
	WebDriver driver;
	
	public confirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".hero-primary")
	WebElement messageDisplay;
	String confirmationMessage =  messageDisplay.getText();
	
	
	
	public Boolean checkConfirmationMessage(String confirmationMsg)
	{
		Boolean messageMatch =  confirmationMessage.equalsIgnoreCase(confirmationMsg) ;
		return messageMatch;
	}

}
