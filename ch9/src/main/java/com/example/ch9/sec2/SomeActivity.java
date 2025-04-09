package com.example.ch9.sec2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch9.R;
import com.example.ch9.databinding.ActivitySomeBinding;

public class SomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        ActivitySomeBinding binding = ActivitySomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 자신을 실행시킨 Intent 객체 획득
        Intent intent = getIntent();
        // 넘어온 extra 추출
        String data1 = intent.getStringExtra("data1");
        int data2 = intent.getIntExtra("data2", 0);

        binding.tvExtra.setText(data1 + ":" + data2);

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 결과 데이터 포함시켜 intent에 extra로 추가
                intent.putExtra("result", "hello world");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}