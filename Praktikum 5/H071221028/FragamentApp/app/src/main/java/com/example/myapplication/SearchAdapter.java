package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.Post;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {
    private ArrayList<Post> postList;

    public SearchAdapter(ArrayList<Post> post) {
        this.postList = post;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.seacrh,parent,false);
        return new SearchViewHolder(view);
    }


    // untuk inject data ke viewnya, dtanya dar datasc
    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Post post = postList.get(position);
        holder.accountUsernameText.setText(post.getUsername());
        holder.profileImage.setImageResource(post.getProfileDrawableResourceId());
        holder.accountName.setText(post.getName());


        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.context, ProfileActivity.class);
            intent.putExtra("post", post);
            holder.context.startActivity(intent);
        });

//        holder.profileImage.setOnClickListener(view -> {
//            Intent intent = new Intent(holder.context, ProfileActivity.class);
//            intent.putExtra("post", post);
//            holder.context.startActivity(intent);
//        });
//
//        holder.accountUsernameText.setOnClickListener(view -> {
//            Intent intent = new Intent(holder.context, ProfileActivity.class);
//            intent.putExtra("post", post);
//            holder.context.startActivity(intent);
//        });
    }

    @Override
    public int getItemCount() {
        return this.postList.size();
    }
}
