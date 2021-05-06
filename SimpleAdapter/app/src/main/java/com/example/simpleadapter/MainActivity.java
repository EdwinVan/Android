package com.example.simpleadapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    // 定义名字数组
    private String[] name = {"张三", "王五", "赵六"};
    // 定义描述任务数组
    private String[] desc = {"唱歌", "跳舞", "打球"};
    // 定义头像数组
    private int[] icon = new int[]
            {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_layout);
        ListView list_View = (ListView) findViewById(R.id.list_view1);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < name.length; i++) {
            Map<String, Object> list_item = new HashMap<String, Object>();
            list_item.put("icon", icon[i]);
            list_item.put("name", name[i]);
            list_item.put("desc", desc[i]);
            list.add(list_item);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.list_item_layout,
                new String[]{"name", "icon", "desc"}, new int[]{R.id.name, R.id.icon, R.id.desc});
        list_View.setAdapter(adapter);
    }
}