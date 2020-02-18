package steps;

import endpoints.BaseEndPoints;
import io.cucumber.java.en.Given;
import org.eclipse.jetty.http.HttpStatus;
import utils.Constants;

public class CommonSteps {
    BaseEndPoints be = new BaseEndPoints();

    @Given("Booking API is available")
    public void booking_API_is_available() {
        String url = be.getBaseURL() + "ping";
        be.sendRequest(null, Constants.RequestType.GET_REQUEST, url, null).then().statusCode(HttpStatus.CREATED_201);
    }
}
