package com.example.RestCrudWithJpa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Channel {



     private long id;

     @NotBlank(message = "Name cannot be blank")
     @Size(min=2, max=20, message ="Name must be between 2 and 20 characters long")
     private String name;

     public Channel() {

     }
     public Channel(long id, String name) {
         this.id = id;
         this.name = name;
     }

    public long getId() {
        return id;
    }
    public void setId(long id) {
         this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
