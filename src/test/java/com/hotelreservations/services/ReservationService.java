package com.hotelreservations.services;

import com.hotelreservations.models.Auth;
import com.hotelreservations.models.Booking;
import com.hotelreservations.models.BookingDates;
import com.hotelreservations.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReservationService extends BaseTest {

    // token olustur

    public String generateToken() {

        Auth authBody = new Auth("admin", "password123");


        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(authBody)
                .post("/auth");


        response
                .then().statusCode(200);


        return response.jsonPath().getJsonObject("token");

    }

    // rezervasyon olustur

    public BookingResponse createBooking() {

        BookingDates bookingDates = new BookingDates("2024-04-01", "2024-05-01");

        Booking booking = new Booking("Can", "Sonmez", 1000, false, bookingDates, "Köpek Yatagi");

            Response response = given(spec)
                    .contentType(ContentType.JSON)
                    .when()
                    .body(booking)
                    .post("/booking");

            response
                    .then()
                    .statusCode(200);

            return response.as(BookingResponse.class);


    }

    // rezervasyon silme

    public void deleteReservations(String token, int bookingId){

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token="+token)
                .when()
                .delete("/booking/"+bookingId);

        response
                .then()
                .statusCode(201);



    }


}
