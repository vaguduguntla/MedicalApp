package com.example.medicalapp;

public class Doctor {

    String Name;
    String Hours;
    String Location;
    String PhoneNumber;
    String Specialty;
    String did;



    public Doctor() {

    };

    public Doctor(String did, String Name){
        this.did = did;
        this.Name = Name;
    };

    public Doctor(String did, String Name, String Speciality){
        this.did = did;
        this.Name = Name;
        this.Specialty = Speciality;
    }

    public Doctor(String did, String Name, String Speciality, String Hours, String Location, String PhoneNumber) {
        this.did = did;
        this.Name = Name;
        this.Specialty = Speciality;
        this.Hours = Hours;
        this.Location = Location;
        this.PhoneNumber = PhoneNumber;
    }


    public String getSpecialty() {
        return Specialty;
    }

    public void setSpecialty(String specialty) {
        Specialty = specialty;
    }

    public String getDid(){
        return this.did;
    }

    public void setDid(String did){
        this.did = did;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getHours() {
        return Hours;
    }

    public void setHours(String hours) {
        Hours = hours;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }


}
