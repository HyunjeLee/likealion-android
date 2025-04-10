package com.example.ch10;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch10.databinding.ActivityTest2Binding;

public class Test2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        ActivityTest2Binding binding = ActivityTest2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btnInsert.setOnClickListener(view -> {
            String name = binding.etName.getText().toString();
            String address = binding.etAddress.getText().toString();

            SQLiteDatabase db = new DBHelper(this).getWritableDatabase();
            // 컬럼 데이터를 ContentValues로
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("address", address);

            db.insert("tb_user", null, values);
            db.close();
        });

        binding.btnQuery.setOnClickListener(view -> {
            // 기존의 db 생성과정을 helper를 통해 대체
            SQLiteDatabase db = new DBHelper(this).getReadableDatabase();

            Cursor cursor = db.query("tb_user", null, null, null, null, null, null);
            // db.rawQuery("select * from tb_user", null)  // 위 코드는 이 코드와 같음  // query에 조건에 null은 조건없음의 의미

            String result = "";

            while(cursor.moveToNext()) {
                result += cursor.getString(1) + ":" + cursor.getString(2) + "\n";
            }

            binding.tvResult.setText(result);
        });


    }
}