package com.example.RestCrudWithJpa.controller;


import com.example.RestCrudWithJpa.service.MessageService;
import com.example.RestCrudWithJpa.model.Message;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private MessageService messageService;


    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @PostMapping
    public ResponseEntity<Message> createNewMessage(@RequestBody Message message) {
        return ResponseEntity.ok(messageService.createMessage(message));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @Valid @RequestBody Message newMessage) throws Exception {

        newMessage.setId(id);
        Message message = messageService.updateMessage(newMessage);

        if (message != null) {
            return ResponseEntity.accepted().body(newMessage);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public void deleteMessageById(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }
}
