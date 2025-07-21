package com.automation.api.utilities;

import com.automation.api.clients.ApiUserClient;
import com.automation.api.payloads.UserPayload;
import io.restassured.response.Response;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


public class ApiTestBase {
    protected static final Logger logger = LoggerFactory.getLogger(ApiTestBase.class);
    protected ApiUserClient apiUserClient;
    protected Response response; // Objek response dari Rest Assured
    protected String createdId; // Untuk menyimpan ID user yang dibuat (reqres.in returns 'id' not 'userId')
    protected UserPayload requestPayload; // Untuk menyimpan payload request yang dikirim

    //@org.testng.annotations.BeforeClass
    @BeforeClass
    public void setupClient() {
        apiUserClient = new ApiUserClient();
        logger.info("ApiUserClient initialized for test suite.");
    }

    @BeforeMethod
    public void setupMethod(){
        response = null; // Pastikan response bersih sebelum setiap tes
        requestPayload = null; // Pastikan payload bersih
        createdId = null;
        logger.info("Test method setup complete.");
    }

    @AfterMethod
   // @org.testng.annotations.AfterMethod
    public void teardownMethod() {
        if (createdId != null) {
            logger.info("Cleanup: User with ID {} was created. Note: reqres.in does not persist data.", createdId);
            createdId = null;
        }
        logger.info("Test method teardown complete.");
    }
}

