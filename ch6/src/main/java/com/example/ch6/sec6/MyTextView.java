package com.example.ch6.sec6;

// 주가를 출력하는 TextView 개발
// 주가 데이터만 지정되면 알아서 빨강이나 파랑으로 출력하게끔

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

// 만드는 뷰의 최종 능력은 문자열 출력 // 하지만 문자열 출력 이전에 분기문을 통한 색상 결정 기능도 추가해야함
// TextView 상속보다는 동일한 능력의 AppcompatTextView 상속
public class MyTextView extends AppCompatTextView {
    public MyTextView(@NonNull Context context) {
        super(context);
    }

    public MyTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 뷰 화면의 드로잉을 위해 호출되는 onDraw 함수 커스텀
    @Override
    protected void onDraw(Canvas canvas) {
        try {
            int value = Integer.parseInt(getText().toString());
            if (value > 0) setTextColor(Color.RED);
            else if (value < 0) setTextColor(Color.BLUE);

            // 화면에 출력 되기 이전 색상을 선택하는 분기문을 먼저 실행 이후
            // 기존의 화면 출력 코드 실행
            super.onDraw(canvas);
        } catch (Exception e) {
            // try문 중 예외 발생 시 조작없이 기존의 화면 출력 진행
            super.onDraw(canvas);
        }

    }
}
