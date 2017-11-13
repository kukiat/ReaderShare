package com.example.kukiat.readershare;

/**
 * Created by kukiat on 11/13/2017 AD.
 */

public class User {
    private String name;
    private String email;
    private String password;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getName() {

        return name;

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User(String name, String email, String password) {

        this.name = name;
        this.email = email;
        this.password = password;
    }
}
