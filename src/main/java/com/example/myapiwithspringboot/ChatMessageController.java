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
        chatMessages = new ArrayList<>();
        chatMessages.add(new ChatMessage(1, "author1", "message1"));
        chatMessages.add(new ChatMessage(2, "author2", "message2"));
        chatMessages.add(new ChatMessage(3, "author3", "message3"));
        nextMessageId = 0;
    }

    private int nextMessageId = 0;

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


    @PostMapping("/messages")
    public ResponseEntity<ChatMessage> addChatMessage(@RequestBody ChatMessage message) {
        ChatMessage newMsg = new ChatMessage(nextMessageId++, message.author(), message.text());

        chatMessages.add(newMsg);
        //TODO: remove older messages once we have 1000 to prevent performance testing from exhausting memory.
        return ResponseEntity.ok(newMsg);
    }

    @PostMapping("/messages/reset")
    public boolean resetAllMessages() {
        resetChatMessages();
        return true;
    }

    @DeleteMapping("/messages")
    public long deleteAllMessages() {
        long prevSize = chatMessages.size();
        chatMessages.clear();
        return prevSize;
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
