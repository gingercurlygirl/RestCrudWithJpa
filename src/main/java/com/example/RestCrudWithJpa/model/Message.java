package com.example.RestCrudWithJpa.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "channels_id")
    private Channel channels;

    public Message(Long id, String message) {
        this.id = id;
        this.message = message;
        this.channels = null;
    }

    public Message() {

    }

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

    public void setChannels(Channel channels) {
        this.channels = channels;
    }

}
