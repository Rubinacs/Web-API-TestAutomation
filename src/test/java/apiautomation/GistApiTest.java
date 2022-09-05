package apiautomation;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;


public class GistApiTest {

    //JSON file which contains the request body
    File jsonDataInFile = new File("src/test/resources/POST.json");
    //base url
    String BASE_URL = Setup.getValueFromProp("baseUrl") + "/gists";

    //Test to validate the response body with the POST method
    @Test
    public void givenGistPOSTThenVerifyResponse() {
        //Storing the description present in the request body
        String expectedDescription = "Example of a gist";
        //Sending the request parameters
        String response = given()
                .baseUri(BASE_URL)
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer " + Setup.getValueFromProp("gistTokenWithScope"))
                .body(jsonDataInFile)
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(201)
                .body("description", Matchers.equalTo(expectedDescription))
                .body("public", Matchers.equalTo(false))
                .extract().response().getBody().asString();

    }

    //Test to verify the response JSON schema with POST method
    @Test
    public void givenGistPOSTThenVerifyResponseJSONSchema() {
        given()
                .baseUri(BASE_URL)
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer " + Setup.getValueFromProp("gistTokenWithScope"))
                .body(jsonDataInFile)
                // WHEN
                .when()
                .post()
                // THEN
                .then()
                .assertThat()
                .statusCode(201)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema.json"));

    }

    //Test to verify the response status code 422 with POST method
    @Test
    public void givenGistPOSTThenVerifyStatusCode422() {
        //Storing the empty invalid body
        String reqBody = "{}";
        //Sending the request parameters with invalid body
        given()
                .baseUri(BASE_URL)
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer " + Setup.getValueFromProp("gistTokenWithScope"))
                .body(reqBody)
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(422);
    }

    //Test to verify the response status code 404 with POST method
    @Test
    public void givenGistPOSTThenVerifyStatuscode404() {
        //Sending the request parameters with the token which does not have a scope of create gist
        given()
                .baseUri(BASE_URL)
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer " + Setup.getValueFromProp("gistTokenWithOutScope"))
                .body(jsonDataInFile)
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(404);
    }

    //Test to verify the response status code 401 with POST method
    @Test
    public void givenGistPOSTThenVerifyStatuscode401() {
        //Sending the request parameters with invalid token
        given()
                .baseUri(BASE_URL)
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer abcd")
                .body(jsonDataInFile)
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(401);
    }

    //Test DELETE method with valid gist id
    @Test
    public void givenGistDELETEThenVerifyStatuscode204() {
        String gistId = getGistID();
        //Sending the request parameters
        given()
                .baseUri(BASE_URL + "/" + gistId)
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer " + Setup.getValueFromProp("gistTokenWithScope"))
                .when()
                .delete()
                .then()
                .assertThat()
                .statusCode(204);
    }

    //Test DELETE method with invalid gist id
    @Test
    public void givenGistDELETEThenVerifyStatuscode404() {
        //Storing the invalid gist id
        String invalidId = "git_1234";
        //Sending the request parameters
        given()
                .baseUri(BASE_URL + "/" + invalidId)
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer " + Setup.getValueFromProp("gistTokenWithScope"))
                .when()
                .delete()
                .then()
                .assertThat()
                .statusCode(404);
    }

    public String getGistID() {
        //Sending the request parameters
        String response = given()
                .baseUri(BASE_URL)
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer " + Setup.getValueFromProp("gistTokenWithScope"))
                .body(jsonDataInFile)
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(201)
                .extract().response().getBody().asString();

        JsonPath jsonPath = new JsonPath(response);
        //variable declaration for storing the gist ID
        return jsonPath.getString("id");
    }

}
