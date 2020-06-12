package TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helpers.Constant;
import helpers.Body;
import Utility.RestClientWrapper;
import BaseTest.Base;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PasswordTest extends Base{
	
	private String authToken; 
	public RestClientWrapper restClient;
	public Response serverResponse = null;
	
	
	@BeforeMethod
	public void Header()
	{
		httpRequest.header("Authorization",authToken).header("User-Token",userToken).header("Content-Type","application/json");
		restClient=new RestClientWrapper(baseUrl,httpRequest); 
	}
	
	@Test(priority=7) 
	public void ForgotPassord() throws Exception
	{
		String passwordjson = readingJsonFile.readJSON("forgotPassword");
		serverResponse =restClient.post(Constant.forgotPwd,passwordjson);
		
		Assert.assertEquals(serverResponse.statusCode(),200);
		Assert.assertEquals(serverResponse.statusLine(),"HTTP/1.1 200 OK");
		Assert.assertEquals(serverResponse.getContentType(),"application/json; charset=utf-8");
		logger.info("Inside ForgotPassword");
	}
	
	@Test(priority=8) 
	public void ResetPassword() throws Exception
	{
		String passwordjson = readingJsonFile.readJSON("resetPassword");
		serverResponse =restClient.post(Constant.resetPwd, passwordjson);
		
		Assert.assertEquals(serverResponse.statusCode(),200);
		Assert.assertEquals(serverResponse.statusLine(),"HTTP/1.1 200 OK");
		Assert.assertEquals(serverResponse.getContentType(),"application/json; charset=utf-8");
		logger.info("Inside ResetPassword");
	}
	

}
