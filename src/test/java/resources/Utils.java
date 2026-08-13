package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.PrintStream;


public class Utils {

    TestDataBuild testDataBuild = new TestDataBuild();

    public RequestSpecification requestSpecification() throws FileNotFoundException {


        RestAssured.baseURI = "https://rahulshettyacademy.com/";

        PrintStream log = new PrintStream("logging.text");


        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com/")
                .addQueryParam("key", "qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType(ContentType.JSON)
                .setBody(testDataBuild.addPlacePayload())
                .build();

        return requestSpec;


    }
}
