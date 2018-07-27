package com.example.administrator.myapp;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LongDef;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.example.administrator.myapp.util.MusicUtil;

import java.io.File;


public class CarActivity extends BasicActivity implements View.OnClickListener {
    MapView mMapView;
    Bundle bundle;
    AMap aMap;
    long up_time;
    Button btn_up,btn_down;
    boolean isCar=false;
    int money=0;
    TextView car_price,car_time;
    MyLocationStyle myLocationStyle;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                long nowTime=System.currentTimeMillis();
                int time=(int)((nowTime-up_time)/1000);
                String textTime=null,textPrice=null;
                int hour=(time/3600)%60;
                int minute=((time-time/3600)/60)%60;
                int second=(time-(time/3600)-(time-time/3600)/60)%60;
                textTime=(String.valueOf(hour).length()>1? ""+hour:"0"+hour)+":"+ (String.valueOf(minute).length()>1? ""+minute:"0"+minute) +":"+(String.valueOf(second).length()>1? ""+second:"0"+second);
                car_time.setText(textTime);
                int money=5+(time>10? (time-10)*3:0);
                car_price.setText(money+"元");

                Log.d("result","haha"+textTime+"--"+String.valueOf(second).length());

                handler.sendEmptyMessageDelayed(1,1000);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        initMusic();


        finViews();
        initTime();
        bundle=savedInstanceState;
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this,new String[]{
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                    Manifest.permission.READ_PHONE_STATE,
//                    Manifest.permission.ACCESS_COARSE_LOCATION},1);
//        }else {
            //获取地图控件引用
            mMapView = (MapView) findViewById(R.id.mapView);
            //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
            mMapView.onCreate(savedInstanceState);
            aMap=mMapView.getMap();
            MyLocationStyle myLocationStyle;
            myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
            myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
            aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
            aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
//        }


    }

    private void initMusic() {
        File file=new File(Environment.getExternalStorageDirectory(),"MyApp/musicCar.mp3");
        MusicUtil.listenerMusic(file);
    }
    private void initTime(){
        SharedPreferences sharedPreferences=getSharedPreferences("CarTime",MODE_PRIVATE);
        up_time=sharedPreferences.getLong("up_time",up_time);
        isCar=sharedPreferences.getBoolean("isCar",isCar);
        if (isCar){
            handler.sendEmptyMessage(1);
        }
    }
    private void saveTime(){
        SharedPreferences.Editor editor=getSharedPreferences("CarTime",MODE_PRIVATE).edit();
        editor.putLong("up_time",up_time);
        editor.putBoolean("isCar",isCar);
        editor.apply();
    }
    private void finViews() {
        mMapView=findViewById(R.id.mapView);
        btn_up=findViewById(R.id.Btn_up);
        btn_down=findViewById(R.id.Btn_down);
        car_price=findViewById(R.id.Car_price);
        car_time=findViewById(R.id.Car_time);

        btn_up.setOnClickListener(this);
        btn_down.setOnClickListener(this);
    }

    private void initMap(final Bundle savedInstanceState){
                //获取地图控件引用
                mMapView = (MapView) findViewById(R.id.mapView);
                //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
                mMapView.onCreate(bundle);
        aMap=mMapView.getMap();

        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        //myLocationStyle.showMyLocation(false);
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style

        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW) ;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMap(bundle);
                } else {

                }
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();

    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View view) {
        if(view==btn_up){
            if (!isCar) {
                up_time = System.currentTimeMillis();
                isCar = true;
                saveTime();
                handler.sendEmptyMessage(1);
            }
        }else if (view==btn_down){
            if (isCar){
                isCar=false;
                handler.removeMessages(1);
                up_time=0;
                saveTime();
                Log.d("result","time:"+(System.currentTimeMillis()-up_time));
            }

        }
    }
}
