package com.example.prak6.API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUserListResponse {
    @SerializedName("page")
    private int page;

    @SerializedName("data")
    private List<User> users;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
