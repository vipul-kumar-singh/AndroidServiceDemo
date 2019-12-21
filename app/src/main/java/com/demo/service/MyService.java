package com.demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {

    //gets executed whenever you start a a service
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(getString(R.string.service_demo), "Service Started, thread id: " + Thread.currentThread().getId());

//        //to automatically destroy service once the task has been done
//        stopSelf();  // calls onDestroy method

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(getString(R.string.service_demo), "Service Destroyed");
    }


}
