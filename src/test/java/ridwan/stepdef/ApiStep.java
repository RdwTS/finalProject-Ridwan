package ridwan.stepdef;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.TestNG;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiStep {

    private String appId;
    private Response response;
    private String baseURL;
    public static String createdUserId;
    public static String seaTag;

    //    @Before
//    public void setup() {
//        spec = new RequestSpecBuilder()
//                .setBaseUri("https://dummyapi.io/")
//                .addHeader("app-id", "63a804408eb0cb069b57e43a")
//                .build();
//        RestAssured.requestSpecification = spec;
//    }

    @Given("the API base URL is {string}")
    public void setBaseURL(String url) {
        this.baseURL = url;
        RestAssured.baseURI = baseURL;
    }

    @And("the header {string} is set to {string}")
    public void setHeader(String key, String value) {
        this.appId = value;
    }

    @When("I send a GET request to {string}")
    public void sendGetRequest(String endpoint) {
        response = given()
                .header("app-id", appId)
                .log().all() // log request
                .when()
                .get(endpoint)
                .then()
                .log().all() // log response
                .extract()
                .response();
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @And("the response body {string} should be {int}")
    public void verifyResponseBodySize(String jsonPath, int expectedSize) {
        response.then().body(jsonPath,Matchers.equalTo(expectedSize));
    }

    @And("the response should contain user ID equals to {string} ID")
    public void validateUserIdMatch(String id) {
        response.then().body("id", Matchers.equalTo(id));
    }

    @When("I send a PUT request to {string} to update firstName to {string}")
    public void updateUser(String endpoint, String newName) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("firstName", newName);
        JSONObject json = new JSONObject(body);

        response = given().log().all()
                .header("app-id", appId)
                .header("Content-Type", "application/json")
                .body(json.toString())
                .put(endpoint);
    }

    @Then("the response should contain firstName {string}")
    public void the_response_should_contain_first_name(String expectedFirstName) {
        response.then()
                .assertThat().statusCode(200)
                .assertThat().body("firstName", Matchers.equalTo(expectedFirstName));
    }

    @And("the response should match JSON schema {string}")
    public void validateJsonSchema(String fileName) {
        File file = new File("src/test/resources/jsonSchema/" + fileName);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(file)); // validasi output
    }

    @When("I send a POST request to {string} firstName {string} and lastName {string} andwith valid user payload")
    public void sendCreateUser(String endpoint ,String firstName ,String lastName) {
        JSONObject locationJson = new JSONObject();
        locationJson.put("street", "jlnPancoratBarat, Jakarta Selatan");
        locationJson.put("city", "DKI-JAKARTA");
        locationJson.put("state", "Indonesia");
        locationJson.put("country", "Asia");
        locationJson.put("timezone", "+8");

        JSONObject fieldJson = new JSONObject();
        fieldJson.put("title", "mr");
        fieldJson.put("firstName", firstName);
        fieldJson.put("lastName", lastName);
        fieldJson.put("picture", "");
        fieldJson.put("gender", "male");
        fieldJson.put("email", "ridwan.TegarSetiawan@example.com");
        fieldJson.put("dateOfBirth", "1999-06-07T22:16:47.420Z");
        fieldJson.put("phone", "021-147258");
        fieldJson.put("registerDate", "2025-05-19T12:34:56.000Z");
        fieldJson.put("updatedDate", "2025-05-19T12:34:56.000Z");
        fieldJson.put("location", locationJson);

        response = RestAssured
                .given().log().all()
                .header("app-id", appId)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fieldJson.toString())
                .post(endpoint);

    }

    @And("the response should contain firstName {string} and lastName {string}")
    public void verifyName(String first, String last) {
        response.then()
                .body("firstName", Matchers.equalTo(first))
                .body("lastName", Matchers.equalTo(last))
                .body("$", Matchers.hasKey("id"));
    }

    @When("I send a DELETE request to {string}")
    public void i_send_a_delete_request_to(String endpoint) {
        String resolvedEndpoint = endpoint.replace("{createdUserId}", createdUserId);

        response = given().log().all()
                .header("app-id", appId)
                .when().delete(resolvedEndpoint)
                .then().log().all()
                .extract().response();
    }

    @And("I store the created user ID")
    public void storeUserId() {
        createdUserId = response.path("id");
        System.out.println("Created user ID: " + createdUserId);
    }

    @Given("running TestNG suite {string}")
    public void runTestNgSuite(String suitePath) {
        TestNG testng = new TestNG();
        testng.setTestSuites(Collections.singletonList(suitePath));
        testng.run();
    }


    @And("I extract and store the tag value {string} from the response body")
    public void iExtractAndStoreTheTagValueFromTheResponseBody(String value) {
        List<String> tagList = response.jsonPath().getList("data");

        seaTag = tagList.stream()
                .filter(tag -> tag != null && tag.trim().equalsIgnoreCase(value))
                .map(String::trim)
                .findFirst()
                .orElse(null);

        System.out.println("Tag yang ditemukan: " + seaTag);
    }

    @And("each item in response body {string} should contain {string}")
    public void eachItemInResponseBodyShouldContain(String dataBody, String value) {
        response.then()
                .assertThat().statusCode(200)
                .body(dataBody, Matchers.everyItem(Matchers.hasItem(value)));

    }

    @When("I send a GET tag request to {string}")
    public void iSendAGETTagRequestTo(String endpoint) {
        String resolvedEndpoint = endpoint.replace("{seaTag}", seaTag);

        response = given().log().all()
                .header("app-id", appId)
                .log().all() // log request
                .when()
                .get(resolvedEndpoint)
                .then().log().all() // log response
                .extract().response();

    }
}
