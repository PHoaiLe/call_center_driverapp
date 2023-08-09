package com.example.call_center_driver_app.components;

import java.io.Serializable;
import java.util.List;

public class MyDriver extends User implements Serializable
{
    protected float rating;
    protected List<String> e_wallet;


    public float getRating() {
        return rating;
    }

    public List<String> getE_wallet() {
        return e_wallet;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setE_wallet(List<String> e_wallet) {
        this.e_wallet = e_wallet;
    }

    @Override
    public String toString() {
        return "MyDriver{" +
                "rating=" + rating +
                ", e_wallet=" + e_wallet +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", dob=" + dob +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
