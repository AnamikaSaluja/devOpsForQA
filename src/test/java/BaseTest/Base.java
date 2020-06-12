package BaseTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import Utility.ReadingJsonFile;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utility.PropertyFileReading;
import helpers.Constant;
import Utility.ExtentReport;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class Base {
	public static String authToken;
	public static String baseUrl;
	protected static ReadingJsonFile readingJsonFile = new ReadingJsonFile();
	public static String userToken = null;
	protected static RequestSpecification httpRequest;
	public static ExtentReports rep = ExtentReport.getInstance();
	public static ExtentTest test;
	static String className = new Throwable().getStackTrace()[0].getClassName();
	public static final Logger logger = Logger.getLogger(className);

	public static HashMap<String, String> propertyMap;

	@BeforeSuite
	public static void initializeBrowser() throws FileNotFoundException, IOException {
		propertyMap = new PropertyFileReading()
				.getProperties(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
		authToken =propertyMap.get("authToken");
		PropertyConfigurator.configure(Constant.log4jConfPath);
		test = rep.startTest("starting");
	}

	@BeforeMethod
	public void readConstants(Method method) {
		baseUrl = propertyMap.get("baseUri");
		RestAssured.baseURI = baseUrl;
		httpRequest = RestAssured.given();
		test = rep.startTest(method.getName());
	}

	@AfterMethod
	public void reportFlush(ITestResult result) {
		System.out.println(result.getMethod().getMethodName());
		if (result.getStatus() == ITestResult.SUCCESS)
			test.log(LogStatus.PASS, "Test Case Passed");
		else if (result.getStatus() == ITestResult.FAILURE)
			test.log(LogStatus.FAIL, result.getThrowable());
		else if (result.getStatus() == ITestResult.SKIP)
			test.log(LogStatus.SKIP, result.getThrowable());

		rep.flush();
	}

	public static String getAuthorizationToken() {
//		authToken= Constant.authToken;
		return authToken;
	}

}
