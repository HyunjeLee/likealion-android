package com.example.ch9.sec2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch9.R;
import com.example.ch9.databinding.ActivityTest21Binding;

public class Test2_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        ActivityTest21Binding binding = ActivityTest21Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                // 요청의 실행자는 2가지.. 퍼미션(RequestPermission), 인텐트 발생(SatartActivityForResult)
                // 이전 버전에서는 위 2개를 각각 다른 함수로 처리했지만 최신버전에서는 런처를 통해 같이 처리함

                new ActivityResultContracts.StartActivityForResult(),  // 이 경우 인텐트 발생
                new ActivityResultCallback<ActivityResult>() {  // 콜백
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        Intent intent = o.getData();
                        binding.tvResult.setText(intent.getStringExtra("result"));
                    }
                }
        );

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Test2_1Activity.this, SomeActivity.class);
                intent.putExtra("data1", "hello");
                intent.putExtra("data2", 100);
                launcher.launch(intent);
            }
        });

    }
}