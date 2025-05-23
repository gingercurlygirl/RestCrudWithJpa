package com.example.RestCrudWithJpa.model;

import java.util.ArrayList;
import java.util.List;

public class ChannelDTO {

    private Long id;

    private String name;

    private List<String> messages = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<String> getMessages() {
        return messages;
    }


}
