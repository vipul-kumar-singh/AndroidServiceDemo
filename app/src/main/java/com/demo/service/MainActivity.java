package com.demo.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button startServiceBtn;
    private Button stopServiceBtn;
    private Button bindServiceBtn;
    private Button unbindServiceBtn;
    private Button getRandomNumberBtn;

    private TextView textViewThreadCount;

    private MyService myService;

    private boolean isServiceBound;

    private ServiceConnection serviceConnection;

    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        Log.i(getString(R.string.service_demo), "MainActivity, thread id: " + Thread.currentThread().getId());

        startServiceBtn = findViewById(R.id.serviceStartBtn);
        stopServiceBtn = findViewById(R.id.serviceStopBtn);
        bindServiceBtn= findViewById(R.id.serviceBindBtn);
        unbindServiceBtn = findViewById(R.id.serviceUnbindBtn);
        getRandomNumberBtn= findViewById(R.id.getRandomNumberBtn);

        textViewThreadCount = findViewById(R.id.randomNumberText);

        serviceIntent = new Intent(getApplicationContext(), MyService.class);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.serviceStartBtn:
                Log.i("Button Pressed", "Start Button Pressed");
                startService(serviceIntent);
                break;
            case R.id.serviceStopBtn:
                Log.i("Button Pressed", "Stop Button Pressed");
                stopService(serviceIntent);
                break;
            case R.id.serviceBindBtn:
                bindService();
                break;
            case R.id.serviceUnbindBtn:
                unbindService();
                break;
            case R.id.getRandomNumberBtn:
                setRandomNumber();
                break;
        }
    }

    private void bindService() {
        if (serviceConnection==null){
            serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    MyService.MyServiceBinder myServiceBinder = (MyService.MyServiceBinder) iBinder;
                    myService = myServiceBinder.getService();
                    isServiceBound = true;
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    isServiceBound = false;
                }
            };
        }

        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);//BIND_AUTO_CREATE : it'll create the service if not created at the time of binding
    }

    private void unbindService() {
        if (isServiceBound){
            unbindService(serviceConnection);
            isServiceBound = false;
        }
    }

    private void setRandomNumber() {
        if (isServiceBound){
            textViewThreadCount.setText("Random no. : "+ myService.getRandomNumber());
        }else {
            textViewThreadCount.setText("Service not Bound");
        }
    }
}
