package com.example.RestCrudWithJpa;

public class Channel {

     private long id;

     private String name;


     public Channel() {

     }
     public Channel(long id, String name) {
         this.id = id;
         this.name = name;
     }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
