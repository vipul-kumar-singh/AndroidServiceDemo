package com.demo.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button startServiceBtn;
    private Button stopServiceBtn;

    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        Log.i(getString(R.string.service_demo), "MainActivity, thread id: " + Thread.currentThread().getId());

        startServiceBtn = findViewById(R.id.serviceStartBtn);
        stopServiceBtn = findViewById(R.id.serviceStopBtn);

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
        }
    }
}
