package modules;

import com.github.javafaker.Faker;

import pojos.Auth;
import pojos.Booking;
import pojos.Bookingdates;
import pojos.TokenResponse;

import com.google.gson.Gson;


public class PayloadManager {
	
	public  String  payloadBookingAsString() {
		
		
		Booking booking = new Booking();
        booking.setFirstname("yadraj");
        booking.setLastname("shinde");
        booking.setDepositpaid(true);
        booking.setTotalprice(400);
        booking.setAdditionalneeds("Lunch");


        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2018-01-01");

        booking.setBookingdates(bookingdates);

        Gson gson = new Gson();
        String payload_gson = gson.toJson(booking);
        System.out.println(payload_gson);
        return payload_gson;

	}
	
public  String  putpayloadBookingAsString() {
		
		
		Booking booking = new Booking();
        booking.setFirstname("raj");
        booking.setLastname("shinde");
        booking.setDepositpaid(true);
        booking.setTotalprice(400);
        booking.setAdditionalneeds("Lunch");


        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2018-01-01");

        booking.setBookingdates(bookingdates);

        Gson gson = new Gson();
        String payload_gson = gson.toJson(booking);
        System.out.println(payload_gson);
        return payload_gson;

	}
	
	public String  payloadBookingAsStringFaker() {
	
		Faker faker = new Faker();
		
		Booking booking = new Booking();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setDepositpaid(true);
        booking.setTotalprice(faker.random().nextInt(1000));
        booking.setAdditionalneeds("Lunch");


        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("23-1-024");
        bookingdates.setCheckout("23-1-024");

        booking.setBookingdates(bookingdates);

        Gson gson = new Gson();
        String payload_gson = gson.toJson(booking);
        System.out.println(payload_gson);
        return payload_gson;

	}
	
	
	public String setAuthPayload() {
		
	
		Auth auth = new Auth();
		auth.setUsername("admin");
		auth.setPassword("password123");
		Gson gson = new Gson();
		String authJson = gson.toJson(auth);
		System.out.println("authpayload set to "+authJson);
		return authJson;
		
	}
	
	
	public String getTokenFromJson(String tokenResponse) {
		
		Gson gson = new Gson();
		TokenResponse  tokenResponse1=gson.fromJson(tokenResponse, TokenResponse.class);
		return tokenResponse1.getToken();
		
	}
	
	
	public Booking getResponseFromJson(String getResponse) {
		
		Gson gson = new Gson();
		Booking  booking=gson.fromJson(getResponse, Booking.class);
		return booking;
		
	}
	
	
	
	
	
	
	
	
	

}
