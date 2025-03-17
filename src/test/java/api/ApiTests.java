package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class ApiTests {

	private static String baseURL = "https://reqres.in/api";
	private static String userId;

	@BeforeClass
	public void setup() {
		RestAssured.baseURI = baseURL;
	}

	@Test(priority = 1)
	public void createUser() {
		Map<String, String> userData = new HashMap<>();
		userData.put("name", "John Doe");
		userData.put("job", "Software Engineer");
		userData.put("age", "30");

		Response response = given().contentType(ContentType.JSON).body(userData).when().post("/users").then()
				.statusCode(201).extract().response();

		userId = response.jsonPath().getString("id");
		System.out.println(response.jsonPath());
		Assert.assertNotNull(userId, "User ID should not be null");
	}

	@Test(priority = 2, dependsOnMethods = "createUser")
	public void retrieveUser() {
		given().when().get("/users/" + userId).then().statusCode(200).body("data.id",
				equalTo(Integer.parseInt(userId)));
	}

	@Test(priority = 3, dependsOnMethods = "retrieveUser")
	public void updateUser() {
		Map<String, String> updatedData = new HashMap<>();
		updatedData.put("name", "John Updated");
		updatedData.put("job", "Senior Engineer");
		updatedData.put("age", "30");

		given().contentType(ContentType.JSON).body(updatedData).when().put("/users/" + userId).then().statusCode(200)
				.body("job", equalTo("Senior Engineer"));
	}

	@Test(priority = 4)
	public void handleInvalidUser() {
		given().when().get("/users/9999").then().statusCode(404);
	}
}
