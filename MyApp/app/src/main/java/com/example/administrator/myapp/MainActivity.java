package com.example.administrator.myapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager vp_title;
    List<ImageView> list=new ArrayList<ImageView>();
    int cuttentItem=0;
    ShakeListener shakeListener;
    Button btn_dezhoujiaotong,btn_shenghuoyouhuiquan,btn_dachexinxi,btn_weather;
    LinearLayout location;
    boolean isAlert=false;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1 ){
                if (cuttentItem>2){
                    cuttentItem-=3;
                }
                vp_title.setCurrentItem(cuttentItem++);
                handler.sendEmptyMessageDelayed(1,2000);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initList();
        initViewpager();
        shakeListener=new ShakeListener(this);
        shakeListener.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake() {

                if (!isAlert) {
                    isAlert=true;
                    new AlertDialog.Builder(MainActivity.this).setTitle("确认退出登陆吗？")
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 点击“确认”后的操作
                                   Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                                   startActivity(intent);
                                   finish();
                                }
                            })
                            .setNegativeButton("返回", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 点击“返回”后的操作,这里不设置没有任何操作
                                    isAlert=false;
                                }
                            }).show();
                }
            }
        });

    }
    private void initViewpager(){
        MyAdapter1 myAdapter=new MyAdapter1();
        vp_title.setAdapter(myAdapter);
        handler.sendEmptyMessage(1);
    }
    private void findView(){
        vp_title=findViewById(R.id.Vp_title);
        btn_dezhoujiaotong=findViewById(R.id.Btn_dezhoujiaotong);
        btn_shenghuoyouhuiquan=findViewById(R.id.Btn_shenghuoyouhuiquan);
        btn_dachexinxi=findViewById(R.id.Btn_dachexinxi);
        btn_weather=findViewById(R.id.Btn_weather);
        location=findViewById(R.id.Lin_location);

        btn_dezhoujiaotong.setOnClickListener(this);
        btn_shenghuoyouhuiquan.setOnClickListener(this);
        btn_dachexinxi.setOnClickListener(this);
        btn_weather.setOnClickListener(this);
        location.setOnClickListener(this);
    }
    private void initList(){
        int[] imageResource={R.drawable.main_viewpager_one,R.drawable.main_viewpager_two,R.drawable.main_viewpager_three};
        for (int i=0;i<3;i++){
            ImageView imageView=new ImageView(this);
            imageView.setImageResource(imageResource[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            list.add(imageView);
        }
    }

    @Override
    public void onClick(View view) {
        if (view==btn_dezhoujiaotong){
            Intent intent=new Intent(this,NewsActicity.class);
            startActivity(intent);
        }else if (view==btn_shenghuoyouhuiquan){
            Intent intent=new Intent(this,DiscountsActivity.class);
            startActivity(intent);
        }else if (view==btn_dachexinxi){
            Intent intent=new Intent(this,CarActivity.class);
            startActivity(intent);
        }else if (view==btn_weather){
            Intent intent=new Intent(this,WeatherActivity.class);
            startActivity(intent);
        }else if (view==location){
            Intent intent=new Intent(this,MapActivity.class);
            startActivity(intent);
        }
    }

    class MyAdapter1 extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view==o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView=list.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            ImageView imageView=list.get(position);
            container.removeView(imageView);
        }
    }

}
