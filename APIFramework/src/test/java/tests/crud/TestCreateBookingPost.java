package tests.crud;

import org.hamcrest.Matchers;

import org.testng.Assert;

import org.testng.annotations.Test;

import base.BaseTest;
import com.google.gson.Gson;
import endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import modules.PayloadManager;
import pojos.BookingResponse;
import utils.PropertyReader;

public class TestCreateBookingPost extends BaseTest {
	
	
	@Owner("yadraj")
	@Description("verify post request working fine")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void testPost() {
		
		
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
		
		
	}

}
