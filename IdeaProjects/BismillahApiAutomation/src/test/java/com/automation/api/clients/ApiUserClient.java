package com.automation.api.clients;

import com.automation.api.payloads.UserPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class ApiUserClient {
    private static final Logger logger = LoggerFactory.getLogger(ApiUserClient.class);
    private static final String BASE_URL = "https://reqres.in/api"; // BASE URL untuk reqres.in

    private String authToken;

    public ApiUserClient() {
        RestAssured.baseURI = BASE_URL;
        logger.info("Base URI set to: " + BASE_URL);
    }

    // Metode untuk mengatur token otorisasi
    public void setAuthToken(String token) {
        this.authToken = token;
        logger.info("Authentication token set.");
    }

    // Metode pembantu untuk mendapatkan RequestSpecification dengan header otorisasi
    private RequestSpecification getRequestSpec() {
        RequestSpecification requestSpec = RestAssured.given();
        if (authToken != null && !authToken.isEmpty()) {
            requestSpec.header("Authorization", "Bearer " + authToken);
            logger.info("Adding Authorization header with Bearer token.");
        }
        return requestSpec;
    }

    // Mengambil daftar user
    public Response getListOfUsers(int page) {
        logger.info("Mengirim GET request untuk daftar user halaman: " + page);
        return RestAssured.given()
                .header("x-api-key", "reqres-free-v1")
                .queryParam("page", page)
                .when()
                .get("/users");
    }

    // Mengambil user berdasarkan ID
    public Response getSingleUser(int userId) {
        logger.info("Mengirim GET request untuk user ID: " + userId);
        return RestAssured.given()
                .when()
                .get("/users/" + userId);
    }

    // Membuat user baru (reqres.in menggunakan name dan job)
    public Response createUser(UserPayload user) {
        logger.info("Mengirim POST request untuk membuat user: " + user.getName());
        return RestAssured.given()
                .header("x-api-key", "reqres-free-v1") // Set header default untuk semua request
                .contentType(ContentType.JSON)
                .body(user) // Body akan berisi name dan job
                .when()
                .post("/users");
    }

    // Mengupdate user (PUT)
    public Response updateUser(int userId, UserPayload userUpdates) {
        logger.info("Mengirim PUT request untuk mengupdate user ID: {}", userId); // Menggunakan parameterized logging
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(userUpdates) // Body akan berisi name dan job
                .when()
                .put("/users/" + userId);
    }

    // Menghapus user
    public Response deleteUser(int userId) {
        logger.info("Mengirim DELETE request untuk user ID: {}", userId);
        return RestAssured.given()
                .when()
                .delete("/users/" + userId);
    }
}
