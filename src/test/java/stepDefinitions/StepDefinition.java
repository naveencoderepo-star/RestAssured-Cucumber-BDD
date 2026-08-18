package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.config.RedirectConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.AddPlace;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class StepDefinition extends Utils {

    private RequestSpecification requestSpec;
    private Response apiResponse;
    private AddPlace addPlacePayload;
    TestDataBuild testDataBuild = new TestDataBuild();


    @Given("AddPlaceAPI is available with payload {string}, {string}, {string}")
    public void add_place_api_is_available_with_payload(String name, String language, String address) throws Exception {

        addPlacePayload = testDataBuild.addPlacePayload(name, language, address);
        requestSpec = given().spec(requestSpecificationAddPlace()).body(addPlacePayload);

    }

    @When("user call {string} with valid {string} http request method,")
    public void user_call_add_place_api_with_valid_post_http_request_method(String resource, String method) throws IOException {


       String enumName = resource.substring(0,1).toLowerCase() + resource.substring(1);
        ApiResources apiResources = ApiResources.valueOf(enumName);
        apiResources.getResource();


        if (method.equalsIgnoreCase("post")) {
            apiResponse = requestSpec
                    .config(RestAssured.config().redirect(RedirectConfig.redirectConfig().followRedirects(true)))
                    .when()
                    .post(apiResources.getResource())
                    .then()
                    .extract()
                    .response();
        } else if (method.equalsIgnoreCase("get")) {
            apiResponse = requestSpec
                    .config(RestAssured.config().redirect(RedirectConfig.redirectConfig().followRedirects(true)))
                    .when()
                    .get(apiResources.getResource())
                    .then()
                    .extract()
                    .response();
        } else {
            throw new IOException("Unsupported HTTP method: " + method);
        }
    }

    @Then("the API call is successful and response status code is {int}")
    public void the_api_call_is_successful_and_response_status_code_is(Integer expectedStatusCode) {

        assertThat(apiResponse.getStatusCode(), equalTo(expectedStatusCode));
        assertThat(apiResponse.getContentType().toLowerCase().contains("application/json"), equalTo(true));
    }

    @And("{string} in response body is {string}")
    public void verify_field_in_response_body(String responseKey, String expectedValue) {

        String normalizedKey = responseKey.toLowerCase();
        String actualValue = apiResponse.jsonPath().getString(normalizedKey);
        assertThat(actualValue, equalTo(expectedValue));
    }
}
