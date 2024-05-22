package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;


//referense komponen by id
public class PostViewHolder extends RecyclerView.ViewHolder {
  CircleImageView profileImage;
  TextView accountName, accountUsernameText, descriptionTextView;
  ImageButton  deleteButton;
  ImageView postImage;
  Context context;


  public PostViewHolder(@NonNull View itemView) {
      super(itemView);

      profileImage = itemView.findViewById(R.id.post_profile_image);
      accountName = itemView.findViewById(R.id.post_account_name);
      accountUsernameText = itemView.findViewById(R.id.post_account_username);
      descriptionTextView= itemView.findViewById(R.id.description);
      deleteButton = itemView.findViewById(R.id.post_delete_button);
      postImage = itemView.findViewById(R.id.post_image);
      context = itemView.getContext();
  }
}
