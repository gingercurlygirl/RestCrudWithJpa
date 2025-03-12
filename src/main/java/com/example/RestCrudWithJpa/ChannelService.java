package com.example.RestCrudWithJpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChannelService {

    @PersistenceContext
    private EntityManager entityManager;

    ChannelRepository repo;

    MessageRepository messageRepository;

    public ChannelService(ChannelRepository repo, MessageRepository messageRepository) {
        this.repo = repo;
        this.messageRepository = messageRepository;
    }

    public Channel addChannel(Channel channel) {
        entityManager.clear();
        return repo.save(channel);
    }

    public List<ChannelDTO> getAllChannels() {
        List<Channel> channels = repo.findAll();

        return channels.stream().map(ChannelMapper.INSTANCE::channelToChannelDTO).collect(Collectors.toList());
    }

    public ChannelDTO getChannelById(Long id) {
        Optional<Channel> channel = repo.findById(id);
        if (channel.isPresent()) {
            Optional<ChannelDTO> result = channel.map(ChannelMapper.INSTANCE::channelToChannelDTO);
            return result.orElse(null);
        }
        return null;

    }

    public ChannelDTO addMessageToChannel(Long channelId, Message message) {
        Optional<Channel> oc = repo.findById(channelId);

        if (oc.isPresent()) {
            Channel channel = oc.get();
            channel.addMessage(message);
            message.setChannels(channel);
            repo.save(channel);
            return ChannelMapper.INSTANCE.channelToChannelDTO(channel);

        }
        return null;
    }

    public Channel updateChannel(Channel newChannel) {

        return repo.findById(newChannel.getId()).map(channel -> {
            channel.setName(newChannel.getName());
            return repo.save(channel);
        }).orElse(null);
    }

    public void deleteChannel(long id) {
        repo.deleteById(id);

    }
}
