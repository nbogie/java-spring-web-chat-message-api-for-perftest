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
    private final Object dbLock = new Object();

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
    public List<ChatMessage> getAllMessages() throws InterruptedException {
        //Our domain object will be converted to JSON automatically by the Jackson 2 library
        this.sleepThreadRandomMillis(10, 200);
        synchronized (dbLock) {
            return new ArrayList<>(chatMessages);
        }
    }


    public long sleepThreadRandomMillis(int minDuration, int maxDuration) throws InterruptedException {
        long duration = (long) (minDuration + (Math.random() * maxDuration - minDuration));
        Thread.sleep(duration);
        return duration;
    }

    @GetMapping("/messages/{id}")
    public Optional<ChatMessage> getOneMessage(@PathVariable("id") Long soughtId) throws InterruptedException {
        sleepThreadRandomMillis(10, 200);


        synchronized (dbLock) {
            for (ChatMessage chatMessage : chatMessages) {
                if (chatMessage.id() == soughtId) {
                    return Optional.of(chatMessage);
                }
            }
            return Optional.empty();
        }
    }


    @PostMapping("/messages")
    public ResponseEntity<ChatMessage> addChatMessage(@RequestBody ChatMessage message) throws InterruptedException {
        this.sleepThreadRandomMillis(0, 300);

        synchronized (dbLock) {

            ChatMessage newMsg = new ChatMessage(nextMessageId++, message.author(), message.text());

            chatMessages.add(newMsg);
            //TODO: remove older messages once we have 1000 to prevent performance testing from exhausting memory.
            return ResponseEntity.ok(newMsg);
        }
    }

    @PostMapping("/messages/reset")
    public boolean resetAllMessages() {
        synchronized (dbLock) {

            resetChatMessages();
            return true;
        }
    }

    @DeleteMapping("/messages")
    public long deleteAllMessages() {
        synchronized (dbLock) {

            long prevSize = chatMessages.size();
            chatMessages.clear();
            return prevSize;
        }
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Long> deleteOneMessage(@PathVariable("id") Long idToDelete) throws InterruptedException {
        this.sleepThreadRandomMillis(20, 100);

        synchronized (dbLock) {

            boolean successfullyRemoved = chatMessages.removeIf(message -> message.id() == idToDelete);
            if (successfullyRemoved) {
                return ResponseEntity.ok(idToDelete);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }


}
