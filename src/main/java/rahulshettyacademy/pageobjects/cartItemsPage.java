package rahulshettyacademy.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class cartItemsPage extends AbstractComponent{
	
WebDriver driver;
	
public cartItemsPage(WebDriver driver) {
	
	super(driver);	
	this.driver = driver;
	PageFactory.initElements(driver,this);
	System.out.println("I am in cart Page");
		
	}

@FindBy(css="div.cartSection h3")
List<WebElement> cartProdList;


@FindBy(css=".totalRow button")
WebElement chkOutbutton;

By cartProdListBy = By.cssSelector("div.cartSection h3");

public List<WebElement> cartListMatch()
	{
	
	waitForElementToAppear(cartProdListBy);
	System.out.println("I am back");
	System.out.println(cartProdList.get(0).getText());
	return cartProdList;
	//	
	
	}

public Boolean verifyCartList(String prodName)
{
	System.out.println("Inside verifyCartList");
	List<WebElement> prod = cartListMatch();
	
	Boolean match = prod.stream().anyMatch(cl -> cl.getText().equalsIgnoreCase(prodName));
	return match;
		
	
}

public placeOrder goToCheckOut()
{
	chkOutbutton.click();
	return new placeOrder(driver);
}
	
/*	//	waitForElementsToAppear(cartProdList);
		System.out.println(prodName);
		
		List<WebElement> cartProd = driver.findElements(By.cssSelector("div.cartSection h3"));
		
		for(int i=0; i<cartProd.size(); i++)
		{
			System.out.println(cartProd.get(i).getText());
		}
		
		
		//Boolean match = cartProdList.stream().anyMatch(cp -> cp.getText().equalsIgnoreCase(prodName));
		
	//	System.out.println(match);
//		assertCalling(match);
//		chkOutbutton.click();
				
	}*/




}
















