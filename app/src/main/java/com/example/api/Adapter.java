package com.example.api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    List<Users> users;
    Context context;

    public Adapter(List<Users> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
       return new  MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(users.get(position).getName());
        holder.userName.setText(users.get(position).getUsername());
        holder.email.setText(users.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name,userName,email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            userName = (TextView)itemView.findViewById(R.id.user_name);
            email = (TextView)itemView.findViewById(R.id.email);
        }

    }
}