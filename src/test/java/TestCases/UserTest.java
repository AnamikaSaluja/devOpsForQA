package TestCases;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helpers.Constant;
import Utility.RestClientWrapper;

import BaseTest.Base;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class UserTest extends Base{
	
	public RestClientWrapper restClient;
	private static String paramValue;
	public Response serverResponse = null;

	@BeforeMethod
	public void Header()
	{
		httpRequest.header("Authorization",authToken).header("Content-Type","application/json");
		restClient= new RestClientWrapper(baseUrl,httpRequest);
	}
		

	@Test(priority=1)
	public void AddUser() throws Exception{
		
		String userjson = readingJsonFile.readJSON("createUser");
		serverResponse =restClient.post(Constant.postUser, userjson);
		
		Assert.assertEquals(serverResponse.statusCode(),200);
		Assert.assertEquals(serverResponse.statusLine(),"HTTP/1.1 200 OK");
		Assert.assertEquals(serverResponse.getContentType(),"application/json; charset=utf-8");
		logger.info("Inside AddUser");
		
	}
	
	
	@Test(priority=2) 
	public void GetUser() throws Exception{
		
		
		serverResponse  = restClient.get(Constant.getUser + paramValue);
		
		Assert.assertEquals(serverResponse.statusCode(),200);
		Assert.assertEquals(serverResponse.statusLine(),"HTTP/1.1 200 OK");
		Assert.assertEquals(serverResponse.getContentType(),"application/json; charset=utf-8");
		logger.info("Inside GetUser");
	
	}
	
	@Test(priority=3) 
	public void UpdateUser() throws Exception{
		
		String userjson = readingJsonFile.readJSON("updateUser");
		serverResponse  = restClient.put(Constant.getUser + paramValue, userjson);			

		Assert.assertEquals(serverResponse.statusCode(),200);
		Assert.assertEquals(serverResponse.statusLine(),"HTTP/1.1 200 OK");
		Assert.assertEquals(serverResponse.getContentType(),"application/json; charset=utf-8");
		logger.info("Inside UpdateUser");
		
	}
	

}
