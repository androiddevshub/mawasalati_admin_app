package com.example.mawasalatiadminapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mawasalatiadminapp.R;
import com.example.mawasalatiadminapp.utils.AppUtils;

public class SplashActivity extends AppCompatActivity {

    private AppUtils appUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appUtils = new AppUtils(getApplicationContext());

        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    if (appUtils.loggedIn()){

                        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();

                    }else {
                        Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();

    }
}