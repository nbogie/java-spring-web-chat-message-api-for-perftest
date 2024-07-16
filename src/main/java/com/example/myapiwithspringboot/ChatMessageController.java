package com.example.myapiwithspringboot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ChatMessageController {

    //TODO: store these chat messages in a service representing a database
    //  instead of here.  (I don't think we're guaranteed to have a single controller)
    private ArrayList<ChatMessage> chatMessages;

    private void resetChatMessages() {
        chatMessages = new ArrayList<ChatMessage>();
        chatMessages.add(new ChatMessage(1, "abc", "def"));
        chatMessages.add(new ChatMessage(2, "abc2", "def2"));
        chatMessages.add(new ChatMessage(3, "abc3", "def3"));
    }

    public ChatMessageController() {
        System.out.println("Making a new ChatMessageController and all the messages again");
        resetChatMessages();
    }

    @GetMapping("/messages")
    public List<ChatMessage> getAllMessages() {
        //Our domain object will be converted to JSON automatically by the Jackson 2 library
        return chatMessages;
    }


    @GetMapping("/messages/{id}")
    public Optional<ChatMessage> getOneMessage(@PathVariable("id") Long soughtId) {
        for (ChatMessage chatMessage : chatMessages) {
            if (chatMessage.id() == soughtId) {
                return Optional.of(chatMessage);
            }
        }
        return Optional.empty();
    }

    @DeleteMapping("/messages")
    public long deleteAllMessages() {
        long prevSize = chatMessages.size();
        chatMessages.clear();
        System.out.println("now cm is: " + chatMessages.size());
        return prevSize;
    }

    @PostMapping("/messages/reset")
    public boolean resetAllMessages() {
        resetChatMessages();
        return true;
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Long> deleteOneMessage(@PathVariable("id") Long idToDelete) {
        boolean successfullyRemoved = chatMessages.removeIf(message -> message.id() == idToDelete);
        if (successfullyRemoved) {
            return ResponseEntity.ok(idToDelete);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
