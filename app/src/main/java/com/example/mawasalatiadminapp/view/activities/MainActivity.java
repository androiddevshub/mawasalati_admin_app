package com.example.mawasalatiadminapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mawasalatiadminapp.R;
import com.example.mawasalatiadminapp.utils.AppUtils;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private HashMap<String, String> userDetails;
    private AppUtils appUtils;
    private TextView userName;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        appUtils = new AppUtils(getApplicationContext());
        userDetails = appUtils.getUserDetails();

        userName = findViewById(R.id.hello_message);
        btnLogout = findViewById(R.id.btn_logout);

        String name = userDetails.get(AppUtils.KEY_USER_NAME);

        userName.setText("Hello, "+name);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appUtils.logoutUser();
            }
        });

    }
}