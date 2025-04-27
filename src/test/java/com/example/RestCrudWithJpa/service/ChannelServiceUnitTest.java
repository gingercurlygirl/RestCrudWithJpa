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
        /* Unit test:
         * Testing the behavior of adding a message to an existing channel.
         * This test ensures that when a message is added to a known channel,
         * it is correctly stored and retrievable afterward.
         */
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
        /* Unit test:
         * Testing the behavior of adding a message to a non-existing channel.
         * We expect that no channel will be updated, and the
         * expected return value should be null while the retrieved
         * channel's message list remains empty.
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
       /* Unit test:
        * This test verifies that the channelService can successfully
        * retrieve a channel by its ID from the repository. It checks
        * that the correct channel is returned and that the repository's
        * `findById` method is invoked with the expected channel ID.
        */
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
        /* Unit test:
         * This test verifies that a channel can be successfully
         * deleted by its ID from the channelService. It checks that
         * after invoking the delete method, the channel no longer
         * exists in the repository.
         */
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