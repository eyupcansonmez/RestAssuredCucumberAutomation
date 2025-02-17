package com.hotelreservations.steps;

import com.hotelreservations.models.BookingResponse;
import com.hotelreservations.services.ReservationService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class ReservationSteps {
    ReservationService reservationService;
    String authKey;

    BookingResponse bookingResponse;

    @Given("Kullanici yeni bir rezervasyon olusturuyor")
    public void cagriBaslangici(){

         reservationService = new ReservationService();

    }

    @Given("Kullanici rezervasyon icin gereken bilgileri veriyor")
    public void createAuth(){

        authKey = reservationService.generateToken();

    }

    @When("Kullanici otel rezervasyonu yaratiyor")
    public void createReservation(){

        bookingResponse = reservationService.createBooking();

    }

    @Then("Rezervasyon basarili sekilde olusturuldu")
    public void reservationAssertions(){

        Assertions.assertEquals("Can",bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Sonmez",bookingResponse.getBooking().getLastname());
        Assertions.assertEquals(1000,bookingResponse.getBooking().getTotalprice());
        Assertions.assertFalse(bookingResponse.getBooking().isDepositpaid());
        Assertions.assertEquals("Köpek Yatagi",bookingResponse.getBooking().getAdditionalneeds());

    }


    @Then("Kullanici olusturulan rezervasyonu iptal ediyor")
    public void cancelResevation(){

        reservationService.deleteReservations(authKey,bookingResponse.getBookingid());




}

}

