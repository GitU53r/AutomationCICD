package rahulshettyacademy.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.cartItemsPage;
import rahulshettyacademy.pageobjects.productCatalogue;
import rahulshettyacademy.testComponents.BaseTest;
import rahulshettyacademy.testComponents.RetryTests;
 

public class ErrorValidationsTest extends BaseTest {
	
	

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=RetryTests.class)	
	public void LoginErrorValidation() 
	{
		landingPage.loginAction("navani@gmail.com", "Praajan@10");
		Assert.assertEquals("Incorrect email orr password.", landingPage.getErrorMessage());
		
		//div[@aria-label='Incorrect email or password.']
	}
	
	@Test
	public void prodErrorValidation() throws InterruptedException
	{
		String prodName = "ZARA COAT 3";
		productCatalogue prodCat = landingPage.loginAction("radraj@gmail.com", "Prajan@10");
	
		//productCatalogue page Actions		
		List<WebElement> prodList = prodCat.getProdListAction();
//			
		prodCat.addProdToCart(prodName);
		System.out.println("Actions in product catalogue page done!!");
	
		cartItemsPage cartItemsPage = prodCat.goToCart();
//	
//		//cartItemsPage Actions
		Boolean match = cartItemsPage.verifyCartList("ZARA COAT 3");
//	
		Assert.assertTrue(match);
	}
	

}
