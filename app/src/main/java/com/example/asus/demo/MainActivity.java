package com.example.asus.demo;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.asus.demo.adapter.ListTextAdapter;
import com.example.asus.demo.entity.ListDataEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private String[] data = {"a","b","c","d","e","f","g","h","i","j","k","l"};
    private List<ListDataEntity> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.lv);
        mList = new ArrayList<>();
        for(int i=0;i<20;i++){
            ListDataEntity entity = new ListDataEntity();
            entity.setId(android.R.drawable.btn_star);
            entity.setString("字母 ："+i);
            mList.add(entity);

        }
         ListTextAdapter adapter = new ListTextAdapter(this,R.layout.item_listview,mList);
        mListView.setAdapter(adapter);
    }
}
