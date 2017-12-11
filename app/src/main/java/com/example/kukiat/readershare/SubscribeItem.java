package com.example.kukiat.readershare;

/**
 * Created by aiy on 12/11/17.
 */

public class SubscribeItem {
    private String id;
    private String subscribeName;
    private String subscribeImage;
    private String subscribeEmail;
    public String getId() {
        return id;
    }

    public String getSubscribeEmail() {
        return subscribeEmail;
    }

    public void setSubscribeEmail(String subscribeEmail) {
        this.subscribeEmail = subscribeEmail;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubscribeName() {
        return subscribeName;
    }

    public void setSubscribeName(String subscribeName) {
        this.subscribeName = subscribeName;
    }

    public String getSubscribeImage() {
        return subscribeImage;
    }

    public void setSubscribeImage(String subscribeImage) {
        this.subscribeImage = subscribeImage;
    }

    public SubscribeItem(String id, String subscribeName, String subscribeImage, String subscribeEmail) {

        this.id = id;
        this.subscribeName = subscribeName;
        this.subscribeImage = subscribeImage;
        this.subscribeEmail = subscribeEmail;
    }
}
