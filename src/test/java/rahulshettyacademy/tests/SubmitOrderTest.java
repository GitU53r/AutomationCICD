package rahulshettyacademy.tests;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.AbstractComponents.AbstractComponent;
import rahulshettyacademy.pageobjects.cartItemsPage;
import rahulshettyacademy.pageobjects.confirmationPage;
import rahulshettyacademy.pageobjects.landingPage;
import rahulshettyacademy.pageobjects.orderItemsPage;
import rahulshettyacademy.pageobjects.placeOrder;
import rahulshettyacademy.pageobjects.productCatalogue;
import rahulshettyacademy.testComponents.BaseTest;
import rahulshettyacademy.pageobjects.placeOrder;
 

public class SubmitOrderTest extends BaseTest {
	
	//String prodName = "IPHONE 13 PRO";
	String countryName = "India";
	String confirmationMsg = "THANKYOU FOR THE ORDER.";
	

	@Test (dataProvider="getData", groups="purchase")
//	public static void main(String[] args) throws InterruptedException {
	
	
//	public void submitOrder(String email, String password, String prodName) throws IOException, InterruptedException
	// The above line of code is for taking the data from the previous getData(). It is modified as below to get data from HashMap.
	public void submitOrder(HashMap <String, String> map) throws IOException, InterruptedException
	{
		By confirmPageload = By.cssSelector(".cx-info-box");
		
		
	//	productCatalogue prodCat = landingPage.loginAction(email, password);
	// The above line of code is modified as below for HashMap.	
		productCatalogue prodCat = landingPage.loginAction(map.get("email"), map.get("password"));
		
		//productCatalogue page Actions		
		List<WebElement> prodList = prodCat.getProdListAction();
//				
		prodCat.addProdToCart(map.get("prodName"));
		System.out.println("Actions in product catalogue page done!!");
		
		cartItemsPage cartItemsPage = prodCat.goToCart();
//		
//		//cartItemsPage Actions
		Boolean match = cartItemsPage.verifyCartList(map.get("prodName"));
//		
		Assert.assertTrue(match);
		
		placeOrder placeOrder = cartItemsPage.goToCheckOut();
		
		//Place Order page Actions
	
		confirmationPage confPge = placeOrder.provideOrderInfo(countryName);
		
		Boolean messageMatch = confPge.checkConfirmationMessage(confirmationMsg);
		
		Assert.assertTrue(messageMatch);
		
				
	}
	

	@Test (dependsOnMethods = {"submitOrder"},dataProvider="getData")
	public void orderHistoryTest(HashMap<String, String> map)
	{
		// We want to check if the product added available in the 'Orders' page
		System.out.println("Calling Order List Check");
		
		productCatalogue prodCat = landingPage.loginAction(map.get("email"), map.get("password"));
		
		orderItemsPage orderItemsPage = prodCat.goToOrders();
		Boolean orderItemMatch = orderItemsPage.verifyOrdersList(map.get("prodName"));
		
		System.out.println(orderItemMatch);
		Assert.assertTrue(orderItemMatch);
	}
	
 
	//The below code is used to send different sets of data for the TCs to pick up and run.
	/*
	@DataProvider
	 public Object[][] getData()
	 {
		 return new Object[][] {{"navani@gmail.com", "Prajan@10", "ZARA COAT 3"},{"radraj@gmail.com", "Prajan@10", "ADIDAS ORIGINAL"}} ;
	 }
	*/
	
	// The below code uses HashMap, to send huge sets of data. The above code is used to send few sets.
	/*
	@DataProvider
	public Object[][] getData()
	{
		HashMap <String, String> map1 = new HashMap<String, String>();
		map1.put("email", "navani@gmail.com");
		map1.put("password", "Prajan@10");
		map1.put("prodName","IPHONE 13 PRO");
		
		HashMap <String, String> map2 = new HashMap<String, String>();
		map2.put("email", "radraj@gmail.com");
		map2.put("password", "Prajan@10");
		map2.put("prodName","ADIDAS ORIGINAL");
		
		
		
		//instead of sending the individual sets of data as in previous example, send the map object (that contains the sets of data)
		return new Object[][] {{map1},{map2}};
	}
	*/
	
	//The bleow code replaces the above two codes. The data is read from a JSON file.
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = 
				getJasonDataToMap(System.getProperty("user.dir")+ "//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	

}















