package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	static ExtentReports extent;
	
	public static ExtentReports getReportObject()
	{
		//ExtentReports, ExtentSparkReporter
		
		//Create object for ExtentSparkReporter. The constructor expects the 'path' where the report will be generated.
		
		String path = System.getProperty("user.dir") + "//reports//index.html"; //The index.html file will contain the generated report.
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		//Below is the main reports class. The report generated above is attached to this class.
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "RRR"); //Name info of the tester
		return extent;
		
	}

}
