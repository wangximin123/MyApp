package com.example.administrator.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapp.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    TextView text_forget;
    EditText edit_user,edit_pass;
    Button btn_login,btn_register;
    String username,password;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                Log.d("result","haha");
                SharedPreferences.Editor editor= getSharedPreferences("beforeCount",MODE_PRIVATE).edit();
                editor.putString("user",username);
                editor.putString("pass",password);
                editor.apply();
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        init();
        text_forget.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );//TextView加下划线


    }
    private void init(){
        SharedPreferences sharedPreferences=getSharedPreferences("beforeCount",MODE_PRIVATE);
        username=sharedPreferences.getString("user","");
        password=sharedPreferences.getString("pass","");
        edit_user.setText(username);
        edit_pass.setText(password);
    }
    private void checkUser(final String user, final String pass){
        new Thread(new Runnable() {
            @Override
            public void run() {

                String s= HttpUtil.sendRequest("http://192.168.120.2:8080/account.json");
                Log.d("result",s);
                try {
                    JSONArray array=new JSONArray(s);
                    for (int i=0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);
                        String Suser=object.getString("id");
                        String Spass=object.getString("pass");
                        Log.d("result",Suser+"--"+Spass);
                        if (Suser.equals(user)&&Spass.equals(pass)){
                                Message message=new Message();
                                message.what=1;
                                message.obj="true";
                                handler.sendMessage(message);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    private void findView(){
        text_forget=findViewById(R.id.Text_forget);
        edit_user=findViewById(R.id.Edit_user);
        edit_pass=findViewById(R.id.Edit_pass);
        btn_login=findViewById(R.id.Btn_login);
        btn_register=findViewById(R.id.Btn_register);

        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==btn_login){
            username=edit_user.getText().toString();
            password=edit_pass.getText().toString();
            checkUser(username,password);
        }else if (view==btn_register){
            Intent intent=new Intent(this,RegisterActivity.class);
            startActivity(intent);
        }
    }
}
