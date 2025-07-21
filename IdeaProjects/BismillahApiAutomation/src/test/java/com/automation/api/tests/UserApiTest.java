package com.automation.api.tests;

import com.automation.api.payloads.UserPayload;
import com.automation.api.utilities.ApiTestBase;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertNotNull;


public class UserApiTest extends ApiTestBase {

    @Test(description = "Positive: Get Single User by ID")
    public void TestGetSingleUserById() {
        int userId = 2;
        response = apiUserClient.getSingleUser(userId);

        assertThat("Status should be 200", response.statusCode(), is(200));
        assertThat(response.jsonPath().getInt("data.id"), is(userId));
        assertThat(response.jsonPath().getString("data.email"), notNullValue());
        assertThat(response.jsonPath().getString("data.first_name"), notNullValue());
        assertThat(response.jsonPath().getString("data.last_name"), notNullValue());
    }

//    /* HELP KAK, SETIAP KALI AKU UNCOMMENT INI DAN DIJALANKAN SELALU ERROR :(
    @Test(description = "Positive: Get list of users on page 1") // TestNG Test dengan deskripsi
    public void testGetListOfUsers() {
        int page = 1;
        response = apiUserClient.getListOfUsers(page);

        // Tambahkan logging untuk melihat response body jika status code bukan 200
        if (response.statusCode() != 200) {
            logger.error("API Response for GetListOfUsers (Page {}): Status {} - Body: {}", page, response.statusCode(), response.body().asString());
        }

        assertThat("Status code should be 200", response.statusCode(), is(200));
        assertThat(response.jsonPath().getInt("page"), is(page));
        assertThat(response.jsonPath().getList("data").size(), greaterThan(0));
        assertThat(response.jsonPath().getString("data[0].email"), notNullValue());
    }

    @Test(description = "Positive: Create a new user") // TestNG Test dengan deskripsi
    public void testCreateNewUser() {
        requestPayload = new UserPayload("morpheus", "leader");
        response = apiUserClient.createUser(requestPayload);
        System.out.println("Response Body: " + response.body().asString());

        assertThat("Status code should be 201 (Created)", response.statusCode(), is(201));
        assertNotNull(response.jsonPath().getString("id"));
        assertThat(response.jsonPath().getString("name"), is(requestPayload.getName()));
        assertThat(response.jsonPath().getString("job"), is(requestPayload.getJob()));
        assertNotNull(response.jsonPath().getString("createdAt"));
        createdId = response.jsonPath().getString("id"); // Simpan ID yang dibuat
    }

}