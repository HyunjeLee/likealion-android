package com.example.ch6.sec1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ch6.R;
import com.example.ch6.databinding.ActivityTest12Binding;
import com.example.ch6.databinding.ItemRecyclerviewBinding;

import java.util.ArrayList;

public class Test1_2Activity extends AppCompatActivity {

    ActivityTest12Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        binding = ActivityTest12Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<String> list = new ArrayList<>();
        for (int i=0; i<= 10; i++)  list.add("Item " +  i);

        binding.main.setAdapter(new MyAdapter(list));
        binding.main.setLayoutManager(new LinearLayoutManager(this));
        binding.main.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    ItemRecyclerviewBinding binding;

    MyViewHolder(ItemRecyclerviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}

class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    ArrayList<String> datas;

    MyAdapter(ArrayList<String> data){
        this.datas = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecyclerviewBinding binding  // 리사이클러뷰의 아이템 레이아웃에 대한 바인딩
                = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
                false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.itemData.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}