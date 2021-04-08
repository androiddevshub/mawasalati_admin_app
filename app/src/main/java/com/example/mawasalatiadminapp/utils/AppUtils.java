package com.example.mawasalatiadminapp.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.mawasalatiadminapp.view.activities.LoginActivity;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtils {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context ctx;
    // Email address (make variable public to access from outside)

    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_NAME = "name";
    public static final String KEY_USER_EMAIL = "email";
    public static final String KEY_USER_PHONE = "phone";
    public static final String KEY_USER_TOKEN = "user_token";
    public static final String KEY_USER_ADMIN = "user_admin";

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public AppUtils(Context ctx){
        this.ctx = ctx;
        pref = ctx.getSharedPreferences("mawasalati", Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setLoggedIn(boolean loggedIn, String userId ,String name, String email, String phone, String token, String admin){
        editor.putBoolean("loggedInMode",loggedIn);
        // Storing email in pref
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_USER_NAME, name);
        editor.putString(KEY_USER_PHONE, phone);
        editor.putString(KEY_USER_TOKEN, token);
        editor.putString(KEY_USER_ID, userId);
        editor.putString(KEY_USER_ADMIN, admin);
        editor.commit();
    }

    public boolean loggedIn(){
        return pref.getBoolean("loggedInMode", false);
    }

    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user email id
        user.put(KEY_USER_EMAIL, pref.getString(KEY_USER_EMAIL, null));
        user.put(KEY_USER_NAME, pref.getString(KEY_USER_NAME, null));
        user.put(KEY_USER_PHONE, pref.getString(KEY_USER_PHONE, null));
        user.put(KEY_USER_TOKEN, pref.getString(KEY_USER_TOKEN, null));
        user.put(KEY_USER_ID, pref.getString(KEY_USER_ID, null));
        user.put(KEY_USER_ADMIN, pref.getString(KEY_USER_ADMIN, null));

        // return user
        return user;
    }

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(ctx, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        ctx.startActivity(i);
    }

    public void showToast(String msg){
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }


}
