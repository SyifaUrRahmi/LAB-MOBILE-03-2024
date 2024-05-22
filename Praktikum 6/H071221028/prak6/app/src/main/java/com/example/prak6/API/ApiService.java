package com.example.prak6.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("users")
    Call<GetUserListResponse> getUsers(@Query("page")int page);

    @GET("users/{id}")
    Call<GetUserResponse>getUser(@Path("id")int id);
}
