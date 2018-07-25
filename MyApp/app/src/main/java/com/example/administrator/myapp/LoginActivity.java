package com.example.administrator.myapp;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    TextView text_forget;
    EditText edit_user,edit_pass;
    Button btn_login,btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        text_forget.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );//TextView加下划线

    }
    private void findView(){
        text_forget=findViewById(R.id.Text_forget);
        edit_user=findViewById(R.id.Edit_user);
        edit_pass=findViewById(R.id.Edit_pass);
        btn_login=findViewById(R.id.Btn_login);
        btn_login=findViewById(R.id.Btn_register);
    }
}
