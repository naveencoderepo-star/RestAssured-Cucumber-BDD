package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.AddPlace;
import pojo.Location;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StepDefinition {
    private RequestSpecification req;
    private Response resp;



    @Given("AddPlaceAPI is available with payload")
    public void add_place_api_is_available_with_payload() {


        RestAssured.baseURI = "https://rahulshettyacademy.com";
        req = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"location\":{\"lat\":-38.383494,\"lng\":33.427362},\"accuracy\":50,\"name\":\"Frontline house\",\"phone_number\":\"(+91) 983 893 3937\",\"address\":\"29, side layout, cohen 09\",\"types\":[\"shoe park\",\"shop\"],\"website\":\"http://google.com\",\"language\":\"French-IN\"}");
    }

    @When("user call AddPlaceAPI with valid post http request method,")
    public void user_call_add_place_api_with_valid_post_http_request_method() {
        // Adjust the path to match your API    
        resp = req.when().post("/maps/api/place/add/json");
    }

    @Then("the API call is successful and response status code is {int}")
    public void the_api_call_is_successful_and_response_status_code_is(Integer code) {
        resp.then().statusCode(code);
    }

    @Then("Status in response body is OK")
    public void status_in_response_body_is_ok() {
        String status = resp.jsonPath().getString("status");
        assertThat(status, equalTo("OK"));
    }
}
