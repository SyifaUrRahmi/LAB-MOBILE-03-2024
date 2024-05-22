package com.example.myapplication;

import com.example.myapplication.model.Post;
import com.example.myapplication.model.PostDefault;
import java.util.ArrayList;

public class DB {

    private static ArrayList<Post> postList = new ArrayList<>();

    public static void init(){
        postList.add(new PostDefault("Audi Motors", "audi","this is audi", R.drawable.audi_logo, R.drawable.audi_post));
        postList.add(new PostDefault("BMW Motors", "BMW","this is bmw", R.drawable.bmw_logo, R.drawable.bmw_post));
        postList.add(new PostDefault("Jaguar Motors", "Jaguar","this is jaguar", R.drawable.jaguar_logo, R.drawable.jaguar_post));
        postList.add(new PostDefault("Porsche Motors", "Porsche","this is porsche", R.drawable.porsche_logo, R.drawable.porsche_post));
        postList.add(new PostDefault("Mazda Motors", "Mazda","this is mazda", R.drawable.mazda_logo, R.drawable.mazda_post));
    }

    public static void add(Post post){
        postList.add(0, post);
    }

    public static ArrayList<Post> getPostList() {
        return postList;
    }

    public static ArrayList<Post> postList() {
        return postList;
    }
}
