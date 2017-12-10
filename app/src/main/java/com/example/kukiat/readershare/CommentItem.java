package com.example.kukiat.readershare;

/**
 * Created by aiy on 12/11/17.
 */

public class CommentItem {
    private String id;
    private String commentContent;
    private String commentName;
    private String commentUserImage;
    private int commentTimestamp;

    public CommentItem(String id, String commentContent, String commentName,
                       String commentUserImage, int commentTimestamp) {
        this.id = id;
        this.commentContent = commentContent;
        this.commentName = commentName;
        this.commentUserImage = commentUserImage;
        this.commentTimestamp = commentTimestamp;
    }

    public String getCommentUserImage() {
        return commentUserImage;
    }

    public void setCommentUserImage(String commentUserImage) {
        this.commentUserImage = commentUserImage;
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

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentUserName) {
        this.commentName = commentUserName;
    }

    public int getCommentTimestamp() {
        return commentTimestamp;
    }

    public void setCommentTimestamp(int commentTimestamp) {
        this.commentTimestamp = commentTimestamp;
    }

}
