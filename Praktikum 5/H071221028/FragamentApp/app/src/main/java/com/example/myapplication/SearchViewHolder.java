package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchViewHolder extends RecyclerView.ViewHolder{

    ImageView profileImage;
    TextView accountName, accountUsernameText;
    Context context;
    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        profileImage = itemView.findViewById(R.id.iv_search);
        accountName = itemView.findViewById(R.id.tv_name);
        accountUsernameText = itemView.findViewById(R.id.tv_username);
        context = itemView.getContext();
    }
}
