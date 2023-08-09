package com.example.call_center_driver_app.components;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    protected String name;
    protected boolean gender; //female: true, male: false :)
    protected Date dob;
    protected String phoneNumber;
    protected String email;
    protected String avatar;

    public User()
    {
        name = "";
        gender = false;
        dob = new Date();
        email = "";
        avatar = "";
    }

    public String getName() {
        return name;
    }

    public boolean getGender()
    {
        return gender;
    }

    public Date getDob()
    {
        return dob;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public String getAvatar()
    {
        return avatar;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", dob=" + dob +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
