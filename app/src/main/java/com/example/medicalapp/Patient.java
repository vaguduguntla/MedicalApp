package com.example.medicalapp;

public class Patient {

    private String name;
    private String PID;
    private String age;
    private String gender;

    public Patient(String PID, String name, String age, String gender){
        this.PID = PID;
        this.name = name;
        this.age =  age;
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getPID() {
        return PID;
    }
}
