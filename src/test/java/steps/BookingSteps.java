package steps;

import endpoints.BookingEndPoints;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Booking;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class BookingSteps {
    World world = new World();
    Booking booking = new Booking();
    String bookingId;
    List<Response> responseList = new ArrayList<>();
    BookingEndPoints bookingEndPoints = new BookingEndPoints();

    @When("I add a booking in the API")
    public void i_add_a_booking_in_the_API_with_following() {
        world.setBooking(booking);
        bookingEndPoints.addBooking(world);
    }

    @Then("the response has {int} response code")
    public void theResponseHasResponseCode(int code) {
        bookingEndPoints.verifyResponseStatusCode(world.getResponse(), code);
    }

    @Then("the booking requests response contains the correct json data")
    public void the_booking_requests_response_contains_the_correct_json_data() {
        if(ContentType.JSON.toString().contains(world.getResponse().contentType()) ||
                world.getResponse().contentType().contains(ContentType.XML.toString())) {
            Assert.assertTrue(bookingEndPoints.verifyBookingValuesAreAsExpected(world.getResponse(), world.getBooking()));
        }
    }

    @Then("the response has following response code")
    public void theResponseHasFollowingResponseCode(DataTable dt) {
        List<List<String>> rows = dt.asLists(String.class);
        for (int i = 1; i < dt.cells().size(); i++) {
            List<String> columns = rows.get(i);
            bookingEndPoints.verifyResponseStatusCode(responseList.get(i - 1), Integer.parseInt(columns.get(0)));
        }
    }


    @When("I add a booking in the API with following")
    public void iAddABookingInTheAPIWithFollowing(DataTable dt) {
        List<List<String>> rows = dt.asLists(String.class);
        for (int i = 1; i < dt.cells().size(); i++) {
            List<String> columns = rows.get(i);
            Booking bo = new Booking(columns.get(0), columns.get(1), Integer.parseInt(columns.get(2)), Boolean.parseBoolean(columns.get(3)), null, columns.get(4));
            responseList.add(bookingEndPoints.addBooking(null, bo));
        }
    }

    @When("I delete a booking in the API")
    public void iDeleteABookingInTheAPI() {
        Response response = bookingEndPoints.addBooking(world);
        bookingId = bookingEndPoints.getBookingIdFromResponse(response);
        world.setResponse(bookingEndPoints.deleteBooking(bookingId, null));
    }

    @When("getting the same booking with Id")
    public void gettingTheSameBookingWithId() {
        world.setResponse(bookingEndPoints.getBookingById(bookingId, null));
    }

    @When("I update booking with following")
    public void iUpdateBookingWithFollowing(List<String> boList) {
        Response response = bookingEndPoints.addBooking(world);
        bookingId = bookingEndPoints.getBookingIdFromResponse(response);
        Booking updatedBooking = new Booking(boList.get(0),
                boList.get(1),
                Integer.parseInt(boList.get(2)),
                Boolean.parseBoolean(boList.get(3)),
                null,
                boList.get(4));
        world.setResponse(bookingEndPoints.updateBooking(bookingId, updatedBooking, null));
    }
}
