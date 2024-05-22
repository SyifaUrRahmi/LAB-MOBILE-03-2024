package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {
    private String name, username;
    private String content;
    private int profileDrawableResourceId;

    public Post(String name, String username, String content, int profileDrawableResourceId) {
        this.name = name;
        this.username = username;
        this.content = content;
        this.profileDrawableResourceId = profileDrawableResourceId;
    }

    protected Post(Parcel in) {
        name = in.readString();
        username = in.readString();
        content = in.readString();
        profileDrawableResourceId = in.readInt();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getProfileDrawableResourceId() {
        return profileDrawableResourceId;
    }

    public void setProfileDrawableResourceId(int profileDrawableResourceId) {
        this.profileDrawableResourceId = profileDrawableResourceId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(username);
        parcel.writeString(content);
        parcel.writeInt(profileDrawableResourceId);
    }
}
