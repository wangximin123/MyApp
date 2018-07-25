package com.example.administrator.myapp;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text_forget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_forget=findViewById(R.id.Text_forget);
        text_forget.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
    }
}
