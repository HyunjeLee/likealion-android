package com.example.ch4.sec1;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch4.R;
import com.example.ch4.databinding.ActivityTest11Binding;

public class Test1_1Activity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    ActivityTest11Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityTest11Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 내부 클래스가 이벤트핸들러
        binding.btn1.setOnClickListener(new MyEventHandler());
        binding.btn2.setOnClickListener(new MyEventHandler());

        // 액티비티 자체가 이벤트 핸들러
        binding.checkbox.setOnCheckedChangeListener(this);

        // 이벤트 핸들러가 재사용되지 않는다면 익명 클래스로 !
//        binding.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//              // 내부 로직
//            }
//        });

        // 위의 코드를 람다식으로 깔끔하게!
        binding.switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // 내부 로직
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // 내부 로직
    }

    // button 이벤트 핸들러 클래스
    class MyEventHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // 내부 로직
        }
    }
}