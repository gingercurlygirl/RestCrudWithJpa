package com.example.RestCrudWithJpa.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChannelDTOUnitTest {

    @Test
    public void testSetAndGetMethodsForChannelDTO() {
        /* Unit test:
         * Testing logic in isolation from Spring and the database.
         * This test verifies the functionality of getter and setter
         * methods for ChannelDTO. It checks the default values
         * and ensures that values can be set and retrieved correctly.
         */
        ChannelDTO channelDTO = new ChannelDTO();

        //assert default constructor
        assertNull(channelDTO.getId());
        assertNull(channelDTO.getName());
        assertNotNull(channelDTO.getMessages());
        assertEquals(0, channelDTO.getMessages().size());

        //act
        channelDTO.setId(123L);
        channelDTO.setName("first channel");

        //assert
        assertEquals(123L, channelDTO.getId());
        assertEquals("first channel", channelDTO.getName());
    }
}
