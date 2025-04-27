package com.example.RestCrudWithJpa.service;

import com.example.RestCrudWithJpa.model.Channel;
import com.example.RestCrudWithJpa.model.ChannelDTO;
import com.example.RestCrudWithJpa.model.Message;
import com.example.RestCrudWithJpa.repository.ChannelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ChannelServiceUnitTest {

    @Mock
    private ChannelRepository channelRepository;

    @InjectMocks
    private ChannelService channelService;

    @Test
    public void addMessageToExistingChannel() {
        //arrange
        Channel channel = new Channel(1L, "first channel");
        lenient().when(channelRepository.findById(1L)).thenReturn(Optional.of(channel));

        //act
        ChannelDTO expectedChannel = channelService.addMessageToChannel(1L, new Message(123L, "First message"));
        ChannelDTO resultChannel = channelService.getChannelById(1L);
        List<String> resultMessages = resultChannel.getMessages();

        //assert
        assertEquals(expectedChannel.getMessages(), resultChannel.getMessages());
        assertEquals(1, resultMessages.size());
        assertEquals("First message", resultMessages.getFirst());
    }

    @Test
    public void addMessageToNonExistingChannel() {
        /*
         *
         */
        //arrange
        Channel channel = new Channel(1L, "first channel");
        lenient().when(channelRepository.findById(1L)).thenReturn(Optional.of(channel));

        //act
        ChannelDTO expectedChannel = channelService.addMessageToChannel(7777L, new Message(123L, "First message"));
        ChannelDTO resultChannel = channelService.getChannelById(1L);
        List<String> resultMessages = resultChannel.getMessages();

        //assert
        assertNull(expectedChannel);
        assertEquals(0, resultMessages.size());
    }

    @Test
    public void testGetChannelByIdReturnsChannel() {
        //arrange
        Channel channel = new Channel(1L, "first channel");
        when(channelRepository.findById(1L)).thenReturn(Optional.of(channel));

        //act
        ChannelDTO result = channelService.getChannelById(1L);

        //assert
        assertEquals("first channel", result.getName());
        verify(channelRepository).findById(1L);
    }

    @Test
    public void testDeleteChannelById() {
        //arrange
        Channel channel = new Channel(1L, "first channel");
        lenient().when(channelRepository.findById(1L)).thenReturn(Optional.of(channel));

        //act
        channelService.deleteChannel(1L);

        //assert
        boolean result = channelRepository.existsById(1L);
        assertFalse(result);
    }

}