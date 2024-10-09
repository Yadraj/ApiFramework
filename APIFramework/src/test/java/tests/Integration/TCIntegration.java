package tests.Integration;

import org.hamcrest.Matchers;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import base.BaseTest;
import endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import modules.PayloadManager;
import pojos.Booking;
import pojos.BookingResponse;
import utils.PropertyReader;
import static org.assertj.core.api.Assertions.*;

public class TCIntegration  extends BaseTest {
	
	@Test(groups = "integration",priority=1)
	@Owner("yadraj")
	@Description("verify create Booking")
	public void testcreateBooking(ITestContext iTestContext) {
		
		iTestContext.setAttribute("token", getToken());
		
		requestSpecification.basePath(APIConstants.Booking_URL);
		System.out.println("before RA");
		response = RestAssured.given(requestSpecification).when().body(payloadManager.payloadBookingAsString())
				.post();
		System.out.println("before response");
		
		validatableResponse = response.then().log().all();
		
		System.out.println("after response");
		validatableResponse.statusCode(200);
		System.out.println(response.asString());
		
		validatableResponse.body("booking.firstname", Matchers.equalTo(PropertyReader.readKey("booking.post.firstname")));
		
		
		
		Gson gson = new Gson();
		BookingResponse bookingResponse = gson.fromJson(response.asString(),BookingResponse.class);
		System.out.println(bookingResponse.getBookingid());
		System.out.println(bookingResponse.getBooking().getFirstname());
		
		assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo(PropertyReader.readKey("booking.post.firstname"));
		
		iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());
		
		
		
		
		
	}
	@Test(groups = "integration",priority=2)
	@Owner("yadraj")
	@Description("verify get Booking")
	public void testGetRequest(ITestContext iTestContext) {
		
		Integer bookingID = (Integer) iTestContext.getAttribute("bookingid");
		
		String basePathGet = APIConstants.Booking_URL+ "/" + bookingID;
		System.out.println(basePathGet);
		requestSpecification.basePath(basePathGet);
		
		Response response  = RestAssured.given(requestSpecification).when().get();
	

        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        
        
        Booking booking = payloadManager.getResponseFromJson(response.asString());
        
        assertThat(booking.getFirstname()).isNotNull();
		
		
	}
	@Test(groups = "integration",priority=3)
	@Owner("yadraj")
	@Description("verify update Booking")
	public void testUpdateBooking(ITestContext iTestContext) {
		
		Integer bookingID = (Integer) iTestContext.getAttribute("bookingid");
		String token =(String) iTestContext.getAttribute("token");
		System.out.println(token);
		System.out.println(bookingID);
		
		String basePathPut = APIConstants.Booking_URL+ "/" + bookingID;
		System.out.println(basePathPut);
		requestSpecification.basePath(basePathPut);
		
		Response response  = RestAssured.given(requestSpecification).
							cookie("token",token).
							when().
							body(payloadManager.putpayloadBookingAsString()).put();
		

        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        
        
        Booking booking = payloadManager.getResponseFromJson(response.asString());
        
        assertThat(booking.getFirstname()).isNotNull();
        assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readKey("booking.put.firstname"));
        
		
		
		
		
		
	}
	@Test(groups = "integration",priority=4)
	@Owner("yadraj")
	@Description("verify Delete Booking")
	public void testDeleteBooking(ITestContext iTestContext) {
		
		String token =(String) iTestContext.getAttribute("token");
		System.out.println(token + "token present in delete method");
		
		Integer bookingID = (Integer) iTestContext.getAttribute("bookingid");
		

		String basePathDelete = APIConstants.Booking_URL+ "/" + bookingID;
		System.out.println(basePathDelete);
		requestSpecification.basePath(basePathDelete);
		System.out.println(basePathDelete+" basePathDelete");
		
		
		
		
		 validatableResponse = RestAssured.given(requestSpecification).
				cookie("token", token).
				when().delete()
				.then().log().all();
		
        validatableResponse.statusCode(201);
		
		
		
		
		
		
	}

}
