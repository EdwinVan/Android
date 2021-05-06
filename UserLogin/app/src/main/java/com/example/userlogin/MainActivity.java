package com.example.userlogin;

import androidx.annotation.IdRes;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends Activity implements View.OnClickListener,
RadioGroup.OnCheckedChangeListener{
    // 定义字符串用来保存各个信息
    private String phone_str = "";
    private String password_str = "";

    // 默认选中男性
    private String sex_str = "男性";
    private String hobby_str = "1";
    private String city_str = "";

    // 组件定义
    EditText phone_edit, password_edit;
    RadioGroup sex_group;
    RadioButton nan_but, nv_but;
    CheckBox play, read, music;
    Button register;
    Spinner spinner;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 组件初始化
        phone_edit = (EditText) findViewById(R.id.phone);
        password_edit = (EditText) findViewById(R.id.password);
        sex_group = (RadioGroup) findViewById(R.id.sex);

        // 添加监听事件
        sex_group.setOnCheckedChangeListener(this);
        nan_but = (RadioButton) findViewById(R.id.nan);
        read = (CheckBox) findViewById(R.id.read_book);
        play = (CheckBox) findViewById(R.id.play_ball);
        music = (CheckBox) findViewById(R.id.music);
        register = (Button) findViewById(R.id.register);

        // 添加监听事件
        register.setOnClickListener(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        final String[] city = new String[]{"北京", "上海", "武汉", "南京", "南昌", "武汉", "成都", "广州", "深圳"};
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_1,city);
        spinner.setAdapter(adapter);

        // 城市下拉列表添加监听事件
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                city_str = city[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.register:
                // 获取手机号和密码
                phone_str = phone_edit.getText().toString();
                password_str = password_edit.getText().toString();
                // 获取兴趣爱好
                hobby_str = ""; // 清除上一次已选中的选项
                if (read.isChecked()) {
                    hobby_str += read.getText().toString();
                }if (play.isChecked()) {
                    hobby_str += play.getText().toString();
                }if (music.isChecked()) {
                    hobby_str += music.getText().toString();
                }
                    Intent intent = new Intent(this, Second_Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("phone", phone_str);
                    bundle.putString("password", password_str);
                    bundle.putString("sex", sex_str);
                    bundle.putString("hobby", hobby_str);
                    bundle.putString("city", city_str);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
        }
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i){
        // 根据用户选择来改变sex_str的值
        sex_str=i==R.id.nan?"男性":"女性";
    }
}