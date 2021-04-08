package com.example.mawasalatiadminapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mawasalatiadminapp.R;
import com.example.mawasalatiadminapp.model.User;
import com.example.mawasalatiadminapp.model.responsebean.UserResponse;
import com.example.mawasalatiadminapp.utils.ApiClient;
import com.example.mawasalatiadminapp.utils.AppUtils;
import com.example.mawasalatiadminapp.utils.NetworkAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    private EditText et_name, et_email, et_phone, et_address, et_password;
    private String name, email, phone, address, password;
    private AppUtils appUtils;
    private TextView txt_login_here;
    private Button btn_register;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        appUtils = new AppUtils(getApplicationContext());
        progressDialog = new ProgressDialog(RegistrationActivity.this);
        et_name = findViewById(R.id.et_regis_name);
        et_email = findViewById(R.id.et_regis_email);
        et_phone = findViewById(R.id.et_regis_phone);
        et_address = findViewById(R.id.et_regis_address);
        et_password = findViewById(R.id.et_regis_password);
        txt_login_here = findViewById(R.id.txt_login_here);
        btn_register = findViewById(R.id.btn_register);

        txt_login_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = et_name.getText().toString();
                email = et_email.getText().toString();
                phone = et_phone.getText().toString();
                address = et_address.getText().toString();
                password = et_password.getText().toString();

                if (name.isEmpty()){
                    appUtils.showToast("Please enter name");
                }else if(email.isEmpty()){
                    appUtils.showToast("Please enter email");
                }else if(phone.isEmpty()){
                    appUtils.showToast("Please enter password");
                }else if(address.isEmpty()){
                    appUtils.showToast("Please enter password");
                }else if(password.isEmpty()){
                    appUtils.showToast("Please enter password");
                }
                else {
                    userRegisterFun();
                }
            }
        });


    }


    private void userRegisterFun() {

        progressDialog.setMessage("Signing up");
        progressDialog.show();

        final NetworkAPI networkAPI = ApiClient.getClient().create(NetworkAPI.class);
        Call<UserResponse> userRegisterResponseCall = networkAPI.userRegisterApi(new User(name, email, phone, address, password));

        userRegisterResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if (response.body().isStatus()){
                    progressDialog.dismiss();
                    appUtils.showToast(response.body().getMessage());
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.v("UserRegistration", t.getLocalizedMessage());

            }
        });

    }


}