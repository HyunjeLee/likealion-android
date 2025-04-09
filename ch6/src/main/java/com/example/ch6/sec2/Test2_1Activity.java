package com.example.ch6.sec2;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch6.R;
import com.example.ch6.databinding.ActivityTest21Binding;

public class Test2_1Activity extends AppCompatActivity {

    ActivityTest21Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        binding = ActivityTest21Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String[] datas = getResources().getStringArray(R.array.location);

        binding.spinner.setAdapter(new ArrayAdapter<>(Test2_1Activity.this, android.R.layout.simple_spinner_item, datas));

        ArrayAdapter<String> adapter1
                = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, datas);
        binding.autoView.setAdapter(adapter1);
    }
}