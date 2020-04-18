package com.example.medicalapp.fragments;

public class Users {
    String uid;
    String Name;

    public Users(String uid, String Name){
        this.uid = uid;
        this.Name = Name;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return Name;
    }


}
