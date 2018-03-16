package com.citywalker.lbstest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LocationClient client;
    TextView positionText;
    private MapView mapView;
    private BaiduMap baiduMap;
    private boolean isFirstLocate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        client = new LocationClient(getApplicationContext());
        client.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        mapView = findViewById(R.id.bmapView);
        positionText = findViewById(R.id.position_text_view);
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);

        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        } else {
            requestLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        client.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            StringBuilder sb = new StringBuilder();
            sb.append("维度：" + bdLocation.getLatitude()).append("\n");
            sb.append("经度：" + bdLocation.getLongitude()).append("\n");
            sb.append("国家：" + bdLocation.getCountry()).append("\n");
            sb.append("省：" + bdLocation.getProvince()).append("\n");
            sb.append("市：" + bdLocation.getCity()).append("\n");
            sb.append("区：" + bdLocation.getDistrict()).append("\n");
            sb.append("街道：" + bdLocation.getStreet()).append("\n");

            sb.append("定位方式：");
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                sb.append("GPS");
            } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                sb.append("网络");
            }
            positionText.setText(sb.toString());

            /*if (bdLocation.getLocType() == BDLocation.TypeGpsLocation || bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                Log.d("LL", "lan:" + bdLocation.getLatitude() + "long:" + bdLocation.getLongitude());
                navigateTo(bdLocation);
            }*/
            navigateTo(bdLocation);
        }
    }

    private void requestLocation() {
        initLocation();
        client.start();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        //option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        client.setLocOption(option);
    }

    private void navigateTo(BDLocation location) {
        if (isFirstLocate) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate statusUpdate = MapStatusUpdateFactory.newLatLng(latLng);
            baiduMap.animateMapStatus(statusUpdate);
            statusUpdate = MapStatusUpdateFactory.zoomTo(18f);
            baiduMap.animateMapStatus(statusUpdate);
            isFirstLocate = false;
        }

        MyLocationData.Builder builder = new MyLocationData.Builder();
        builder.latitude(location.getLatitude());
        builder.longitude(location.getLongitude());
        MyLocationData data = builder.build();
        baiduMap.setMyLocationData(data);
    }
}
