package com.example.ch10;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SQLiteDatabase database = openOrCreateDatabase("testdb", MODE_PRIVATE, null);  // db 생성

        String query = "select name from sqlite_master where type='table' and name=?";  // 쿼리문 작성  // 테이블 중에 해당하는 이름의 테이블명 select
        Cursor cursor = database.rawQuery(query, new String[]{"test1_tb"});  // 커서를 통해 쿼리문 실행  // 결과값
        boolean exists = cursor.getCount() > 0; // 획득한 것이 있냐 없냐
        cursor.close();

        if (!exists) {
            // 획득한 것이 없는 경우  // 테이블이 없는 경우
            // 테이블 생성
            database.execSQL("create table test1_tb (" +
                    "_id integer primary key autoincrement," +  // 테이블 row명 _id
                    "title text," +  // 테이블 row명 title
                    "content text)");  // 테이블 row명 context

            // 테이블에 데이터 제장
            for (int i = 1; i <= 10; i++) {
                database.execSQL("insert into test1_tb (title, content) values (?,?)",
                        new String[]{"title" + i, "content" + i});
            }
        }

        Cursor cursor1 = database.rawQuery("select * from test1_tb", null);
        while (cursor1.moveToNext()) {
            Log.d("LHJ", cursor1.getInt(0) + ":"
                    + cursor1.getString(1) + ":"
                    + cursor1.getString(2));
        }
        cursor1.close();

    }
}