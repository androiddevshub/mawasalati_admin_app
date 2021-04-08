package com.example.mawasalatiadminapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mawasalatiadminapp.R;
import com.example.mawasalatiadminapp.model.User;
import com.example.mawasalatiadminapp.model.responsebean.UserResponse;
import com.example.mawasalatiadminapp.utils.ApiClient;
import com.example.mawasalatiadminapp.utils.AppUtils;
import com.example.mawasalatiadminapp.utils.NetworkAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText et_email, et_password;
    private String email, password;
    private AppUtils appUtils;
    private TextView txt_register_here;
    private Button btn_login;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        appUtils = new AppUtils(getApplicationContext());
        progressDialog = new ProgressDialog(LoginActivity.this);
        et_email = findViewById(R.id.et_login_email);
        et_password = findViewById(R.id.et_login_password);
        txt_register_here = findViewById(R.id.txt_register_here);
        btn_login = findViewById(R.id.btn_login);


        txt_register_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = et_email.getText().toString();
                password = et_password.getText().toString();

                if(email.isEmpty()){
                  showToast("Please enter email");
                }else if(password.isEmpty()){
                    showToast("Please enter password");
                }
                else{
                    userLoginFun();
                }

            }
        });

    }

    private void userLoginFun(){

        progressDialog.setMessage("Logging In");
        progressDialog.show();

        final NetworkAPI networkAPI = ApiClient.getClient().create(NetworkAPI.class);
        Call<UserResponse> userLoginResponseCall = networkAPI.userLoginApi(new User(email, password));

        userLoginResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    showToast(response.body().getMessage());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    appUtils.setLoggedIn(true,
                            String.valueOf(response.body().getUser().getUser_id()),
                            response.body().getUser().getUser_name(),
                            response.body().getUser().getUser_email(),
                            response.body().getUser().getUser_phone(),
                            response.body().getUser().getToken(),
                            String.valueOf(response.body().getUser().isUser_admin()));

                }else{
                    progressDialog.dismiss();
                    showToast("Something went wrong");
                }
            }



            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.v("UserLogin", t.getLocalizedMessage());
            }
        });


    }

    public void showToast(String msg){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = layout.findViewById(R.id.text);
        text.setText(msg);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}