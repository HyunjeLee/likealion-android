package com.example.ch6.sec5;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch6.R;
import com.example.ch6.databinding.ActivityTest51Binding;

public class Test5_1Activity extends AppCompatActivity {

    ActivityTest51Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        binding = ActivityTest51Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // thread 구동
        ProgressThread thread = new ProgressThread();
        thread.start();

        binding.sbSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // 값이 변경되는 순간
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                binding.tvResult.setText("현재 값 : " + progress);
            }

            // 터치하는 순간
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            // 터치를 떼는 순간
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                Toast.makeText(Test5_1Activity.this, "선택 값 : " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    class ProgressThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                // 1초식 쉬기
                SystemClock.sleep(1000);

                binding.pbProgress.incrementProgressBy(10);  // 실제 진행도  // 검정
                binding.pbProgress.incrementSecondaryProgressBy(15);  // 예비 진행도  // 회색  // 스트리밍 버퍼, 예상 진행도
            }

        }
    }
}