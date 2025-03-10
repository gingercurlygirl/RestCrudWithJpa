package com.example.RestCrudWithJpa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "channels")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters long")
    private String name;

    public Channel() {

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
