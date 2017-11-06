package com.example.kukiat.readershare;

/**
 * Created by kukiat on 11/6/2017 AD.
 */

public class ListItem {

    private int id;
    private String name;
    private String topic;
    private String rating;
    private String book;

    ListItem(int id, String name, String topic, String rating, String book) {
        this.id = id;
        this.name = name;
        this.topic = topic;
        this.rating = rating;

        this.book = book;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
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

    public String getName() {

        return name;
    }

    public String getTopic() {
        return topic;
    }

    public String getRating() {
        return rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
