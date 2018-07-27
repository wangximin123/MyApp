package com.example.administrator.myapp;

import android.icu.util.LocaleData;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;
import com.example.administrator.myapp.util.HttpUtil;
import com.example.administrator.myapp.util.MusicUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherActivity extends BasicActivity {
    List<Weather> list=new ArrayList<Weather>();
    List<ImageView> listImage=new ArrayList<ImageView>();
    List<TextView> listText=new ArrayList<TextView>();
    LinearLayout lin_background;
    TextView tv1,tv2,tv3,tv4,tv5,tv_city;
    ImageView iv1,iv2,iv3,iv4,iv5;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                init();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initMusic();

        findViews();
        getDate();

//        f();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    private void initMusic() {
        File file=new File(Environment.getExternalStorageDirectory(),"MyApp/musicWeather.mp3");
        MusicUtil.listenerMusic(file);
    }

//    private void f() {
//        //检索参数为城市和天气类型，实况天气为WEATHER_TYPE_LIVE、天气预报为WEATHER_TYPE_FORECAST
//        mquery = new WeatherSearchQuery("北京", WeatherSearchQuery.WEATHER_TYPE_LIVE);
//        mweathersearch=new WeatherSearch(this);
//        mweathersearch.setOnWeatherSearchListener(this);
//        mweathersearch.setQuery(mquery);
//        mweathersearch.searchWeatherAsyn(); //异步搜索
//    }

    public void init(){
        // String wdata,wcity,wwendu,wforcast;
        Weather weather=list.get(0);
        String  wdata,wcity,wwendu,wforcast;
        wdata=weather.getWdata();
        wcity=weather.getWcity();
        wforcast=weather.getWforcast();
        if (wforcast.equals("晴")){
            lin_background.setBackgroundResource(R.drawable.qingtian);
        }else if (wforcast.equals("多云")){
            lin_background.setBackgroundResource(R.drawable.yintian);
        }
        int index=0;
        for (Weather weather1:list){
            setIcon(listImage.get(index),listText.get(index),weather1);
            index++;
        }
        tv_city.setText(wcity);


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void setIcon(ImageView imageView, TextView textView, Weather weather){
        if (weather.getWforcast().equals("晴")){
            imageView.setImageResource(R.drawable.a1);
        }else if (weather.getWforcast().equals("多云")){
            imageView.setImageResource(R.drawable.a2);
        }
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(weather.getWwendu());
        textView.setText(m.replaceAll("").trim()+"°");
    }

    private void findViews() {
        lin_background=findViewById(R.id.Lin_background);
        tv1=findViewById(R.id.Tv_one);
        tv2=findViewById(R.id.Tv_two);
        tv3=findViewById(R.id.Tv_three);
        tv4=findViewById(R.id.Tv_four);
        tv5=findViewById(R.id.Tv_five);
        tv_city=findViewById(R.id.Tv_city);
        listText.add(tv1);
        listText.add(tv2);
        listText.add(tv3);
        listText.add(tv4);
        listText.add(tv5);

        iv1=findViewById(R.id.Iv_one);
        iv2=findViewById(R.id.Iv_two);
        iv3=findViewById(R.id.Iv_three);
        iv4=findViewById(R.id.Iv_four);
        iv5=findViewById(R.id.Iv_five);
        listImage.add(iv1);
        listImage.add(iv2);
        listImage.add(iv3);
        listImage.add(iv4);
        listImage.add(iv5);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void getDate(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String s=HttpUtil.sendRequest("http://192.168.120.2:8080/a.json");
                Log.d("result",s);
                try {
                    JSONObject object=new JSONObject(s);
                    String data=object.getString("data");
                    Log.d("result","data:"+data);
                    JSONObject object1=new JSONObject(data);
                    String city=object1.getString("city");
                    Log.d("result","-----------"+city);
                    String forecast=object1.getString("forecast");
                    Log.d("result","forecast:"+forecast);
                    JSONArray jsonArray=new JSONArray(forecast);
                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject o=jsonArray.getJSONObject(i);
                        String sdata=o.getString("date");
                        String shight=o.getString("high");
                        String fengli=o.getString("fengli");
                        String low=o.getString("low");
                        String fengxiang=o.getString("fengxiang");
                        String type=o.getString("type");
                        Log.d("result---",sdata+"--"+shight+"--"+fengli+"--"+low+"--"+fengxiang+"--"+type);
                        final Weather weather=new Weather(sdata,city,shight,type);
                                // String wdata,wcity,wwendu,wforcast;
                        list.add(weather);
                        handler.sendEmptyMessage(1);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("result",e.getMessage());
                }
            }
        }).start();
    }
}
