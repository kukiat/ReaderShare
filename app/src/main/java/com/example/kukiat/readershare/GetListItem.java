package com.example.kukiat.readershare;

/**
 * Created by kukiat on 11/6/2017 AD.
 */

public class GetListItem {
    private int id;
    private String name;
    private String topic;
    private String rating;
    private String description;
    private String imageName;
    private String imageBook;

    public GetListItem(int id, String name, String topic, String rating, String description, String imageName, String imageBook) {
        this.id = id;
        this.name = name;
        this.topic = topic;
        this.rating = rating;
        this.description = description;
        this.imageName = imageName;
        this.imageBook = imageBook;

    }

    public void setId(int id) {
        this.id = id;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setImageBook(String imageBook) {
        this.imageBook = imageBook;
    }

    public int getId() {

        return id;
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

    public String getDescription() {
        return description;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageBook() {
        return imageBook;
    }
}
