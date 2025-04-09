package com.example.ch6.sec1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ch6.R;
import com.example.ch6.databinding.ActivityTest11Binding;

import java.util.ArrayList;
import java.util.HashMap;

public class Test1_1Activity extends AppCompatActivity {

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

        // 데이터 획득
        String[] arrayData = getResources().getStringArray(R.array.location);

        // 어댑터에 아이템 집어넣기
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayData);
        // 리스트뷰에 어댑터 매핑
        binding.list1.setAdapter(arrayAdapter);

        // 아이템 클릭 이벤트
//        binding.list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
        binding.list1.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(Test1_1Activity.this, arrayData[position], Toast.LENGTH_SHORT)
                    .show();
        });

        // SimpleAdapter
        ArrayList<HashMap<String, String>> simpleData = new ArrayList<>();

        for (int i = 0; i < arrayData.length; i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", arrayData[i]);
            map.put("content", String.valueOf(i*10));

            simpleData.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this,
                simpleData,
                android.R.layout.simple_list_item_2,
                new String[] {"name", "content"},
                new int[] {android.R.id.text1, android.R.id.text2}
        );
        binding.list2.setAdapter(simpleAdapter);

    }
}