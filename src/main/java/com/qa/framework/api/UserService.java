package com.qa.framework.api;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserService extends BaseApiService {

    /**
     * Call API GET to get information of 1 user
     * @param userId    user ID
     * @return          a RestAssured {@link Response} object containing the server's HTTP response
     */
    public static Response getUserDetails (int userId) {
        return given()
                    .spec(requestSpec)
                .when()
                    .get("/api/users/" + userId);
    }

    /**
     * Sends a GET request to retrieve a paginated list of users.
     * @param page  the page number to retrieve (e.g., 1, 2, 3)
     * @return      a RestAssured {@link Response} object containing the server's HTTP response
     */
    public static Response getUserList(int page) {
        return given()
                    .spec(requestSpec)
                    .queryParam("page", page)
                .when()
                    .get("/api/users");
    }

    /**
     * Sends a POST request to create a new user.
     * @param name  username
     * @param job   job
     * @return      a RestAssured {@link Response} object containing the server's HTTP response
     */
    public static Response createUser(String name, String job) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("job", job);

        return given()
                    .spec(requestSpec)
                    .body(requestBody)
                .when()
                    .post("/api/users");
    }

    /**
     * Sends a PUT request to update a user
     * @param userId   userId you want to change information
     * @param name     username
     * @param job      job
     * @return         a RestAssured {@link Response} object containing the server's HTTP response
     */
    public static Response updateUser(int userId, String name, String job){
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("job", job);
        return given()
                    .spec(requestSpec)
                    .body(requestBody)
                .when()
                    .put("/api/users/" + userId);
    }

    /**
     * Send a DELETE request to delete a user
     * @param userId    userId you want to delete
     * @return          a RestAssured {@link Response} object containing the server's HTTP response
     */
    public static Response deleteUser(int userId){
        return given()
                    .spec(requestSpec)
                .when()
                    .delete("/api/users/" + userId);
    }

    /**
     * Send a POST request to log in user
     * @param email     user email
     * @param password  user password
     * @return          HTTP response
     */
    public static Response loginUser(String email, String password){
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);
        return given()
                    .spec(requestSpec)
                    .body(requestBody)
                .when()
                    .post("/api/login");
    }

    /**
     * Sends a POST request to log in and get token.
     *
     * @param email    user email
     * @param password user password
     * @return         token
     */
    public static String getAuthTokenAfterLogin(String email, String password) {
        Response response = loginUser(email, password);
        return response.jsonPath().getString("token");
    }
}
