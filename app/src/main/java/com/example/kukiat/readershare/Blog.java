package com.example.kukiat.readershare;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kukiat on 11/4/2017 AD.
 */

public class Blog {
    String status;

    int count;

    @SerializedName("count_total")
    int totalCount;

    int pages;

    List<Post> posts;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}