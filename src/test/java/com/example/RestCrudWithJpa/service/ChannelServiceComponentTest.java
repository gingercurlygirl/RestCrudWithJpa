package com.example.RestCrudWithJpa.service;

import com.example.RestCrudWithJpa.model.Channel;
import com.example.RestCrudWithJpa.model.ChannelDTO;
import com.example.RestCrudWithJpa.repository.ChannelRepository;
import com.example.RestCrudWithJpa.repository.MessageRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ChannelServiceComponentTest {
    @Autowired
    private ChannelService channelService;


    @Test
    public void testCreateAndFetchChannel() {
        //arrange
        Channel channel = new Channel(1L, "first channel");

        //act
        channelService.addChannel(channel);
        ChannelDTO fetchedChannel = channelService.getChannelById(1L);

        //assert
        assertEquals(1L, fetchedChannel.getId());
        assertEquals("first channel", fetchedChannel.getName());
    }

}