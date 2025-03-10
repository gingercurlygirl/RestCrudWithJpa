package com.example.RestCrudWithJpa;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ChannelRepository {
     private List<Channel> channels = new ArrayList<>();

    private long maxId = 0;

    public Optional<Channel> createChannel(Channel channel) {

        maxId = maxId + 1;
        channel.setId(maxId);

        Optional <Channel> channelOptional= Optional.of(channel);
        channels.add(channel);

        return channelOptional;
    }

    public List<Channel> getAllChannel() {
        return channels;
    }

    public Optional<Channel> getChannelById(Long id) {
        Optional <Channel> channel = channels.stream().filter(c -> c.getId() == id).findFirst();

        return channel;
    }

    public Channel updateChannel(Channel newChannel) {

        getChannelById(newChannel.getId()).ifPresent(c -> {
            c.setName(newChannel.getName());
        });

        return newChannel;
    }

    public void deleteChannel(long id) {
        channels.removeIf(c -> c.getId() == id);

    }
}
