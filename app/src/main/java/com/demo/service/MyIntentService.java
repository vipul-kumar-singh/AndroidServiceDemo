package com.demo.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Random;

public class MyIntentService extends IntentService {

    private int randomNumber;
    private boolean isRandomGeneratorOn;

    private final int MIN = 0;
    private final int MAX = 100;

    public MyIntentService() {
        super(MyIntentService.class.getSimpleName());
    }

    class MyServiceBinder extends Binder{
        public MyIntentService getService(){
            return MyIntentService.this;
        }
    }

    private IBinder binder = new MyServiceBinder();

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(getString(R.string.service_demo), "Service Started, thread id: " + Thread.currentThread().getId());
        isRandomGeneratorOn = true;
        startRandomGenerator();
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
