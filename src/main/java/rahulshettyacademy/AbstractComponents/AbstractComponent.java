package rahulshettyacademy.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.cartItemsPage;
import rahulshettyacademy.pageobjects.orderItemsPage;

public class AbstractComponent {
	WebDriver driver;
	
	
	public AbstractComponent(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "button[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "button[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(5));
		wt.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	//	System.out.println("I am waiting here");
		
		
	}
	
	public void waitForElementToDisappear(WebElement webElm) throws InterruptedException
	{
	//	Thread.sleep(1000);
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(5));
		wt.until(ExpectedConditions.invisibilityOf(webElm));
		
	}

	public void waitForWebElementToAppear(WebElement webElm)
	{
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		wt.until(ExpectedConditions.visibilityOf(webElm));		
			
	}
	
	public void waitForWebElementListToAppear(List<WebElement> webElmLst)
	{
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		wt.until(ExpectedConditions.visibilityOfAllElements(webElmLst));
		
	}
	
	public cartItemsPage goToCart()
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
		cartItemsPage cartItemsPage = new cartItemsPage(driver);
		cartHeader.click();
		return cartItemsPage;
	}

	public orderItemsPage goToOrders()
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
		orderItemsPage orderItemsPage = new orderItemsPage(driver);
		orderHeader.click();
		return orderItemsPage;
	}
}	



