package com.prashant.automation.api;

// ── Why we import these ───────────────────────────
import io.restassured.RestAssured;
// Main REST Assured engine
// Lets us send HTTP requests in Java

import io.restassured.response.Response;
// Holds the complete API response
// Status code + body + headers all in one object

import org.testng.Assert;
// Our verification toolbox
// Same Assert we use in Playwright tests

import org.testng.annotations.BeforeClass;
// Runs ONCE before ALL tests in this class
// Perfect for API setup

import org.testng.annotations.Test;
// Marks a method as a test

public class UserAPiTest {

    // Base URL — starting point for all requests
    private static final String BASE_URL =
        "https://reqres.in/api";

    // Your personal API key from app.reqres.in
    // Replace with YOUR actual key
    private static final String API_KEY =
        "pub_49199586fd2d49c1ea8fd1700fe826ce83807a7ae4c0b81cf5037b30cbd6bf7c";

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        System.out.println("API Base URL: " + BASE_URL);
    }

    // Helper method — builds base request
    // Why: avoids repeating headers in every test
    // Like a reusable Tosca module for API setup
    private io.restassured.specification.RequestSpecification baseRequest() {
        return RestAssured
            .given()
                .header("Content-Type", "application/json")
                .header("x-api-key", API_KEY);
    }

    // ── Test 1: GET single user ───────────────────
    @Test
    public void testGetSingleUser() {
        System.out.println("\n--- Test: Get Single User ---");

        Response response = baseRequest()
            .when()
                .get("/users/2")
            .then()
                .extract()
                .response();

        System.out.println("Status: "
            + response.getStatusCode());
        System.out.println("Body: "
            + response.getBody().asString());

        Assert.assertEquals(
            response.getStatusCode(), 200,
            "Should be 200 OK!"
        );

        String firstName = response.jsonPath()
            .getString("data.first_name");

        System.out.println("User: " + firstName);
        Assert.assertNotNull(firstName,
            "First name should not be null!");

        System.out.println("PASSED - Get user works!");
    }

    // ── Test 2: GET all users ─────────────────────
    @Test
    public void testGetAllUsers() {
        System.out.println("\n--- Test: Get All Users ---");

        Response response = baseRequest()
            .when()
                .get("/users?page=1")
            .then()
                .extract()
                .response();

        System.out.println("Status: "
            + response.getStatusCode());

        Assert.assertEquals(
            response.getStatusCode(), 200,
            "Should be 200 OK!"
        );

        int totalUsers = response.jsonPath()
            .getInt("data.size()");

        System.out.println("Users found: " + totalUsers);
        Assert.assertTrue(totalUsers > 0,
            "Should have users!");

        System.out.println("PASSED - Get all users works!");
    }

    // ── Test 3: POST create user ──────────────────
    @Test
    public void testCreateUser() {
        System.out.println("\n--- Test: Create User ---");

        String body = "{\"name\":\"Prashant Goel\","
            + "\"job\":\"QA Automation Engineer\"}";

        Response response = baseRequest()
            .body(body)
            .when()
                .post("/users")
            .then()
                .extract()
                .response();

        System.out.println("Status: "
            + response.getStatusCode());
        System.out.println("Response: "
            + response.getBody().asString());

        Assert.assertEquals(
            response.getStatusCode(), 201,
            "Should be 201 Created!"
        );

        String name = response.jsonPath()
            .getString("name");

        Assert.assertNotNull(name,
            "Name should not be null!");

        System.out.println("Created: " + name);
        System.out.println("PASSED - Create user works!");
    }

    // ── Test 4: PUT update user ───────────────────
    @Test
    public void testUpdateUser() {
        System.out.println("\n--- Test: Update User ---");

        String body = "{\"name\":\"Prashant Goel\","
            + "\"job\":\"Senior QA Engineer\"}";

        Response response = baseRequest()
            .body(body)
            .when()
                .put("/users/2")
            .then()
                .extract()
                .response();

        System.out.println("Status: "
            + response.getStatusCode());
        System.out.println("Response: "
            + response.getBody().asString());

        Assert.assertEquals(
            response.getStatusCode(), 200,
            "Should be 200 OK!"
        );

        String job = response.jsonPath()
            .getString("job");

        Assert.assertEquals(job,
            "Senior QA Engineer",
            "Job should be updated!");

        System.out.println("PASSED - Update works!");
    }

    // ── Test 5: DELETE user ───────────────────────
    @Test
    public void testDeleteUser() {
        System.out.println("\n--- Test: Delete User ---");

        Response response = baseRequest()
            .when()
                .delete("/users/2")
            .then()
                .extract()
                .response();

        System.out.println("Status: "
            + response.getStatusCode());

        Assert.assertEquals(
            response.getStatusCode(), 204,
            "Should be 204 No Content!"
        );

        System.out.println("PASSED - Delete works!");
    }

    // ── Test 6: GET non-existent user ─────────────
    @Test
    public void testUserNotFound() {
        System.out.println("\n--- Test: Not Found ---");

        Response response = baseRequest()
            .when()
                .get("/users/999")
            .then()
                .extract()
                .response();

        System.out.println("Status: "
            + response.getStatusCode());

        Assert.assertEquals(
            response.getStatusCode(), 404,
            "Should be 404 Not Found!"
        );

        System.out.println("PASSED - 404 works!");
    }

}