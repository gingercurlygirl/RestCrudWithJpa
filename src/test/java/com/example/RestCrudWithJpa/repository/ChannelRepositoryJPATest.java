package com.example.RestCrudWithJpa.repository;

import com.example.RestCrudWithJpa.model.Channel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class ChannelRepositoryJPATest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ChannelRepository channelRepository;

    @Test
    public void testFindByName() {
        /* Integration test:
         * This test verifies that a channel can be successfully
         * persisted and then retrieved by its name. It checks
         * that the found channel matches the expected name.
         */

        //arrange
        Channel channel = new Channel(null, "first channel");
        entityManager.persistAndFlush(channel);

        //act
        Optional<Channel> foundChannel = channelRepository.findByName("first channel");

        //assert
        assertTrue(foundChannel.isPresent());
        assertEquals("first channel", foundChannel.get().getName());
    }

    @Test
    public void testFindAll(){
        /* Integration test:
         * This test verifies that multiple channels can be
         * successfully persisted and then retrieved in a list.
         * It checks that the retrieved list contains all the
         * channels that were added.
         */
        //arrange
        Channel channel1 = new Channel(null, "first channel");
        Channel channel2 = new Channel(null, "second channel");
        Channel channel3 = new Channel(null, "new channel");
        entityManager.persist(channel1);
        entityManager.persist(channel2);
        entityManager.persist(channel3);
        entityManager.flush();

        //act
        List<Channel> resultChannels = channelRepository.findAll();

        //assert
        assertEquals(3, resultChannels.size());
        assertTrue(resultChannels.contains(channel1));
        assertTrue(resultChannels.contains(channel2));
        assertTrue(resultChannels.contains(channel3));

    }

}