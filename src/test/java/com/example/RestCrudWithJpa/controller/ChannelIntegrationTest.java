package com.example.RestCrudWithJpa.controller;

import com.example.RestCrudWithJpa.model.Channel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChannelIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateAndGetChannelByHttp() {
        /* Integration test:
         * This test verifies the HTTP API for creating a channel.
         * It checks the correct status responses and the data returned in the response body.
         */
        Channel channel = new Channel(null, "first channel");

        ResponseEntity<Channel> postResponse =
                restTemplate.postForEntity("http://localhost:" + port + "/channels", channel, Channel.class);

        assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        Long channelId = postResponse.getBody().getId();

        ResponseEntity<Channel> getResponse = restTemplate.getForEntity("http://localhost:" + port + "/channels/"+ channelId, Channel.class);

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals(1L, getResponse.getBody().getId());
        assertEquals("first channel", getResponse.getBody().getName());
    }
}