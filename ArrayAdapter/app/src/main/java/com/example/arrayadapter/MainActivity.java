package com.example.arrayadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arrays);

        ListView listView = (ListView)findViewById(R.id.list_view1);

        String[] arr = {"章节1", "章节2", "章节3"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_expandable_list_item_1, arr);

        listView.setAdapter(adapter);
    }
}