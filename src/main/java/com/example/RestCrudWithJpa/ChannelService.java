package com.example.RestCrudWithJpa;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {
     ChannelRepository repo;


    public ChannelService(ChannelRepository repo) {
        this.repo = repo;
    }

    public Optional<Channel> addChannel (Channel channel){
        return repo.createChannel(channel);
    }

    public List<Channel> getAllChannels(){
        return repo.getAllChannel();
    }

    public Optional<Channel> getChannelById(Long id){
        return repo.getChannelById(id);

    }

    public Optional<Channel> updateChannel(Channel newChannel){

        Optional<Channel> channel = repo.getChannelById(newChannel.getId());
        if (channel.isPresent()) {
            repo.updateChannel(newChannel);
            return channel;
        }else return channel;
    }

    public void deleteChannel(long id){
        repo.deleteChannel(id);

    }
}
