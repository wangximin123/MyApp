package com.example.administrator.myapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myapp.util.MusicUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DiscountsActivity extends BasicActivity {
    ListView listView;
    List<Discounts> list=new ArrayList<Discounts>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discounts);
        initMusic();
        findViews();
        initList();;
        DiscountsListViewAdapter discountsListViewAdapter=new DiscountsListViewAdapter(this,0,list);
        listView.setAdapter(discountsListViewAdapter);
    }
    private void initMusic() {
        File file=new File(Environment.getExternalStorageDirectory(),"MyApp/musicDiscounts.mp3");
        MusicUtil.listenerMusic(file);
    }
    void initList(){
        Discounts d1=new Discounts(8,Discounts.GET_IMMEDIATELY,"家政洗衣按摩美业芬","2016.03.02-2016.03.31有效");
        Discounts d2=new Discounts(50,Discounts.GET_SATISFY,"家政洗衣按摩美业芬","2016.03.02-2016.03.31有效");
        Discounts d3=new Discounts(20,Discounts.GET_SATISFY,"家政洗衣按摩美业芬","2016.03.02-2016.03.31有效");
        Discounts d4=new Discounts(15,Discounts.GET_SATISFY,"家政洗衣按摩美业芬","2016.03.02-2016.03.31有效");
        Discounts d5=new Discounts(6,Discounts.GET_SATISFY,"家政洗衣按摩美业芬","2016.03.02-2016.03.31有效");
        Discounts d6=new Discounts(100,Discounts.GET_SATISFY,"家政洗衣按摩美业芬","2016.03.02-2016.03.31有效");
        Discounts d7=new Discounts(85,Discounts.GET_SATISFY,"家政洗衣按摩美业芬","2016.03.02-2016.03.31有效");
        list.add(d1);
        list.add(d2);
        list.add(d3);
        list.add(d4);
        list.add(d5);
        list.add(d6);
        list.add(d6);
    }
    void findViews(){
        listView=findViewById(R.id.Listview_discounts);
    }
    class DiscountsListViewAdapter extends ArrayAdapter<Discounts>{
        List<Discounts> list;

        public DiscountsListViewAdapter(@NonNull Context context, int resource, @NonNull List<Discounts> objects) {
            super(context, resource, objects);
            list=objects;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view=null;
            MyHolder myHolder=null;
            if (convertView!=null){
                view=convertView;
                myHolder= (MyHolder) view.getTag();
            }else {
                view= LayoutInflater.from(getContext()).inflate(R.layout.discounts_listview_item,parent,false);
                myHolder=new MyHolder();
                myHolder.discounts_leftView=view.findViewById(R.id.discounts_leftView);
                myHolder.discounts_price=view.findViewById(R.id.discounts_price);
                myHolder.discounts_type=view.findViewById(R.id.discounts_type);
                myHolder.discounts_title=view.findViewById(R.id.discounts_title);
                myHolder.discounts_data=view.findViewById(R.id.discounts_data);
                myHolder.discounts_rightBtn=view.findViewById(R.id.discounts_rightBtn);
                view.setTag(myHolder);
            }
            Discounts discounts=list.get(position);
//            private int price,type;
//            private String title,data;
//            public static int GET_IMMEDIATELY=0;
//            public static int GET_SATISFY=1;
            int price=discounts.getPrice();
            int type=discounts.getType();
            String title=discounts.getTitle();
            String data=discounts.getData();
            if (type==Discounts.GET_IMMEDIATELY){
                myHolder.discounts_leftView.setBackgroundResource(R.drawable.discounts_listview_item_line_yello);
                myHolder.discounts_price.setText(Html.fromHtml("<font color='#f2bf07' fontsize='15'>¥</font><font color='#f2bf07' size='20'>"+price+"</font>"));
                //myHolder.discounts_price.setTextColor(getResources().getColor(R.color.discountsYellow));
                myHolder.discounts_rightBtn.setTextColor(getResources().getColor(R.color.discountsYellow));

            }else {
                myHolder.discounts_leftView.setBackgroundResource(R.drawable.discounts_listview_item_line_red);
                myHolder.discounts_price.setText(Html.fromHtml("<font color='#f21307' size='15'>¥</font><font color='#f21307' size='20'>"+price+"</font>"));

                myHolder.discounts_rightBtn.setTextColor(getResources().getColor(R.color.discountsRed));
            }
            myHolder.discounts_title.setText(title);
            myHolder.discounts_data.setText(data);

            return view;
        }
        class MyHolder{
            View discounts_leftView;
            TextView discounts_price,discounts_type,discounts_title,discounts_data;
            Button discounts_rightBtn;
        }
    }
}
