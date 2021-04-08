package com.example.mawasalatiadminapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private String user_id;

    @SerializedName("name")
    @Expose
    private String user_name;

    @SerializedName("email")
    @Expose
    private String user_email;

    @SerializedName("phone")
    @Expose
    private String user_phone;

    @SerializedName("address")
    @Expose
    private String user_address;

    @SerializedName("password")
    @Expose
    private String user_password;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("admin")
    @Expose
    private String user_admin;

    public User() {
    }


    public User(String user_email, String user_password) {
        this.user_email = user_email;
        this.user_password = user_password;
    }

    public User(String user_name, String user_email, String user_phone, String user_address, String user_password) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_phone = user_phone;
        this.user_address = user_address;
        this.user_password = user_password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String isUser_admin() {
        return user_admin;
    }

    public void setUser_admin(String user_admin) {
        this.user_admin = user_admin;
    }

}
