package com.example.masha.androidapplication.Dz5;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class WifiService extends Service {

    private final IBinder binder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void switchOnWIFI(){
        WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            wifi.setWifiEnabled(true);
    }
    public void switchOffWIFI(){
        WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(false);
    }


    public class LocalBinder extends Binder {
        WifiService getService() {
            return WifiService.this;
        }
    }
}
