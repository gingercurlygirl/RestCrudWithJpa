package com.example.RestCrudWithJpa.controller;

import com.example.RestCrudWithJpa.model.Channel;
import com.example.RestCrudWithJpa.repository.ChannelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class ChannelIntegrationWithMockMVC {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testCreateAndGetChannel() throws Exception {

        //arrange
        Channel channel = new Channel(null, "first channel");

        //act & assert
        mockMvc.perform(post("/channels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(channel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("first channel")));

        //act
        Channel savedChannel = channelRepository.findAll().get(0);

        mockMvc.perform(get("/channels/" + savedChannel.getId()))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.name", is("first channel")));
    }

}