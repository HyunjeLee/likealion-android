package com.example.ch7.sec1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch7.R;
import com.example.ch7.databinding.ActivityTest11Binding;

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

        // 퍼미션 요청 다이얼로그가 닫혔을 때 // 사후 처리 콜백  // 보통 거부 상황에서 앱 종료 없이 계속적으로 요청 위함
        ActivityResultLauncher<String> launcher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),  // 퍼미션 요청 다이얼로그
                isGranted -> {  // callback 매개변수임  // 람다식
                    if(isGranted) {  // 권한 획득 성공  // 원래 수행하려던 작업 속행
                        Intent intent = new Intent("CH7_ACTION_TWO");
                        startActivity(intent);
                    } else {    // 권한 획득 실패  // 커스텀하지 않는다면 앱이 종료되는 시점
                        Toast.makeText(this, "permission denied ..", Toast.LENGTH_SHORT)
                                .show();
                    } // ❓❓❓ 왜 2번 다이얼로그 출력 이후에는 다이얼로그가 안 뜨지?
                      // 🚀🚀🚀 안드로이드 기본 동작  // 2번 거부 시 다시 묻지 않음  // 계속적인 출력을 원한다면 추가적인 설정 필요
                }
        );

        binding.btn1.setOnClickListener(view -> {
            // normal 보안레벨의 퍼미션은 그냥 manifest 등록으로 끗
            Intent intent = new Intent("CH7_ACTION_ONE");
            startActivity(intent);
        });

        binding.btn2.setOnClickListener(view -> {
            // dangerous 라면  // 퍼미션 상태 체크  // 만약 거부 상태라면 다이얼로그 띄우기

            // 현재 퍼미션 상태 체크
            if (ContextCompat.checkSelfPermission(this,
                    "com.example.ch7_outer.TWO_PERMISSION") == PackageManager.PERMISSION_GRANTED) {
                // 허락 상태  // 원래 수행하려던 작업 속행
                Intent intent = new Intent("CH7_ACTION_TWO");
                startActivity(intent);
            } else {
                // 거부 상태  // 계속 퍼미션 요청
                launcher.launch("com.example.ch7_outer.TWO_PERMISSION");
            }
        });
    }
}