package com.example.kukiat.readershare;

/**
 * Created by aiy on 12/11/17.
 */

public class CommentItem {
    private String id;
    private String commentContent;
    private String commentUserName;
    private String commentUserId;
    private int commentLike;
    private int commentTimestamp;
    private String commentUserEmail;

    public CommentItem(String id, String commentContent, String commentUserName,
                       String commentUserId, int commentLike, int commentTimestamp,
                       String commentUserEmail) {
        this.id = id;
        this.commentContent = commentContent;
        this.commentUserName = commentUserName;
        this.commentUserId = commentUserId;
        this.commentLike = commentLike;
        this.commentTimestamp = commentTimestamp;
        this.commentUserEmail = commentUserEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentUserName() {
        return commentUserName;
    }

    public void setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public int getCommentLike() {
        return commentLike;
    }

    public void setCommentLike(int commentLike) {
        this.commentLike = commentLike;
    }

    public int getCommentTimestamp() {
        return commentTimestamp;
    }

    public void setCommentTimestamp(int commentTimestamp) {
        this.commentTimestamp = commentTimestamp;
    }

    public String getCommentUserEmail() {
        return commentUserEmail;
    }

    public void setCommentUserEmail(String commentUserEmail) {
        this.commentUserEmail = commentUserEmail;
    }
}
