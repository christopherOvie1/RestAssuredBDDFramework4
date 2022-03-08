package stepDefinition;
import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
//import org.junit.Assert;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import pojo.AddPlace;
import pojo.Location;
import resource.APIResources;
import resource.TestData;
import resource.Utility;

public class StepDefinitions extends Utility{
    RequestSpecification reqSpec;
    ResponseSpecification resSpec;
    RequestSpecification res;
    Response response;
    static String place_id;
    //String place_id;
    //JsonPath js;
    TestData data = new TestData();


    @Given("Addplace playload having parameters {string} and {string} and {string}")
    public void addplace_playload_having_parameters_and_and(String name, String language, String address) throws IOException {

        res=given().spec(requestSpecification())
                .body(data.addPlacePayload(name,language,address));
    }


    @When("user makes a request to the {string} endpoint with {string} request")
    public void user_makes_a_request_to_the_endpoint_with_request(String resource, String method) {

        //constructor will be called with value of resource passed
        //Create the object of the class and call the method
        APIResources resourceAPI=APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());


        resSpec=new  ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if(method.equalsIgnoreCase("POST"))
            response=res.when().post(resourceAPI.getResource());

        else if(method.equalsIgnoreCase("GET"))

            response=res.when().get(resourceAPI.getResource());

    }

    @Then("user should expect to see a success status code {int}")
    public void user_should_expect_to_see_a_success_status_code(Integer int1) {

        System.out.println("the status code is "+response.getStatusCode());
        assertEquals(response.getStatusCode(), 200);

    }
    @Then("confirms {string} in the response body  is {string}")
    public void confirms_in_the_response_body_is(String keyValue, String Expectedvalue) {
        //Assert.assertEquals(getJsonPath(response,keyValue),Expectedvalue);
        assertEquals(getJsonPath(response,keyValue),Expectedvalue);
    }

    @Then("verify that place_id created maps to {string} using {string}")
    public void verify_that_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
        //pseudo code  1..get request specification
        //request specification  to get
        place_id=getJsonPath(response,"place_id");

        res=given().spec(requestSpecification()).queryParam("place_id", place_id);

        user_makes_a_request_to_the_endpoint_with_request(resource,"GET");


        String actualName=getJsonPath(response,"name");
        assertEquals(actualName,expectedName);

    }
    @Given("Delete payload")
    public void delete_payload() throws IOException {
        res= given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
        //use variable res to make http request the endpoint.get or post
    }


}
