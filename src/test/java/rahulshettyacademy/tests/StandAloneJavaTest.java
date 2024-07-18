package rahulshettyacademy.tests;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.landingPage;
 

public class StandAloneJavaTest {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		
				
		driver.findElement(By.id("userEmail")).sendKeys("navani@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Prajan@10");
		driver.findElement(By.id("login")).click();
		
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	//For ZARA COAT (Product 1 on the page -- index is 1)	
		//driver.findElement(By.xpath("//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted'][1]//button[2]")).click();
	//For ZARA COAT (Product 1 on the page -- index is 2)	
		//driver.findElement(By.xpath("//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted'][2]//button[2]")).click();
	//For ZARA COAT (Product 1 on the page -- index is 3)	
		//driver.findElement(By.xpath("//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted'][3]//button[2]")).click();
		
		/*List<WebElement> prodList = driver.findElements
				(By.xpath("//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']"));
		
		for (int i=0; i<prodList.size(); i++)
		{
			if (prodList.get(i).getText().contains("ADIDAS"))
					{
			//	prodList.get(i).findElement(By.cssSelector("div.card-body button:last-of-type")).click();
			//	prodList.get(i).findElement(By.xpath("//button[2]")).click();
			 	prodList.get(i).findElement(By.cssSelector("div.card-body button:nth-child(4)")).click();
			 
					}
		}*/
		
	// The above code to identify the specific products, is rewritten below using Streams	
		
			
	    List<WebElement> prodList =  driver.findElements(By.cssSelector("div.mb-3"));		
			
		WebElement prodNme =  prodList.stream().filter(pr -> pr.findElement(By.cssSelector("b")).getText().equals("IPHONE 13 PRO")).findFirst().orElse(null);
		
		System.out.println(prodNme.getText());
		
		prodNme.findElement(By.cssSelector("div.card-body button:last-of-type")).click(); 
		//prodNme.findElement(By.xpath("div[@class='card-body']/button[2]")).click();
		//prodNme.findElement(By.cssSelector("div.card-body button:nth-child(4)")).click();
		
		//Giving 'Explicit Wait' for the webdriver to wait until the TOAST MESSAGE is disappeared.
		
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(5));
					
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		
		//ng-animating -- this is the class name for the 'processing' animation
		wt.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//clicking the cart button to open the cart page.
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		//To check if the items selected in the shopping page is ALSo in the cart page,
		List<WebElement> cartProd = driver.findElements(By.cssSelector("div.cartSection h3"));
		
		System.out.println(cartProd.get(0).getText());
		//System.out.println(cartProd.get(1).getText());
		//System.out.println(cartProd.get(2).getText());
		
		/*The filter() returns a webelement. But we want to check the boolean condition. 
		*cartProd.stream().filter(cp -> cp.getText().equalsIgnoreCase("IPHONE 13 PRO"));
		*/
		
		Boolean match = cartProd.stream().anyMatch(cp -> cp.getText().equalsIgnoreCase("IPHONE 13 PRO"));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click(); //Takes us to the PLACE ORDER page.
		
		//Filling the country details. Using the usual for/if loop logic.
	/*	driver.findElement(By.cssSelector("div .user__address div .text-validated")).sendKeys("ind");
		
		List<WebElement> countryList = driver.findElements(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']"));
		
		for (WebElement country :countryList)
		{
			if (country.getText().equalsIgnoreCase("indonesia"))
				country.click();	
			break;
		}
		*/
		
		//Using Actions Class the same action is repeated.
		
		Actions acn = new Actions(driver);
		
		acn.sendKeys(driver.findElement(By.cssSelector("div .user__address div .text-validated")), "India").build().perform();
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		 driver.findElement(By.cssSelector(".action__submit")).click();
		 
//		 Confirming if we landed on the right page after successful order placement.
		/*boolean mtch = driver.findElement(By.cssSelector(".hero-primary")).getText().contains("THANKYOU");
		Assert.assertTrue(mtch);
			*/
		 
//		 The above code can be written as below as well.
		 
		 String ConfirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		 Assert.assertTrue(ConfirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		 
		 wt.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cx-info-box")));
		 
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		 
		// driver.close();
	}
	
	

}
