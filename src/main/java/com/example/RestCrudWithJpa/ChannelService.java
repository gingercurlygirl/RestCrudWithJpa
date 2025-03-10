package com.example.RestCrudWithJpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {

    @PersistenceContext
    private EntityManager entityManager;

    ChannelRepository repo;

    public ChannelService(ChannelRepository repo) {
        this.repo = repo;
    }

    public Channel addChannel(Channel channel) {
        entityManager.clear();
        return repo.save(channel);
    }

    public List<Channel> getAllChannels() {
        return repo.findAll();
    }

    public Optional<Channel> getChannelById(Long id) {
        return repo.findById(id);

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
