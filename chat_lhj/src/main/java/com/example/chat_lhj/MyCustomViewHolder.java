package com.example.chat_lhj;

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat_lhj.databinding.ItemRecylcerBinding;

class MyCustomViewHolder extends RecyclerView.ViewHolder {

    ItemRecylcerBinding binding;

    public MyCustomViewHolder(@NonNull ItemRecylcerBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }
}
