package com.example.RestCrudWithJpa.repository;

import com.example.RestCrudWithJpa.model.Channel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
    boolean existsById(long id);
    Optional<Channel> findByName(@NotBlank(message = "Name cannot be blank") @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters long") String name);
}
