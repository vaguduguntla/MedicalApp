package com.example.medicalapp.fragments;

public class MedicalRecord {
    String RID;
    String Type;
    String Name;

    MedicalRecord(String RID, String Type, String Name ){
        this.RID = RID;
        this.Type = Type;
        this.Name =  Name;
    }


    public String getRID() {
        return RID;
    }

    public void setRID(String RID) {
        this.RID = RID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    String Date;
}
