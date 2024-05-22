package com.example.prak6.API;

import com.google.gson.annotations.SerializedName;

public class GetUserResponse {
    @SerializedName("data")
    private  User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
