package com.example.medicalapp;

public class Medication {
    String mid;
    String pid;
    String Name;
    String startDate;
    String endDate;

    public Medication(String mid, String pid, String name, String startDate, String endDate) {
        this.mid = mid;
        this.pid = pid;
        this.Name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
