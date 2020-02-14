package endpoints;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import utils.Constants;
import utils.DefaultConfiguration;

import static org.hamcrest.CoreMatchers.is;

public class BaseEndPoints {
    protected static final String BASE_URL = DefaultConfiguration.getProperty("base_url");
    private static String authToken;

    public String getBaseURL() {
        return BASE_URL;
    }

    /**
     * Set Content-Type in header of the request as JSON
     */
    public RequestSpecification getRequestWithJSONHeaders() {
        RequestSpecification r = RestAssured.given();
        r.header("Content-Type", "application/json");
        return r;
    }

    /**
     * Set Content-Type in header of the request as XML
     */
    public RequestSpecification getRequestWithXMLHeaders() {
        RequestSpecification r = RestAssured.given();
        r.header("Content-Type", "application/xml");
        return r;
    }

    /**
     * Convert POJO to JSON
     */
    protected JSONObject createJSONPayload(Object pojo) {
        return new JSONObject(pojo);
    }

    /**
     * Returns authentication token by log in with the username and
     * password provided in config.properties
     * Authentication token is required for update and delete requests
     */
    public String getAuthorizationToken() {
        if (authToken == null || authToken.length() < 1) {
            String url = getBaseURL() + "auth";
            JSONObject jsonObj = new JSONObject()
                    .put("username", DefaultConfiguration.getProperty("username"))
                    .put("password", DefaultConfiguration.getProperty("password"));
            RequestSpecification request = getRequestWithJSONHeaders().given().body(jsonObj.toString());
            Response response = sendRequest(request, Constants.RequestType.POST_REQUEST, url, null);
            authToken = new JsonPath(response.asString()).getString("token");
        }

        return authToken;
    }

    /**
     * Verify that the response code is the same as expected code
     * by comparing the provided expected code and the response code
     * from the response received by sending the request
     */
    public void verifyResponseStatusCode(Response response, int expectedCode) {
        Assert.assertThat(response.getStatusCode(), is(expectedCode));
    }

    /**
     * Send request
     * @param request
     * @param requestType of the request. i.e GET, POST, PUT, DELETE, UPDATE
     * @param url to execute for the request
     * @param pojo if provided will be added to the body of request as JSON payload
     * @return response received from the service by sending the request
     */
    public Response sendRequest(RequestSpecification request, int requestType, String url, Object pojo) {
        Response response;

        // Add the Json to the body of the request
        if (pojo != null) {
            String payload = createJSONPayload(pojo).toString();
            request.body(payload);
        }

        // need to add a switch based on request type
        switch (requestType) {
            case Constants.RequestType.POST_REQUEST:
                if (request == null) {
                    response = RestAssured.when().post(url);
                } else {
                    response = request.post(url);
                }
                break;
            case Constants.RequestType.DELETE_REQUEST:
                if (request == null) {
                    response = RestAssured.when().delete(url);
                } else {
                    response = request.delete(url);
                }
                break;
            case Constants.RequestType.PUT_REQUEST:
                if (request == null) {
                    response = RestAssured.when().put(url);
                } else {
                    response = request.put(url);
                }
                break;
            case Constants.RequestType.GET_REQUEST:
            default:
                if (request == null) {
                    response = RestAssured.when().get(url);
                } else {
                    response = request.get(url);
                }
                break;
        }
        return response;
    }

}
