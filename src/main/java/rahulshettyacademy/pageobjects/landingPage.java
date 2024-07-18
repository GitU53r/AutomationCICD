package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;


public class landingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public landingPage(WebDriver driver) {
		
		super(driver); //sending the driver information to the parent class
		//the 'driver' info is coming from SubmitOrderTest.java
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

			
	//WebElement email = driver.findElement(By.id("userEmail"));
	//The above line of code is done using FindBy annotation as below.
	@FindBy(id = "userEmail")
	WebElement emailId;
	

	//Same way for password and login button as well.
	@FindBy(id = "userPassword")
	WebElement pwd;
	
	@FindBy(id = "login")
	WebElement login;
	
	@FindBy(css="div[class*='flyInOut']")
	WebElement errorMsg;
	
	
	//This is the first method() called.
	public void goToPage()
	{
		driver.get("https://rahulshettyacademy.com/client/");
		System.out.println("Login page opened");
	}
	
	/*Defining an action method.
	* -- All the actions done in the SubmitOrderTest class for 'landingPage' is consolidated here.
	*/
	public productCatalogue loginAction(String email, String password)
	{
		emailId.sendKeys(email);
		pwd.sendKeys(password);
		login.click();
		
		System.out.println("Product Catalogue page opened");
		
		return new productCatalogue(driver);
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}

}
