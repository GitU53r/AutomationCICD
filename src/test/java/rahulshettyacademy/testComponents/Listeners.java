package rahulshettyacademy.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	
	ThreadLocal<ExtentTest> extTestThrd = new ThreadLocal<ExtentTest>(); //ThreadLocal is a java class that makes the TESTS 'Thread safe'
	
	public void onTestStart(ITestResult result) {
	
		test = extent.createTest(result.getMethod().getMethodName());
		extTestThrd.set(test); //wrapping the ExtentTest Object (test) into ThreadLocal Object (extTestThrd).
		//Creating a unique Thread ID for every 'test' object
		
	  }

	  public void onTestSuccess(ITestResult result) {
		  
		  //test.log(Status.PASS, "SUCCESS message");
		  extTestThrd.get().log(Status.PASS, "SUCCESS message"); //replaced 'test' with 'extTestThrd.get()'
	   
	  }

	  public void onTestFailure(ITestResult result) {
		  
		//test.fail(result.getThrowable());
		  extTestThrd.get().fail(result.getThrowable()); //replaced 'test' with 'extTestThrd.get()'
		
		String SSFilePath = null;  //initializing the file path
	    
	    try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance()) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	   	    
	    //Take a SS and attach to the report on Failure
	    try {
	    	System.out.println("TAKING SCREENSHOT NOW!!!");
	    	SSFilePath = getScreenshot(result.getMethod().getMethodName(), driver); //calling this method() from BaseTest.java
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("In Catch Block");
			e.printStackTrace();
		}
	  
	    System.out.println("Adding the screen capture now!!");
	    System.out.println("SSFilePath: "+SSFilePath+ ":  "+result.getMethod().getMethodName());
	    
	    //test.addScreenCaptureFromPath(SSFilePath, result.getMethod().getMethodName());
	      extTestThrd.get().addScreenCaptureFromPath(SSFilePath, result.getMethod().getMethodName()); //replaced 'test' with 'extTestThrd.get()'
	    
	  }
	  
	  public void onFinish(ITestContext context) {
		  System.out.println("FLUSHING......!");
		   extent.flush();
		   //With this step, the listening stops and REPORT GENERATION is done.
		   //Without this, the report will not be generated.
		  }

}
