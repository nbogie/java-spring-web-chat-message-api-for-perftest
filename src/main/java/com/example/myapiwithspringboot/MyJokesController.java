package com.example.myapiwithspringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

//This is an annotated controller:
// Docs are at: https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller.html#page-title
@RestController
public class MyJokesController {

    @GetMapping("/jokes")
    public ArrayList<Joke> getAllJokes() {
        ArrayList<Joke> allJokes = new ArrayList<Joke>();
        allJokes.add(new Joke(2, "setup2", "punchline2"));
        allJokes.add(new Joke(3, "setup3", "punchline3"));
        allJokes.add(new Joke(4, "setup4", "punchline4"));

        //Our domain object will be converted to JSON automatically by the Jackson 2 library
        return allJokes;
    }
}
