package com.example.perfectgoes;

import android.net.Uri;

public class Helper {

    private String fullname;
    private String username;
    private String password;
    private String email;
    private String phoneNo;
    private int lock;
    private Uri imageUri;

    public Helper(String fullname, String username, String password, String email, String phoneNo,int lock,Uri ImageUri) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
        this.lock =lock;
        this.imageUri=imageUri;
    }

    public int getLock() {
        return lock;
    }

    public void setLock(int lock) {
        this.lock = lock;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
