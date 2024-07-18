package rahulshettyacademy.testComponents;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import rahulshettyacademy.pageobjects.landingPage;


public class BaseTest {

	public static WebDriver driver; //global declaration of driver
	public landingPage landingPage;
	
	@Test
	public WebDriver initializeDriver() throws IOException
	{
		
		//Properties class can read the global properties
		
		Properties prop = new Properties();		
		FileInputStream fis =
				new FileInputStream("C:\\Users\\prana\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
				
		// Instead of giving the entire path as above -- we can use "user.dir" from system.property.
		// user.dir -- provides the eclipse path until the eclipse project location.
				
//			//	System.out.println(System.getProperty("user.dir"));
//				new FileInputStream(System.getProperty
//						("user.dir") + "src/main/java/rahulshettyacademy/resources/GlobalData.properties");
		prop.load(fis);
		//String browserName = prop.getProperty("browser");
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//Now the browser information can be taken from either the globaldata.properties file or from maven commands
		
		//if(browserName.equalsIgnoreCase("chrome"))
		
		
	/*	if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");	//this is added to avoid webSocket errors
				if(browserName.contains("headless"))
				{
					options.addArguments("headless");
				}	
			WebDriverManager.chromedriver().setup(); //Please go through Lecture 148. Selenium Program on WebDriverManager - Login- Get Products List
			driver = new ChromeDriver(options);
			
			driver.manage().window().setSize(new Dimension(1440,900));//full screen when in headless mode
		
		} */
		
		
	//Below code modified a little bit (lines of code shuffled) to fix landingPage null pointer exception	
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");	//this is added to avoid webSocket errors
			WebDriverManager.chromedriver().setup(); //Please go through Lecture 148. Selenium Program on WebDriverManager - Login- Get Products List
				if(browserName.contains("headless"))
				{
					options.addArguments("headless");
					driver = new ChromeDriver(options);
				}	
			
			driver = new ChromeDriver(options);
			
			driver.manage().window().setSize(new Dimension(1440,900));//full screen when in headless mode
		
		}
		
		
		
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			//invoke firefox driver
		}
		else
		{
			//invoke edge driver
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJasonDataToMap(String filePath) throws IOException
	{
		//reading json to String
		String jsonContent =
				FileUtils.readFileToString(new File(filePath),
						 StandardCharsets.UTF_8);
		
		//convert this string to Hashmap Objects - using Jackson databind
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data =  mapper.readValue(jsonContent, 
				new TypeReference<List<HashMap<String, String>>>()
				{
			
				});
		
		return data;
	}
	
	@BeforeMethod (alwaysRun=true)
	public landingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		if (driver!=null)
		{
		landingPage = new landingPage(driver);		
		landingPage.goToPage();	
		
		return landingPage;
		}
		else
		{
			return null;
			
		}
	}
	@AfterMethod (alwaysRun=true)
	public void closeDriver()
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		//driver.close();
		
		System.out.println("Process done, but page is left open");
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		System.out.println("inside getScreenshot():  "+testCaseName+ "   driverObject: "+driver);
		TakesScreenshot tss = (TakesScreenshot)driver;
		File source = tss.getScreenshotAs(OutputType.FILE); //The screenshot is taken as a 'FILE'
		
		System.out.println("Working Directory: "+ System.getProperty("user.dir"));
		
		//The destination path must be a FILE OBJECT.
		File destFilePath = new File("C:\\Users\\prana\\eclipse-workspace\\SeleniumFrameworkDesign\\reports\\" + testCaseName + ".png");
		/*File destFilePath = 
				new File(System.getProperty("C:\\Users\\prana\\eclipse-workspace\\SeleniumFrameworkDesign\\reports\\" + testCaseName + ".png"));
		*/
		//The generated FILE is copied to the local path.
		//FileUtils.copyFileToDirectory(source, destFilePath);
		FileUtils.copyFile(source, destFilePath);
		return "C:\\Users\\prana\\eclipse-workspace\\SeleniumFrameworkDesign\\reports\\" + testCaseName + ".png"; //The path of the file is returned.
		//return System.getProperty("C:\\Users\\prana\\eclipse-workspace\\SeleniumFrameworkDesign\\reports\\" + testCaseName + ".png");
				
	}
}


















