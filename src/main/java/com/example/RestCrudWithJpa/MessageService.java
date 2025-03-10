package com.example.RestCrudWithJpa;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
     private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public Message updateMessage(Message newMessage) {

        return messageRepository.findById(newMessage.getId()).map(message-> {
            message.setMessage(newMessage.getMessage());
            return messageRepository.save(message);
        }).orElse(null);
    }

    public void deleteMessage(long id) {
        messageRepository.deleteById(id);

    }
}
