package com.example.kukiat.readershare;

/**
 * Created by kukiat on 11/4/2017 AD.
 */

public class ListModel {
    String picName;
    String picBook;
    String name;
    String topic;
    String rating;

    public ListModel() {

    }

    public ListModel(String picName, String picBook, String name, String topic, String rating) {
        this.picName = picName;
        this.picBook = picBook;
        this.name = name;
        this.topic = topic;
        this.rating = rating;
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
}
