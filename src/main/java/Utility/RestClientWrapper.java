package Utility;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClientWrapper {

	public  String resource;
	public  String baseUrl;
	private  RequestSpecification request;
	private  Response restResponse;

	public RestClientWrapper(String baseUrl, RequestSpecification request) {
		this.request = request;
		this.request.baseUri(baseUrl);
	}
	
	public void addPathParame(String paramName, String paramValue) {
		this.request.pathParam(paramName, paramValue);
	}
	
	
	
	

	public Response get(String resource) throws Exception {

		restResponse = request.log().all().when().get(resource);

		return restResponse;
	}
	
	
	public Response post(String resource, String body) throws Exception {

		restResponse = request.when().body(body).post(resource);

		return restResponse;
	}

	public Response put(String resource, String body) throws Exception {

		restResponse = request.when().body(body).put(resource).then().log().all().extract().response();

		return restResponse;
	}
	
	public Response delete(String resource) throws Exception {

		restResponse = request.log().all().when().delete(resource);

		return restResponse;
	}

}
