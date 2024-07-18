package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;


public class orderItemsPage extends AbstractComponent{
	
WebDriver driver;


public orderItemsPage(WebDriver driver) {
	
	super(driver);	
	this.driver = driver;
	PageFactory.initElements(driver,this);
	System.out.println("I am in orders items Page");
		
	}

//picks up the list of ordered items.
@FindBy(xpath="//tr/td[2]")
List<WebElement> orderItemsList;

/*
public List<WebElement> orderListPresent()
	{
	
	waitForWebElementListToAppear(orderItemsList);

	return orderItemsList;
	//	
	
	}
*/

public Boolean verifyOrdersList(String prodName)
{
	System.out.println("Inside verifyOrdersList");
	//List<WebElement> prod = orderListPresent();
	
	String orderItem = orderItemsList.get(0).getText();
	
	System.out.println("orderItem"+ orderItem);
	
	Boolean match = orderItemsList.stream().anyMatch(pl -> pl.getText().equalsIgnoreCase(prodName));
	return match;
		
	
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
















