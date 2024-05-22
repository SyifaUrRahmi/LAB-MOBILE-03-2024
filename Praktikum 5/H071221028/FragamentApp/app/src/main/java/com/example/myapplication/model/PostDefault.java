package com.example.myapplication.model;

public class PostDefault extends Post {
    private int imageDrawableId;
    public PostDefault(String name, String username, String content, int profileDrawableResourceId, int imageDrawableId) {
        super(name, username, content, profileDrawableResourceId);
        this.imageDrawableId = imageDrawableId;
    }

    public int getImageDrawableId() {
        return imageDrawableId;
    }

    public void setImageDrawableId(int imageDrawableId) {
        this.imageDrawableId = imageDrawableId;
    }
}
