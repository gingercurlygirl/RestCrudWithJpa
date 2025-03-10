package com.example.RestCrudWithJpa;


import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;


    @ManyToMany(mappedBy = "messages")
    private Set<Channel> Channels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<Channel> getChannels() {
        return Channels;
    }

    public void setChannels(Set<Channel> channels) {
        Channels = channels;
    }

}
