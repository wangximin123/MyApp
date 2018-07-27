package com.example.administrator.myapp;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
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
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.example.administrator.myapp.util.MusicUtil;

import java.io.File;
import java.util.List;


public class MapActivity extends BasicActivity implements View.OnClickListener {
    MapView mMapView;
    Bundle bundle;
    AMap aMap;
    MyLocationStyle myLocationStyle;
    Button huoqu,dingwei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initMusic();
        findViews();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.ACCESS_COARSE_LOCATION},1);
        }else {
            initMap(savedInstanceState);
        }

    }
    private void initMusic() {
        File file=new File(Environment.getExternalStorageDirectory(),"MyApp/musicMap.mp3");
        MusicUtil.listenerMusic(file);
    }

    private void findViews() {
        mMapView=findViewById(R.id.mapView);
        huoqu=findViewById(R.id.Btn_huoqu);
        dingwei=findViewById(R.id.Btn_dingwei);

        dingwei.setOnClickListener(this);
        huoqu.setOnClickListener(this);
    }


    private void initMap(final Bundle savedInstanceState){
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.mapView);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(bundle);
        aMap=mMapView.getMap();

        myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位方式
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
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
        if (view==dingwei){
            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位方式
            aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        }else if (view==huoqu){
            AMapLocationClient aMapLocationClient=new AMapLocationClient(this);
            AMapLocation aMapLocation=aMapLocationClient.getLastKnownLocation();
            String[] address=new String[4];
            address[0]=aMapLocation.getProvince();
            address[1]=aMapLocation.getCity();
            address[2]=aMapLocation.getDistrict();
            address[3]=aMapLocation.getStreet();
            String s="";
            for (int i=0;i<address.length;i++){
                s+=address[i];
            }
            Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
        }
    }




}
