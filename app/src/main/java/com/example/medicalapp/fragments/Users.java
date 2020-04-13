package com.example.medicalapp.fragments;

import com.example.medicalapp.R;

public class Users {
    String pid;
    String Name;
    int image = R.drawable.ic_account_circle_black_24dp;

    public Users(String pid, String Name){
        this.pid = pid;
        this.Name = Name;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPid() {
        return pid;
    }

    public String getName() {
        return Name;
    }

    public int getImage() {
        return image;
    }

}
