package rahulshettyacademy.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTests implements IRetryAnalyzer {
	
	int count =0;
	int maxTry = 1;
	
	public boolean retry(ITestResult result)
	{	
		if (count < maxTry)
		{
			count++;
			return true; //if the 'Test' needs to be RERUN the method() must return TRUE
		}
		return false;
	}

}
