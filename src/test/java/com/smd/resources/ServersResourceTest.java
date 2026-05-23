package com.smd.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.smd.response.ServiceResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServersResourceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testPing() {
        ResponseEntity<ServiceResponse> response = restTemplate.getForEntity("/servers/ping", ServiceResponse.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("200", response.getBody().getErrorcode());
    }

    @Test
    public void testException() {
        ResponseEntity<ServiceResponse> response = restTemplate.getForEntity("/servers/exception", ServiceResponse.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("500", response.getBody().getErrorcode());
        assertEquals("Exception demo", response.getBody().getHttpMessage());
    }

    @Test
    public void testGetAllServers() {
        ResponseEntity<ServiceResponse> response = restTemplate.getForEntity("/servers", ServiceResponse.class);
        // Note: Original code returned 200 for getAllServers, but the previous refactor changed it to 201.
        // I've restored it to 200 in my latest ServersResource.java
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
