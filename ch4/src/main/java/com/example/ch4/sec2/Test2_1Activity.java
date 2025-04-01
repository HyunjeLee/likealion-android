package com.example.ch4.sec2;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch4.R;

public class Test2_1Activity extends AppCompatActivity {

    float initX;

    long initTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_test21);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Log.d("LHJ", "back button pressed by callback");
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            initX = event.getRawX();
        }
        else if(event.getAction() == MotionEvent.ACTION_UP) {
            float diffX = initX - event.getRawX();
            if (diffX > 30) {
                Log.d("LHJ", "left move");
            } else if (diffX < -30) {
                Log.d("LHJ", "right move");
            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            Log.d("LHJ", "vol up key pressed");
        }
//        else if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (System.currentTimeMillis() - initTime > 3000) {
//                Toast.makeText(this, "press again to exit", Toast.LENGTH_SHORT).show();
//                initTime = System.currentTimeMillis();
//
//                return true;
//            }
//        }

        return super.onKeyDown(keyCode, event);
    }

    // 만약 back button만 이벤트 처리를 목적으로 한다면 onKeyDown()에서 처리하지 않는다 !!
    // onKeyDown은 모든 물리키와 시스템키에 대해 동작하므로 ..

//    @Override
//    public void onBackPressed() {  // 하지만 deprecated
//        Log.d("LHJ", "back button pressed");
//
//        super.onBackPressed();
//    }
    // callback으로 백 버튼 처리  // onCreate() 내에서 처리

}