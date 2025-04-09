package com.example.ch6.sec3;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch6.R;
import com.example.ch6.databinding.ActivityTest31Binding;

public class Test3_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        ActivityTest31Binding binding = ActivityTest31Binding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_test31);
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        String data = "복수초 \n img \n 이른봄 설산에서 만나는 복수초는 어쩌구저쩌구...";

        SpannableStringBuilder builder = new SpannableStringBuilder(data);  // string 추가

        // 이제 필요한 만큼 각 `span` 객체 추가
        int start = data.indexOf("img"); // img 라는 문자열이 있는 최초의 위치
        if (start > -1) {  // 없다면 -1, 존재한다면 0 이상  // 즉 존재하는 경우
            int end = start + "img".length();

            // 이미지
            Drawable dr = ResourcesCompat.getDrawable(getResources(), R.drawable.img1, null);
            // 그리는 좌표 설정
            dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(dr);
            builder.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        start = data.indexOf("복수초");
        if (start > -1) {

            int end = start + "복수초".length();

            StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
            RelativeSizeSpan sizeSpan = new RelativeSizeSpan(2.0f);
            builder.setSpan(styleSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(sizeSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            // 추가 코드
            builder.setSpan(new ForegroundColorSpan(Color.RED),
                    start,
                    end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new BackgroundColorSpan(Color.CYAN),
                    start,
                    end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            // url 추가 코드
            builder.setSpan(new URLSpan("http://www.google.com"),
                    start,
                    end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        }

        binding.tv1.setText(builder);
        // url 하이퍼링크 동작 위한 코드
        binding.tv1.setMovementMethod(LinkMovementMethod.getInstance());

        // -------------------------

        // html 태그 효과를 적용해서 문자열을 출력해보자!
        // 내부적으로 span 클래스를 이용하지만 프레임워크에서 진행 (`Html.fromHtml()`)
        String html = "<font color='RED'>얼레지</font><br/><img src='img1'/><br/>곰배령...";

        // 단 <img> 태그의 경우 이미지에 대한 코드는 직접 작성해야함 -> MyImageGetter()
        // 결국 문자열이므로 이미지 주소를 직접 넣어도 특별한 동작이 되지 않기 때문
        // 이미지태그가 없다면 매개변수는 2개면 됨
        binding.tv2.setText(Html.fromHtml(
                html,
                Html.FROM_HTML_MODE_LEGACY,
                new MyImageGetter(),
                null));


        // 람다식 변환
        /*
        binding.tv2.setText(Html.fromHtml(
                html,
                Html.FROM_HTML_MODE_LEGACY,
                source -> {
                    if (source.equals("img1")) {  // 지금은 이미지태그가 한 개지만 이미지 태그가 여러 개라면 이와 같이 분기함
                        Drawable dr = ResourcesCompat.getDrawable(getResources(), R.drawable.img2, null);
                        dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
                        return dr;
                    }

                    return null;
                },
                null));

        */




    }

    class MyImageGetter implements Html.ImageGetter {
        // fromHtml() 함수가 분석하는 문자열에 <img> 이미지 태그 만나면 호출됨

        @Override
        public Drawable getDrawable(String source) {  // 매개변수로 이미지 태그의 src값을 전달됨
            // 서버, 파일, 리소스 등을 직접 획득해서 리턴해야함

            if (source.equals("img1")) {  // 지금은 이미지태그가 한 개지만 이미지 태그가 여러 개라면 이와 같이 분기함
                Drawable dr = ResourcesCompat.getDrawable(getResources(), R.drawable.img2, null);
                dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
                return dr;
            }

            return null;
        }
    }
}