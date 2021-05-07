package com.studypreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button register,show;
    private EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    // 组件初始化
    private void init() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.buttonRegister);
        show = (Button) findViewById(R.id.showRegisterInfo);

        // 按钮添加监听
        register.setOnClickListener(this);
        show.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonRegister:
                String name_str = username.getText().toString();
                String password_str = password.getText().toString();
                boolean flag = save_userMes(MainActivity.this,name_str,password_str);
                if (flag){
                    Toast.makeText(MainActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"保存失败",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.showRegisterInfo:
                Map<String , String> user = getUserMes(MainActivity.this);

                if (user != null){
                    String name = user.get("username");
                    String password = user.get("password");
                    Toast.makeText(MainActivity.this,"用户名是"+name+"\n"+"密码是"+password,Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
    }

    // 保存用户信息
    private boolean save_userMes(Context context, String username, String password){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_mes", MODE_PRIVATE); // 创建一个SharedPreferences对象
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username); // 前面是key，后面是value
        editor.putString("password", password);
        editor.commit();
        return true;
    }

    // 读取用户信息
    private Map<String,String> getUserMes(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_mes",MODE_PRIVATE);

        String username = sharedPreferences.getString("username",null); //第2个参数表示默认值
        String password = sharedPreferences.getString("password",null);

        Map<String,String> user = new HashMap<String,String>();
        user.put("username",username);
        user.put("password",password);
        return user;
    }
}