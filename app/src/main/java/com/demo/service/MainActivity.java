package com.demo.service;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button startServiceBtn;
    private Button stopServiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        startServiceBtn = findViewById(R.id.serviceStartBtn);
        stopServiceBtn = findViewById(R.id.serviceStopBtn);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.serviceStartBtn:
                Log.i("Button Pressed", "Start Button Pressed");
                break;
            case R.id.serviceStopBtn:
                Log.i("Button Pressed", "Stop Button Pressed");
                break;
        }
    }
}
