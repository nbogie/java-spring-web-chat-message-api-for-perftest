package com.example.myapiwithspringboot;
//For variety, we'll make this class for simple data transfer objects / domain objects in a slightly different way than the Joke class.

//record classes are simpler than normal classes.  See: https://docs.oracle.com/en/java/javase/22/language/records.html
public record ChatMessage(int id, String author, String text) {
}
