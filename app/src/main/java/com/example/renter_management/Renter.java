package com.example.renter_management;

import android.media.Image;
import android.net.Uri;

import java.net.URI;
import java.util.Arrays;

public class Renter {
   private int renter_id;
   private String renter_name;
    private String phone;
   private String addres;
    private Uri photo;
    private int flat_id;

    public int getFlat_id() {
        return flat_id;
    }

    public void setFlat_id(int flat_id) {
        this.flat_id = flat_id;
    }

    public int getRenter_id() {
        return renter_id;
    }

    public void setRenter_id(int renter_id) {
        this.renter_id = renter_id;
    }

    public String getRenter_name() {
        return renter_name;
    }

    public void setRenter_name(String renter_name) {
        this.renter_name = renter_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }

    public Renter(int renter_id, String renter_name, String phone, String addres, Uri photo, int flat_id) {
        this.renter_id = renter_id;
        this.renter_name = renter_name;
        this.phone = phone;
        this.addres = addres;
        this.photo = photo;
        this.flat_id = flat_id;
    }

    @Override
    public String toString() {
        return "Renter{" +
                "renter_id=" + renter_id +
                ", renter_name='" + renter_name + '\'' +
                ", phone='" + phone + '\'' +
                ", addres='" + addres + '\'' +
                ", photo=" + photo +
                ", flat_id=" + flat_id +
                '}';
    }
}
