package com.example.administrator.myapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActicity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    ViewPager viewPager;
    MyAdapter myAdapter;
    View selectPoint;
    List<ImageView> list=new ArrayList<ImageView>();
    LinearLayout paint_contain;
    Button wel_button;
    int max;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_acticity);
        paint_contain=findViewById(R.id.Point_contain);
        selectPoint=findViewById(R.id.selectPoint);
        wel_button=findViewById(R.id.wel_button);
        wel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WelcomeActicity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        initList();
        viewPager=findViewById(R.id.viewPager);
        myAdapter=new MyAdapter();
        viewPager.setAdapter(myAdapter);
        viewPager.setOnPageChangeListener(this);
    }
    void initList(){
        ImageView imageView1=new ImageView(this);
        imageView1.setImageResource(R.drawable.wel_one);
        imageView1.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView imageView2=new ImageView(this);
        imageView2.setImageResource(R.drawable.wel_two);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView imageView3=new ImageView(this);
        imageView3.setImageResource(R.drawable.wel_three);
        imageView3.setScaleType(ImageView.ScaleType.FIT_XY);

        list.add(imageView1);
        list.add(imageView2);
        list.add(imageView3);
        max=list.size();
        for (int i=0;i<3;i++){
            View point=new View(this);
            point.setBackgroundResource(R.drawable.sample_point_nomal);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(40,40);
            params.leftMargin=20;
            paint_contain.addView(point,params);
        }

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
        Log.d("result",i+"---"+v+"---"+i1);

    }

    @Override
    public void onPageSelected(int i) {
        RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) selectPoint.getLayoutParams();
        params.leftMargin=20+60*i-1;
        selectPoint.setLayoutParams(params);
        Log.d("result",params.leftMargin+"");
        Log.d("result",i+"");
        if (i==max-1){
            wel_button.setVisibility(View.VISIBLE);
        }else {
            wel_button.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    class MyAdapter extends PagerAdapter{

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
