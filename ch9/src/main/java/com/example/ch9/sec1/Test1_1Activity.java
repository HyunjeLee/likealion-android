package com.example.ch9.sec1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch9.R;
import com.example.ch9.databinding.ActivityTest11Binding;

public class Test1_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        ActivityTest11Binding binding = ActivityTest11Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btn1.setOnClickListener(v -> {
            // Sub1Activity는 명시적 호출해야함
            Intent intent = new Intent(this, Sub1Activity.class);  // todo: 왜 람다로 하면 this 가능 ??
            startActivity(intent);
        });

        binding.btn2.setOnClickListener(new View.OnClickListener() {
            // Sub2는 manifest에서 intent-filter 설정을 통해 암시적 호출하자
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("ACTION_SUB2");  // note: exported: true 설정 필요
                startActivity(intent);
            }
        });

        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("ACTION_SUB3");  // note: 오타나면 그냥 err
                intent.addCategory("com.example.ch9.CATEGORY_SUB3");
                startActivity(intent);
            }
        });

        binding.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("ACTION_SUB4", Uri.parse("http://www.google.com"));  // note: 이전까지 기본 생성자로 호출했지만  // 인자로 action을 주며 생성할 수도 있다.
                startActivity(intent);
            }
        });
    }
}