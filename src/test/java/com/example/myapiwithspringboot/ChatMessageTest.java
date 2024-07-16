package com.example.myapiwithspringboot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatMessageTest {

    @Test
    void id() {
        ChatMessage msg1 = new ChatMessage(1, "abc", "def");
        assertEquals(msg1.id(), 1);
    }

    @Test
    void author() {
        ChatMessage msg1 = new ChatMessage(1, "abc", "def");
        assertEquals(msg1.author(), "abc");
    }

    @Test
    void text() {
        ChatMessage msg1 = new ChatMessage(1, "abc", "def");
        assertEquals(msg1.text(), "def");
    }
}