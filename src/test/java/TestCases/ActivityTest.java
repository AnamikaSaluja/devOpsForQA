package TestCases;

import java.io.IOException;

import org.apache.log4j.Logger;
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

public class ActivityTest extends Base {

	public RestClientWrapper restClient;
	private static String activityID;
	public Response serverResponse = null;
	
	public static String getValueFromResponse(Response serverResponse, String id)
		{
			JsonPath js=new JsonPath(serverResponse.asString());
			String valueFromResponse=js.getString(id);
			return valueFromResponse;
		}

	@BeforeMethod
	public void Header()
	{
		httpRequest.header("Authorization",authToken).header("User-Token",userToken).header("Content-Type","application/json");
		restClient=new RestClientWrapper(baseUrl,httpRequest); 
	}
	
	@Test(priority=9) 
	public void getActivity() throws Exception
	{
		serverResponse =restClient.get(Constant.getActivity);
		activityID=getValueFromResponse(serverResponse , "activities[0].activity_id");
		
		Assert.assertEquals(serverResponse.statusCode(),200);
		Assert.assertEquals(serverResponse.statusLine(),"HTTP/1.1 200 OK");
		Assert.assertEquals(serverResponse.getContentType(),"application/json; charset=utf-8");
		logger.info("Inside GetActivity");
	
	}
	
	@Test(priority=10) 
	public void deleteActivity() throws Exception
	{

		String resourceId=Constant.getActivity;
		serverResponse=restClient.delete(resourceId+activityID);
		  		  
		Assert.assertEquals(serverResponse.statusCode(),200);
		Assert.assertEquals(serverResponse.statusLine(),"HTTP/1.1 200 OK");
		Assert.assertEquals(serverResponse.getContentType(),"application/json; charset=utf-8");
		logger.info("Inside deleteActivity");
	
	}
	
	@Test(priority=11) 
	public void followActivity() throws Exception
	{
		serverResponse=restClient.put(Constant.followActivity,Body.retBlankBody());
		 
		Assert.assertEquals(serverResponse.statusCode(),200);
		Assert.assertEquals(serverResponse.statusLine(),"HTTP/1.1 200 OK");
		Assert.assertEquals(serverResponse.getContentType(),"application/json; charset=utf-8");
		logger.info("Inside followActivity");
	
	
	}

}
