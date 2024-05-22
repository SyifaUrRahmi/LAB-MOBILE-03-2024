package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.Post;
import com.example.myapplication.model.PostDefault;
import com.example.myapplication.model.PostPersonal;

import java.util.ArrayList;
//view holder referensi komponen by id;
public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private ArrayList<Post> postList;

    public PostAdapter() {
        this.postList = postList= new ArrayList<>();
    }

    public void setPostList(ArrayList<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.post_item,parent,false);
        return new PostViewHolder(view);
    }


    // untuk inject data ke viewnya, dtanya dar datasc
    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Post post = postList.get(position);
        holder.accountUsernameText.setText(post.getUsername());
        holder.accountName.setText(post.getName());
        holder.descriptionTextView.setText(post.getContent());

        holder.postImage.setImageResource(post.getProfileDrawableResourceId());
        if(post instanceof PostDefault){
            holder.postImage.setImageResource(((PostDefault)post).getImageDrawableId());
        } else{
            holder.postImage.setImageURI(((PostPersonal)post).getImageUri());
        }
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postList.remove(post);
                notifyItemChanged(position);
//                notifyItemRemoved(position);
            }
        });

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
