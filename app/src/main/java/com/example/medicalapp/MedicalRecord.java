package com.example.medicalapp;

public class MedicalRecord {
    String RID;
    String Type;
    String Doctor;
    String Name;
    String Date;

    public MedicalRecord(String RID, String Type, String Name, String Date, String Doctor){
        this.RID = RID;
        this.Type = Type;
        this.Name =  Name;
        this.Date = Date;
        this.Doctor = Doctor;
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

    public String getDoctor() { return Doctor; }

    public void setDoctor(String doctor) { Doctor = doctor; }


}
