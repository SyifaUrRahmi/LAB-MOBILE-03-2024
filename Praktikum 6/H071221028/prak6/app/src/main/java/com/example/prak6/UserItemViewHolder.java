package com.example.prak6;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserItemViewHolder extends RecyclerView.ViewHolder {
    ImageView userImageView;
    TextView usernameTextView;
    TextView userEmailTextView;
    public UserItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.userImageView = itemView.findViewById(R.id.user_image_view);
        this.usernameTextView = itemView.findViewById(R.id.user_name_tv);
        this.userEmailTextView = itemView.findViewById(R.id.user_email_tv);

    }
}

