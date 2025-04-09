package com.example.ch6.sec6;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ch6.R;

import java.util.ArrayList;

// 🚀🚀🚀 코드의 대부분이 플랫폼api의 뷰들이 행하는 행위들을 다시 구현하고 있음  // 즉 커스텀!이기 때문에 1부터 100까지 짜는 것  // 프레임워크에서 제공하는 코드를 제공받지 못하므로 직접 짜는 것과 동일하다 !

// custom view 개발자 (선임 개발자)
// 동일한 화면을 출력하는 api 뷰가 없다
// 최상위 view 상속으로 개발
public class MyPlusMinusView extends View {
    Context context;
    int value;
    Bitmap plusBitmap;
    Bitmap minusBitmap;
    Rect plusRect;
    Rect minusRect;
    int textColor;

    // 뷰의 이벤트를 전파받기 위해 등록한 핸들러를 담는 곳
    ArrayList<OnMyChangeListener> listeners;

    // 생성자 3개
    public MyPlusMinusView(Context context) {   // 개발자 코드에서 직접 생성하는 경우
        super(context);
        this.context = context;
        init(null);
    }

    public MyPlusMinusView(Context context, @Nullable AttributeSet attrs) {  // 🚀🚀🚀 이 생성자의 호출은 누가 하나? // layoutinflater  // 프레임워크 생성
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    public MyPlusMinusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    // 최초에 한 번 호출되는 함수
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        // 뷰 전체를 지정한 색상으로 // 즉 뷰 내용을 지우는 것  // 알파는 투명도 // CYAN의 투명도는 255로 최대 // 아마 검정으로 칠해지겟지?
        canvas.drawColor(Color.alpha(Color.CYAN));

        Rect plusR = new Rect(0,0,plusBitmap.getWidth(), plusBitmap.getHeight());  // left와 top은 시작 좌표
        Rect minusR = new Rect(0, 0, minusBitmap.getWidth(), minusBitmap.getHeight());

        Paint paint = new Paint();

        paint.setTextSize(80);  // 지금 수치는 px
        paint.setColor(textColor);

        canvas.drawText(String.valueOf(value), 260, 150, paint);  // 출력 위치 좌표값  // 여기도 px  // 실제 개발 시에는 연산 필요
        // 1번 인자 ; 그릴 이미지  // 2번 인자 ; 1번의 이미지를 얼만큼 사용할지  // 3번 인자 ; 캔버스를 얼마나 사용할지  // 4번 인자 ; 스타일
        canvas.drawBitmap(plusBitmap, plusR, plusRect, null);
        canvas.drawBitmap(minusBitmap, minusR, minusRect, null);

        // 지금까지의 코드가 선임개발자의 코드다 !!!  // 복잡하넹 @_@
    }

    private void init(AttributeSet attributeSet) {
        // 이미지 준비
        plusBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.plus);
        minusBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.minus);

        // 드로잉을 위한 사각형 준비
        plusRect = new Rect(10,10,210,210);  // 좌표임 x축 10 -> 210 // y축 동일
        minusRect = new Rect(400, 10, 600, 210);  // x축 400 -> 600 // y축은 위와 동일

        if (attributeSet != null) {  // 개발자가 커스텀 속성을 부여했다면
            TypedArray array = context.obtainStyledAttributes(attributeSet, R.styleable.MyView);  // 🚀🚀🚀 이 부분은 어떤 코드야?  // 커스텀 속성을 추가한 개발자가 속성 적용을 위한 코드
            textColor = array.getColor(R.styleable.MyView_customTextColor, Color.RED);  // red는 default 값
        }

        listeners = new ArrayList<>();
    }

    // 사용하는 액티비티 내에서 이벤트 핸들러를 등록하기 위해 호출하는 함수
    public void setOnMyChangeListener(OnMyChangeListener listener) {
        listeners.add(listener);
    }

    // 뷰의 사이즈를 결정하기 위해 자동 호출
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  // 🚀🚀🚀 이건 무슨 메서드?  // 커스텀뷰 전체 사이즈  // xml에서의 크기는 의뢰 // 실제 처리는 해당 메서드에서 결정됨
        // 액티비티 개발자(하위 개발자)가 지정한 사이즈값을 참조해서 계산
        // 액티비티에서 설정한 사이즈 정보 획득
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int height = 0;

        if (widthMode == MeasureSpec.AT_MOST) {
            width = 700;
        } else if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            height = 250;
        } else if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        }

        // 어떻게 사이즈 결정하든
        // 밑의 setMeasuredDimension을 호출해서 알려준 사이즈가 뷰의 사이즈가 된다
        setMeasuredDimension(width, height);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        // 유저 터치 지점이 plus? minus?
        if (plusRect.contains(x, y) && event.getAction() == MotionEvent.ACTION_DOWN) {  // plus의 경우
            value++;
            invalidate();  // 뷰의 화면이 다시 그려져야한다는 신호  // 이 경우 onDraw() 자동적으로 재호출

            // 액티비티(하위 개발자의 코드 상)의 이벤트 핸들러도 실행
            for (OnMyChangeListener listener: listeners) {
                listener.onChange(value);  // : 이게 delegation이다 이마리야  // 액티비티에서 정의한 뷰에 대한 이벤트 리스너를 직접 뷰까지 끌고와서 처리
            }

            return true;
        } else if (minusRect.contains(x, y) && event.getAction() == MotionEvent.ACTION_DOWN) {  // minus의 경우
            value--;
            invalidate();  // 뷰의 화면이 다시 그려져야한다는 신호  // 이 경우 onDraw() 자동적으로 재호출

            // 액티비티(하위 개발자의 코드 상)의 이벤트 핸들러도 실행
            for (OnMyChangeListener listener : listeners) {
                listener.onChange(value);
            }

            return true;
        }

        return false;
    }


}
