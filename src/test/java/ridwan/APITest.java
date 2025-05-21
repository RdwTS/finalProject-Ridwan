package ridwan;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
//import static jdk.internal.vm.vector.VectorSupport.extract;

public class APITest {

    public static String createdUserId;
    String baseURL = "https://dummyapi.io/";
    String URLApi = "data/v1/user/";

    @Test(priority = 1)
    public  void getUserList() {
        RestAssured.baseURI = baseURL ;

        given().header("app-id", "63a804408eb0cb069b57e43a")
                .when().get(URLApi)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("data.id.size()", Matchers.equalTo(20));



    }

    @Test(priority = 2)
    public void createNewUser(){
        //baseURL
        RestAssured.baseURI = baseURL;
        // parameter Json
        String title       = "mr";
        String firstName   = "Ridwan";
        String lastName    = "TegarSetiawan";
        String picture     = "";  // Kosong
        String gender      = "male";
        String email       = "ridwan.TegarSetiawan@example.com";
        String dateOfBirth = "1999-06-07T22:16:47.420Z";
        String phone       = "021-147258";

        // Lokasi (nested object)
        String street   = "jlnPancoratBarat, Jakarta Selatan";
        String city     = "DKI-JAKARTA";
        String state    = "Indonesia";
        String country  = "Asia";
        String timezone = "+8";

        String registerDate = "2025-05-19T12:34:56.000Z";
        String updatedDate  = "2025-05-19T12:34:56.000Z";

        // `location`
        JSONObject locationJson = new JSONObject();
        locationJson.put("street", street);
        locationJson.put("city", city);
        locationJson.put("state", state);
        locationJson.put("country", country);
        locationJson.put("timezone", timezone);

        // Buat objek utama (fieldJson)
        JSONObject fieldJson = new JSONObject();
        fieldJson.put("title", title);
        fieldJson.put("firstName", firstName);
        fieldJson.put("lastName", lastName);
        fieldJson.put("picture", picture);
        fieldJson.put("gender", gender);
        fieldJson.put("email", email);
        fieldJson.put("dateOfBirth", dateOfBirth);
        fieldJson.put("phone", phone);
        fieldJson.put("registerDate", registerDate);
        fieldJson.put("updatedDate", updatedDate);
        fieldJson.put("location", locationJson);

        //include header Json format
        Response response = given().log().all() // for print entire request to console
                                    .header("app-id", "63a804408eb0cb069b57e43a")
                                    .header("Content-Type","application/json")
                                    .header("Accept","application/json")
                                    .body(fieldJson.toString())
                                    .post(URLApi+"create")
                                    .then()
                                    .log().body()
                                    .assertThat().statusCode(200)
                                    .assertThat().body("firstName", Matchers.equalTo(firstName))
                                    .assertThat().body("lastName",Matchers.equalTo(lastName))
                                    .assertThat().body("$",Matchers.hasKey("id"))
                                    .extract().response();

        createdUserId = response.path("id");
        System.out.println("ID: " + createdUserId);

    }

    @Test(priority = 3)
    public void getUserbyID() {
        //baseURL
        RestAssured.baseURI = baseURL;

        // id user
//        String userId     =  "60d0fe4f5311236168a109ce" ;

        given().header("app-id", "63a804408eb0cb069b57e43a")
                .when().get(URLApi+createdUserId)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("id", Matchers.equalTo(createdUserId)); // validasi output

    }

    @Test(priority = 4)
    public void getUserbyINegative() {
        //baseURL
        RestAssured.baseURI = baseURL;

        // id user
        String userId     =  "A" ;

        given().header("app-id", "63a804408eb0cb069b57e43a")
                .when().get(URLApi+userId)
                .then()
                .log().all()
                .assertThat().statusCode(400);//badRequest

    }


    @Test(priority = 5)
    public void updateUserPATCH() {
        //baseURL
        RestAssured.baseURI = baseURL;

        // id user
//        String userId     = "682aacd35944cbd169142c64";
        String newName    = "updateUser";
        String firstName = given().when().get(URLApi + createdUserId).getBody().jsonPath().get("firstName");
        System.out.println("First name before change :" + firstName );

        // change name
        HashMap<String,Object> bodyMap = new HashMap<>();
        bodyMap.put("firstName",newName);
        JSONObject fieldJson = new JSONObject (bodyMap);


        given().log().all()
                .header("app-id", "63a804408eb0cb069b57e43a")
                .header("Content-Type","application/json")
                .body(fieldJson.toString())
                .put(URLApi + createdUserId)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("firstName", Matchers.equalTo(newName));

    }

    @Test(priority = 6)
    public void validateJsonSchemaGetSingleUser() {
        //baseURL
        RestAssured.baseURI = baseURL;

        // id user
//        String userId     =  "60d0fe4f5311236168a109ce" ;
        //file Path Json Schema
        File pathFile = new File("src/test/resources/jsonSchema/getSingleUserJsonSchema.json");

        given().header("app-id", "63a804408eb0cb069b57e43a")
                .when().get(URLApi+createdUserId)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(pathFile)); // validasi output

    }

    @Test(priority = 7)
    public void deleteUser() {
        //baseURL
        RestAssured.baseURI = baseURL;

        // dataDelete
//        String userToDelete     = "682a9f3eaeaa94dc9318c872";

        given().log().all()
                .header("app-id", "63a804408eb0cb069b57e43a")
                .when().delete(URLApi + createdUserId)
                .then().log().all()
                .assertThat().statusCode(200);

    }

}
