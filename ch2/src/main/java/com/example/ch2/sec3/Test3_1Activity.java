package com.example.ch2.sec3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch2.R;
import com.example.ch2.databinding.ActivityTest31Binding;

public class Test3_1Activity extends AppCompatActivity {

    /*  findViewById 방식
    Button visibleBtn;
    Button invisibleBtn;
    TextView targetView;
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        /*  findViewById 방식
        setContentView(R.layout.activity_test31);  // 이 코드로 화면 출력 완료

        // 뷰 매핑
        visibleBtn = findViewById(R.id.visibleBtn);
        invisibleBtn = findViewById(R.id.invisibleBtn);
        targetView = findViewById(R.id.targetView);
        // 이벤트 핸들링
        visibleBtn.setOnClickListener( view -> {
                targetView.setVisibility(TextView.VISIBLE);
        });
        invisibleBtn.setOnClickListener( view -> {
            targetView.setVisibility(TextView.INVISIBLE);
        });
         */

        // 자동으로 만들어진 binding 클래스에 inflate 명령
        ActivityTest31Binding binding = ActivityTest31Binding.inflate(getLayoutInflater());
        // 화면 출력
        setContentView(binding.getRoot());

        // 이벤트 핸들링
        binding.visibleBtn.setOnClickListener(view -> {
            binding.targetView.setVisibility(TextView.VISIBLE);
        });
        binding.invisibleBtn.setOnClickListener( view -> {
            binding.targetView.setVisibility(TextView.INVISIBLE);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}