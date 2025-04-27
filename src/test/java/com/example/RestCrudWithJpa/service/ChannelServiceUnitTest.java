package com.example.RestCrudWithJpa.service;

import com.example.RestCrudWithJpa.model.Channel;
import com.example.RestCrudWithJpa.model.ChannelDTO;
import com.example.RestCrudWithJpa.repository.ChannelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ChannelServiceUnitTest {

    @Mock
    private ChannelRepository channelRepository;

    @InjectMocks
    private ChannelService channelService;

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