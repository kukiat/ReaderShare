package com.example.kukiat.readershare;

/**
 * Created by aiy on 12/11/17.
 */

public class ReleaseItem {
    private String id;
    private String bookImage;
    private String reviewerId;
    private String reviewTitle;
    private int timestamp;

    public ReleaseItem(String id, String bookImage, String reviewerId, String reviewTitle,
                       int timestamp) {

        this.id = id;
        this.bookImage = bookImage;
        this.reviewerId = reviewerId;
        this.reviewTitle = reviewTitle;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public String getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(String reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

}
