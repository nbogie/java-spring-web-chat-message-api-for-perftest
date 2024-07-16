package com.example.myapiwithspringboot;
//No special behaviour here - this class defines objects that carry around an id and a couple of strings.

public class Joke {
    private final int id;
    private final String setup;
    private final String punchline;

    public Joke(int id, String setup, String punchline) {
        this.id = id;
        this.setup = setup;
        this.punchline = punchline;
    }

    public int getId() {
        return id;
    }

    public String getSetup() {
        return setup;
    }

    public String getPunchline() {
        return punchline;
    }
}
