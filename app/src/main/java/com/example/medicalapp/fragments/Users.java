package com.example.medicalapp.fragments;

public class Users {
    String pid;
    String Name;

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


}
