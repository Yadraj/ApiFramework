package Assertions;

import static org.testng.Assert.assertEquals;

import io.restassured.response.Response;

public class Assert_Actions {
	
	public void verifyResponseBody(String actual,String expected,String description) {
		assertEquals(actual,expected,description);
		
	}
	public void verifyResponseBody(int actual,int expected,String description) {
		assertEquals(actual,expected,description);
		
	}
//	public void verifyStatusCode(Response response,Integer expected) {
//		assertEquals(response.getStatusCode(),expected);
//	
//	}

}
