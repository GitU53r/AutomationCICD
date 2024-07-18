package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class screenshotTest {
	
	public static void main (String[] args) throws IOException
	{
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		
				
		driver.findElement(By.id("userEmail")).sendKeys("navani@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Prajaan@10");
		driver.findElement(By.id("login")).click();
		
	//	Assert.assertEquals("Incorrect email orr password.", "Incorrect email or password.");
		TakesScreenshot tss = (TakesScreenshot)driver;
		
		File source = tss.getScreenshotAs(OutputType.FILE); //The screenshot is taken as a 'FILE'
		
		System.out.println("Working Directory: "+ System.getProperty("user.dir"));
		
		//The destination path must be a FILE OBJECT.
		File destFilePath = new File("C:\\Users\\prana\\eclipse-workspace\\SeleniumFrameworkDesign\\reports\\loginPageTest.png");
		/*File destFilePath = 
				new File(System.getProperty("C:\\Users\\prana\\eclipse-workspace\\SeleniumFrameworkDesign\\reports\\" + testCaseName + ".png"));
		*/
		//The generated FILE is copied to the local path.
		FileUtils.copyFile(source, destFilePath);
		
				
	}

}
