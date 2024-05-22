package com.example.prak6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prak6.API.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserItemViewHolder> {
    private ArrayList<User> userList;

    private OnUserItemClickListener onUserItemClickListener;

    public UserListAdapter(OnUserItemClickListener listener) {
        this.userList = new ArrayList<>();
        this.onUserItemClickListener = listener;
    }

    public void addUsers(List<User> userList) {
        this.userList.addAll(userList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item, parent, false);
        return new UserItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemViewHolder holder, int position) {
        User user = userList.get(position);
        holder.userEmailTextView.setText(user.getEmail());
        holder.usernameTextView.setText(user.getFirstName() + " " + user.getLastName());

        Picasso.get().load(user.getAvatar()).into(holder.userImageView);
        holder.itemView.setOnClickListener(v -> {
            onUserItemClickListener.onUserItemClick(user);
        });
    }

    @Override
    public int getItemCount() {
        return this.userList.size();
    }

    public interface OnUserItemClickListener{
        void onUserItemClick(User user);
    }
}