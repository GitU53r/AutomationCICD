package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.testComponents.BaseTest;
import rahulshettyacademy.pageobjects.cartItemsPage;
import rahulshettyacademy.pageobjects.confirmationPage;
import rahulshettyacademy.pageobjects.landingPage;
import rahulshettyacademy.pageobjects.placeOrder;
import rahulshettyacademy.pageobjects.productCatalogue;

public class StepDefinitionImpl extends BaseTest{
	
	//public landingPage landingPage;
	public productCatalogue prodCat;
	confirmationPage confPge;
	
	@Given("I landed on the Ecommerce page")
	public void I_landed_on_the_Ecommerce_page() throws IOException
	{
		landingPage = launchApplication();		
	}
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password (String username, String password)
	{
		prodCat = landingPage.loginAction(username, password);
	}
	
	@When ("^I add product (.+) to cart$")
	public void  I_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> prodList = prodCat.getProdListAction();
		prodCat.addProdToCart(productName);
	}
	
	 @When ("^Checkout (.+) and submit the order$")
	 public void Checkout_and_submit_the_order(String productName)
	 {
			cartItemsPage cartItemsPage = prodCat.goToCart();			
			Boolean match = cartItemsPage.verifyCartList(productName);			
			Assert.assertTrue(match);			
			placeOrder placeOrder = cartItemsPage.goToCheckOut();
			confPge = placeOrder.provideOrderInfo("india");
	 }
	 
	 @Then("{string} message is displayed on the confirmation page")
	 public void message_is_displayed_on_the_confirmation_page(String string)
	 {
		 Boolean messageMatch = confPge.checkConfirmationMessage(string);
		 Assert.assertTrue(messageMatch);
	 }
	
	
}
