package com.example.kukiat.readershare;

/**
 * Created by aiy on 12/11/17.
 */

public class SubscribeItem {
    private String id;
    private String reviewerImage;
    private String reviewerName;
    private String subscribe;

    public SubscribeItem(String id, String reviewerImage, String reviewerName, String subscribe) {
        this.id = id;
        this.reviewerImage = reviewerImage;
        this.reviewerName = reviewerName;
        this.subscribe = subscribe;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReviewerImage() {
        return reviewerImage;
    }

    public void setReviewerImage(String reviewerImage) {
        this.reviewerImage = reviewerImage;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }
}
