package com.example.medicalapp;

public class MedicalHistory {
    String hid;
    String issue;
    String startDate;
    String endDate;

    public MedicalHistory(String hid, String issue, String startDate, String endDate){
        this.hid = hid;
        this.issue = issue;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
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
