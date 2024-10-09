package base;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Assertions.Assert_Actions;
import endpoints.APIConstants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import modules.PayloadManager;


public class BaseTest {
	
	public PayloadManager payloadManager;
	public Assert_Actions assert_Actions;
	public RequestSpecification requestSpecification;
    public Response response;
    public ValidatableResponse validatableResponse;
	
	
	@BeforeTest
	public void setup() {
		
		payloadManager = new PayloadManager();
		assert_Actions = new Assert_Actions();
		
		requestSpecification = new RequestSpecBuilder()
				.setBaseUri(APIConstants.Base_URL)
				.addHeader("Content-Type", "application/json")
				.build().log().all();
	}
	
	public String getToken() {
		
		requestSpecification = RestAssured
				.given()
				.baseUri(APIConstants.Base_URL)
				.basePath(APIConstants.Auth_URL);
		
		String payload = payloadManager.setAuthPayload();
		
		response = requestSpecification.contentType(ContentType.JSON).body(payload).when().post();
		
		String token = payloadManager.getTokenFromJson(response.asString());
		
		
				
		return token;
		
	}

}
