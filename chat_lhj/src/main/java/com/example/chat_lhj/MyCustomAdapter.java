package com.example.chat_lhj;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat_lhj.databinding.ItemRecylcerBinding;

import java.util.ArrayList;

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomViewHolder> {

    ArrayList<Message> messages;

    protected MyCustomAdapter(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public MyCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecylcerBinding binding = ItemRecylcerBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);

        return new MyCustomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCustomViewHolder holder, int position) {
        holder.binding.tvName.setText(messages.get(position).getName());
        holder.binding.tvMessage.setText(messages.get(position).getMessage());
        holder.binding.tvDate.setText(messages.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
