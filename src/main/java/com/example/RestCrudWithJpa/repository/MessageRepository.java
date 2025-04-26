package com.example.RestCrudWithJpa.repository;

import com.example.RestCrudWithJpa.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
