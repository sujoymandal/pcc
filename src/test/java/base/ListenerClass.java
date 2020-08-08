package base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;

public class ListenerClass extends BaseClass implements ITestListener {

	public void onTestStart(ITestResult result) {
		test=report.startTest(result.getInstanceName());

		if(!setTestCase(result.getInstanceName().substring(6, result.getInstanceName().length()))){
			throw new SkipException(result.getName());
		//System.out.println((result.getInstanceName().substring(6, result.getInstanceName().length())));
		//System.out.println(result.getName());
		}
	
	test.log(LogStatus.INFO, result.getName()+"  Started..");
		
	}

	public void onTestSuccess(ITestResult result) {
		String imagePath=takeScreenshot(result.getInstanceName());
		test.log(LogStatus.PASS, result.getName()+"			Passed!!! ",test.addScreenCapture(imagePath));
		report.endTest(test);
		quitBrowser();
		
	}

	public void onTestFailure(ITestResult result) {

		//String imagePath=takeScreenshot(result.getInstanceName());
		//test.log(LogStatus.FAIL, result.getName()+"         Failed!!! ",test.addScreenCapture(imagePath));
		test.log(LogStatus.FAIL, "failed");
		report.endTest(test);
		quitBrowser();
		
	}

	public void onTestSkipped(ITestResult result) {

		test.log(LogStatus.SKIP, result.getInstanceName()+"  Skipped!!!");
		report.endTest(test);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {

		setReport();
		
		
	}

	public void onFinish(ITestContext context) {

		report.flush();

	}

}
