package helpers;

public interface Constant {
	
	//logging
	public static String log4jConfPath="./log4j.properties";

	//Project level constants
	public static String authToken="Token token= {tokenId}";
	public static String reportPath = "./target/result.html";
	//public static String baseUri="https://favqs.com/";
	
	//User functions
	public static String postUser="api/users";
	public static String getUser="api/users/";
	
	
	//Quote functions
	public static String favQuoteID="api/quotes/{favId}/fav";
	public static String addQuote="api/quotes";
	public static String hideQuote="api/quotes/{favId}/hide";
	
	//Activity functions
	public static String getActivity="api/activities/";
	public static String followActivity="api/activities/follow";
	
	//passwords
	public static String forgotPwd="/api/users/forgot_password";
	public static String resetPwd="/api/users/reset_password";
}
