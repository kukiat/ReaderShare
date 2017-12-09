package com.example.kukiat.readershare;

/**
 * Created by kukiat on 9/12/2017 AD.
 */

public class ReviewItem {
    private String id;
    private String bookImage;
    private String bookName;
    private String reviewerId;
    private String reviewContent;
    private int reviewLike;
    private int reviewRating;
    private String reviewTitle;
    private int timestamp;

    public ReviewItem(String id, String bookImage, String bookName,
                      String reviewerId, String reviewContent, int reviewLike,
                      int reviewRating, String reviewTitle, int timestamp) {
        this.id = id;
        this.bookImage = bookImage;
        this.bookName = bookName;
        this.reviewerId = reviewerId;
        this.reviewContent = reviewContent;
        this.reviewLike = reviewLike;
        this.reviewRating = reviewRating;
        this.reviewTitle = reviewTitle;
        this.timestamp = timestamp;
    }


    public void seteId(String id) {
        this.id = id;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setReviewerId(String reviewerId) {
        this.reviewerId = reviewerId;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public void setReviewLike(int reviewLike) {
        this.reviewLike = reviewLike;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String geteId() {
        return id;
    }

    public String getBookImage() {
        return bookImage;
    }

    public String getBookName() {
        return bookName;
    }

    public String getReviewerId() {
        return reviewerId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public int getReviewLike() {
        return reviewLike;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public int getTimestamp() {
        return timestamp;
    }
}
