package com.example.masha.androidapplication.Dz5;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.masha.androidapplication.R;

import java.util.Objects;

public class Dz5Activity extends AppCompatActivity {
    private Switch WIFI;
    private Button changeWIFI;
    private WifiService wifiService;
    private boolean mBound;


    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mBound) {
                if (WIFI.isChecked()) {
                    wifiService.switchOffWIFI();
                } else {
                    wifiService.switchOnWIFI();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz5);
        WIFI = findViewById(R.id.WIFIchecker);
        WIFI.setClickable(false);
        changeWIFI = findViewById(R.id.buttonWIFIChange);
        changeWIFI.setOnClickListener(click);

    }

    private BroadcastReceiver checkingWIFI = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiStatus = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, ((WifiManager) Objects.requireNonNull(context.getApplicationContext().getSystemService(Context.WIFI_SERVICE))).getWifiState());
            if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
                switch (wifiStatus) {
                    case WifiManager.WIFI_STATE_DISABLED:
                        WIFI.setChecked(false);
                        break;
                    case WifiManager.WIFI_STATE_DISABLING:
                        WIFI.setChecked(false);
                        break;
                    case WifiManager.WIFI_STATE_ENABLED:
                        WIFI.setChecked(true);
                        break;
                    case WifiManager.WIFI_STATE_ENABLING:
                        WIFI.setChecked(true);
                        break;
                }
            }
        }
    };

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            WifiService.LocalBinder localBinder = (WifiService.LocalBinder) service;
            wifiService = localBinder.getService();
            changeWIFI.setOnClickListener(click);
            mBound = true;
            Log.e("111", "work");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };


    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(checkingWIFI, intentFilter);

        Intent intent = new Intent(this, WifiService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);

    }


    protected void onPause() {
        super.onPause();
        unregisterReceiver(checkingWIFI);
        if (mBound) {
            unbindService(serviceConnection);
            mBound = false;
        }
    }
}
