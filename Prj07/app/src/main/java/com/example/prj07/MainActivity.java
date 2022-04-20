package com.example.prj07;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
    }
    class NetworkChangeReceiver extends BroadcastReceiver {

        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectionManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
            boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
            boolean isWiFi = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
            if(isConnected==true){
                if(isWiFi==true){
                    Toast.makeText(context, "已连接上WIFI数据",Toast.LENGTH_SHORT).show();
                    Log.i("TAG","已连接上WIFI数据");
                }else{
                    Toast.makeText(context, "已连接上移动数据",Toast.LENGTH_SHORT).show();
                    Log.i("TAG","已连接上移动数据");
                }
            }else{
                Toast.makeText(context, "当前网络不可用",Toast.LENGTH_SHORT).show();
                Log.i("TAG","当前网络不可用");
            }

        }
    }
}