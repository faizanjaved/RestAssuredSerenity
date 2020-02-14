package endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Booking;
import model.BookingDetail;
import net.thucydides.core.annotations.Step;
import org.json.JSONException;
import steps.World;
import utils.Constants;

public class BookingEndPoints extends BaseEndPoints{
    private final String BOOKING_ENDPOINT_PATH = "booking/";

    public String getPath() {
        return BOOKING_ENDPOINT_PATH;
    }

    public Response addBooking(World world) throws JSONException {
        world.setRequest(getRequestWithJSONHeaders());
        Response response = addBooking(world.getRequest());
        world.setResponse(response);
        return world.getResponse();
    }

    public Response addBooking(RequestSpecification request) throws JSONException {
        Booking booking = new Booking();
        return addBooking(request, booking);
    }

    @Step
    public Response addBooking(RequestSpecification request, Booking booking) throws JSONException {
        String url = getBaseURL() + getPath();
        if(request==null)
            request = getRequestWithJSONHeaders();

        return sendRequest(request, Constants.RequestType.POST_REQUEST, url, booking);
    }

    public Response deleteBooking(String bookingId, RequestSpecification request) {
        String url = getBaseURL() + getPath() + bookingId;
        if(request==null)
            request = getRequestWithJSONHeaders();
        request.header("Cookie", "token="+getAuthorizationToken());
        return sendRequest(request, Constants.RequestType.DELETE_REQUEST, url, null);
    }

    public Response updateBooking(String bookingId, Booking booking, RequestSpecification request) {
        String url = getBaseURL() + getPath() + bookingId;
        if(request==null)
            request = getRequestWithJSONHeaders();
        request.header("Accept", "application/json");
        request.header("Cookie", "token="+getAuthorizationToken());
        return sendRequest(request, Constants.RequestType.PUT_REQUEST, url, booking);
    }

    public String getBookingIdFromResponse(Response response) {
        BookingDetail bd = response.getBody().as(BookingDetail.class);
        return String.valueOf(bd.getBookingid());
    }

    public Response getBookingById(String bookingId, RequestSpecification request) {
        String url = getBaseURL() + getPath() + bookingId;
        if(request==null)
            request = getRequestWithJSONHeaders();
        return sendRequest(request, Constants.RequestType.GET_REQUEST, url, null);
    }

    @Step
    public boolean verifyBookingValuesAreAsExpected(Response response, Booking booking) {
        return booking.equals(response.getBody().as(Booking.class));
    }

}
