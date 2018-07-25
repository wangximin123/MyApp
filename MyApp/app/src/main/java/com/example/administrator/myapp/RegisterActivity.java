package com.example.administrator.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.CheckBox;

public class RegisterActivity extends AppCompatActivity {
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        checkBox=findViewById(R.id.checkbox);
        String html="我已阅读<font color='#0c9c35'>GlocalMe用户协议</font>";
        checkBox.setText(Html.fromHtml(html));
    }
}
