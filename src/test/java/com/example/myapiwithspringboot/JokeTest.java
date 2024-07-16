package com.example.myapiwithspringboot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JokeTest {

    @Test
    void getId() {
        Joke j1 = new Joke(1, "abc", "def");
        assertEquals(j1.getId(), 1);
    }

    @Test
    void getSetup() {
        Joke j1 = new Joke(1, "abc", "def");
        assertEquals(j1.getSetup(), "abc");
    }

    @Test
    void getPunchline() {
        Joke j1 = new Joke(1, "abc", "def");
        assertEquals(j1.getPunchline(), "def");
    }
}