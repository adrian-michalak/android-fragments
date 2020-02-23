package com.example.adrianmichalak.fragment_michalak.retrofit;

import com.example.adrianmichalak.fragment_michalak.models.PostsModel;
import com.example.adrianmichalak.fragment_michalak.models.UsersModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestInterface {
    @GET("/posts")
    Call<List<PostsModel>> getPost();

    @GET("/users")
    Call<List<UsersModel>> getUsers();
}
