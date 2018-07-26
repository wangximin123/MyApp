package com.example.administrator.myapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    CheckBox checkBox;
    EditText edit_phone,edit_noteCode,edit_pass,edit_oncepass;
    Button btn_noteCode,btn_reigster;
    String phone,noteCode,pass,oncepass;
    boolean isSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
        setListener();
        String html="我已阅读<font color='#0c9c35'>GlocalMe用户协议</font>";
        checkBox.setText(Html.fromHtml(html));

    }
    private void findViews(){
        edit_phone=findViewById(R.id.Edit_phone);
        edit_noteCode=findViewById(R.id.Edit_noteCode);
        edit_pass=findViewById(R.id.Edit_pass);
        edit_oncepass=findViewById(R.id.Edit_oncepass);
        btn_noteCode=findViewById(R.id.Btn_noteCode);
        btn_reigster=findViewById(R.id.Btn_register);
        checkBox=findViewById(R.id.checkbox);
    }
    private void setListener(){
        btn_noteCode.setOnClickListener(this);
        btn_reigster.getText();
        btn_reigster.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==btn_noteCode){
            noteCode="";
            for (int i=0;i<4;i++){
                String s=String.valueOf((int)(Math.random()*10));
                noteCode+=s;
            }
            edit_noteCode.setText(noteCode);
        }else if (view==btn_reigster){
            phone=edit_phone.getText().toString().trim();
            noteCode=edit_noteCode.getText().toString().trim();
            pass=edit_pass.getText().toString().trim();
            oncepass=edit_oncepass.getText().toString().trim();
            isSelected=checkBox.isChecked();
            if (phone.equals("")){
                Toast.makeText(this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                return;
            }else if (noteCode.equals("")){
                Toast.makeText(this,"请获取验证码",Toast.LENGTH_SHORT).show();
                return;
            }else if (pass.equals("")){
                Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
                return;
            }else if (oncepass.equals("")){
                Toast.makeText(this,"确认密码不能为空",Toast.LENGTH_SHORT).show();
                return;
            }else if (!pass.equals(oncepass)){
                Toast.makeText(this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                return;
            }else if (!isSelected){
                Log.d("result",isSelected+"");
                Toast.makeText(this,"请阅读“GlocalMa协议”",Toast.LENGTH_SHORT).show();
                return;
            }

        }

    }
}
