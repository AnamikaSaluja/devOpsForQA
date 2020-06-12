package TestCases;

import java.io.IOException;

import org.apache.http.HttpStatus;
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

public class QuoteTest extends Base{
	
	private String authToken; 
	public RestClientWrapper restClient;
	public Response serverResponse = null;
	
		
	@BeforeMethod
	public void Header()
	{
		httpRequest.header("Authorization",authToken).header("User-Token",userToken).header("Content-Type","application/json");
		restClient=new RestClientWrapper(baseUrl,httpRequest); 
	}
	
	@Test(priority=4) 
	public void addQuote() throws Exception
	{
		String quotejson = readingJsonFile.readJSON("addQuote");
		serverResponse =restClient.post(Constant.addQuote, quotejson);
		
		Assert.assertEquals(serverResponse.statusCode(),200);
		Assert.assertEquals(serverResponse.statusLine(),"HTTP/1.1 200 OK");
		Assert.assertEquals(serverResponse.getContentType(),"application/json; charset=utf-8");
		logger.info("Inside AdQuote");
		 
	}
	
	@Test(priority=5) 
	public void FavouriteQuote() throws Exception
	{
		restClient.addPathParame("favId", propertyMap.get("favId"));
		serverResponse =restClient.put(Constant.favQuoteID, Body.retBlankBody());
		
		Assert.assertEquals(serverResponse.statusCode(),200);
		Assert.assertEquals(serverResponse.statusLine(),"HTTP/1.1 200 OK");
		Assert.assertEquals(serverResponse.getContentType(),"application/json; charset=utf-8");
		logger.info("Inside FavQuote");
		 
	} 
	
		
	@Test(priority=6) 
	public void hideQuote() throws Exception
	{
		serverResponse =restClient.put(Constant.hideQuote, Body.retBlankBody());
		
		Assert.assertEquals(serverResponse.statusCode(),200);
		Assert.assertEquals(serverResponse.statusLine(),"HTTP/1.1 200 OK");
		Assert.assertEquals(serverResponse.getContentType(),"application/json; charset=utf-8");
		logger.info("Inside HideQuote");
		 
	}

}
