package com.example.myapplication.model;

import android.net.Uri;

public class PostPersonal extends Post {

    private Uri imageUri;

    public PostPersonal(String name, String username, String content, int profileDrawableResourceId, Uri imageUri) {
        super(name, username, content, profileDrawableResourceId);
        this.imageUri = imageUri;

    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}
