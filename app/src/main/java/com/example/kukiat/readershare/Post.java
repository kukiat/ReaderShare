package com.example.kukiat.readershare;

/**
 * Created by kukiat on 11/4/2017 AD.
 */

public class Post {
    String picName;
    String picBook;
    String name;
    String topic;
    String rating;

    public Post() {

    }

    public String getPicName() {
        return picName;
    }

    public String getPicBook() {
        return picBook;
    }

    public String getName() {
        return name;
    }

    public String getTopic() {
        return topic;
    }

    public String getRating() {
        return rating;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public void setPicBook(String picBook) {
        this.picBook = picBook;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
