package com.demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Random;

public class MyService extends Service {

    private int randomNumber;
    private boolean isRandomGeneratorOn;

    private final int MIN = 0;
    private final int MAX = 100;

    class MyServiceBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }

    private IBinder binder = new MyServiceBinder();

    //gets executed whenever you start a a service
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(getString(R.string.service_demo), "Service Started, thread id: " + Thread.currentThread().getId());
        isRandomGeneratorOn = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                startRandomGenerator();
            }
        }).start();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(getString(R.string.service_demo), "In onBind");
        return binder;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(getString(R.string.service_demo), "In onRebind");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(getString(R.string.service_demo), "In onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRandomGenerator();
        Log.i(getString(R.string.service_demo), "Service Destroyed");
    }

    private void startRandomGenerator() {
        while (isRandomGeneratorOn) {
            try {
                Thread.sleep(1000);
                if (isRandomGeneratorOn) {
                    randomNumber = new Random().nextInt(MAX) + MIN;
                    Log.i(getString(R.string.service_demo), "Thread id: " + Thread.currentThread().getId() + ", Random Number: " + randomNumber);
                }
            } catch (InterruptedException e) {
                Log.e(getString(R.string.service_demo), "Thread Interrupted");
            }
        }
    }

    private void stopRandomGenerator() {
        isRandomGeneratorOn = false;
    }

    public int getRandomNumber() {
        return randomNumber;
    }
}
