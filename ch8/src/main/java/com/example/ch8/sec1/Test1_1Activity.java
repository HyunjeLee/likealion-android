package com.example.ch8.sec1;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch8.R;
import com.example.ch8.databinding.ActivityTest11Binding;

public class Test1_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        EdgeToEdge.enable(this);  // 해당 코드 설정 시 액션바까지 contentview가 차지함  // 따라서 뷰 겹침 발생

        ActivityTest11Binding binding = ActivityTest11Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btn1.setOnClickListener( view -> {
            Intent intent = new Intent(this, Sub1Activity.class);
            startActivity(intent);
        });

        binding.btn2.setOnClickListener( view -> {
            Intent intent = new Intent(this, Sub2Activity.class);
            startActivity(intent);
        });


    }
}