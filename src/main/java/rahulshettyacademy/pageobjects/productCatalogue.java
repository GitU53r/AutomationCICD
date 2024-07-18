package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;


public class productCatalogue extends AbstractComponent{
	
	WebDriver driver;
	
	public productCatalogue(WebDriver driver) {
		
		super(driver);//sending driver info to the parent
		
		//the 'driver' info is coming from SubmitOrderTest.java
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

			
	//List<WebElement> prodList =  driver.findElements(By.cssSelector("div.mb-3"));
	//The above line of code is done using FindBy annotation as below.
	
	@FindBy(css = "div.mb-3")
	List<WebElement> prodList;
	
	By prodListBy = By.cssSelector("div.mb-3");
	By addToCart = By.cssSelector("div.card-body button:last-of-type") ;
	By toastMsg = By.id("toast-container");
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	//Action method
	public List<WebElement> getProdListAction()
	{
		waitForElementToAppear(prodListBy);
		return prodList;
	}
	
	public WebElement getProdByName(String ProdName)
	{
		WebElement prodNme = 
				getProdListAction().stream()
				.filter(pr -> pr.findElement(By.cssSelector("b")).getText().equals(ProdName))
				.findFirst()
				.orElse(null);
		//System.out.println("prodNme: "+prodNme.getText());
		return prodNme;
	}
	
	public void addProdToCart(String ProdName) throws InterruptedException
	{
		WebElement prod = getProdByName(ProdName);
		prod.findElement(addToCart).click(); 
		waitForElementToAppear(toastMsg);
		waitForElementToDisappear(spinner);
		
		//return new cartItemsPage(driver);
				
	}
	

}
