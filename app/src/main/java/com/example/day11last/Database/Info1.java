package com.example.day11last.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Info1 implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "Number")
    private String number;

    @ColumnInfo(name = "Location")
    private String location;

    @ColumnInfo(name = "DonationDay")
    private String donationDay;

    @ColumnInfo(name = "AbleToDonateAgain")
    private String ableToDonateAgain;

    @ColumnInfo(name = "BloodType")
    private String bloodType;

    @ColumnInfo(name = "CheckBox")
    private String point;

    @ColumnInfo(name = "HealthIssue")
    private String healthIssue;

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDonationDay() {
        return donationDay;
    }

    public void setDonationDay(String donationDay) {
        this.donationDay = donationDay;
    }

    public String getAbleToDonateAgain() {
        return ableToDonateAgain;
    }

    public void setAbleToDonateAgain(String ableToDonateAgain) {
        this.ableToDonateAgain = ableToDonateAgain;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getHealthIssue() {
        return healthIssue;
    }

    public void setHealthIssue(String healthIssue) {
        this.healthIssue = healthIssue;
    }
}
