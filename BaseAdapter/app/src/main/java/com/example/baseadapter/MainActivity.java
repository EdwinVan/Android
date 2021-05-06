package com.example.baseadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    private ListView mListView;
    private MyAdapter myAdapter;
    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        mListView = (ListView) findViewById(R.id.list_view);
        myAdapter = new MyAdapter(list, this);
        mListView.setAdapter(myAdapter);
    }

    private void initData(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("img",R.drawable.android);
        map.put("title","Android");
        map.put("button","学习");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img",R.drawable.java);
        map.put("title","Java");
        map.put("button","学习");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img",R.drawable.html);
        map.put("title","HTML5");
        map.put("button","学习");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img",R.drawable.c);
        map.put("title","C");
        map.put("button","学习");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("img",R.drawable.python);
        map.put("title","Python");
        map.put("button","学习");
        list.add(map);
    }
}