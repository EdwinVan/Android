package com.example.userlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Second_Activity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        String phone = bundle.getString("phone");
        String password = bundle.getString("password");
        String sex = bundle.getString("sex");
        String hobby = bundle.getString("hobby");
        String city = bundle.getString("city");

        TextView show_text = (TextView) findViewById(R.id.show_content);
    show_text.setText("手机号为: " + phone + "\n" + "密码为: " + password + "\n" + "性别为: " + sex +
                "\n" + "爱好是: " + hobby + "\n" + "城市是: " + city);
        }
}
