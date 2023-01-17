package com.example.renter_management;

import java.sql.Date;

public class Flat {
    private int flat_id;
    private String nickName;
    private int no_of_rooms;
    private String joining_date;
    private int flat_rent;
    private int advance;
    private float electricity_rate;
    private float intial_meter_reading;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public float getElectricity_rate() {
        return electricity_rate;
    }

    public void setElectricity_rate(float electricity_rate) {
        this.electricity_rate = electricity_rate;
    }

    public Flat(int flat_id, int no_of_rooms,String nickName, String joining_date, int advance, int flat_rent,float electricity_rate ,float intial_meter_reading) {
        this.flat_id = flat_id;
        this.nickName = nickName;
        this.no_of_rooms = no_of_rooms;
        this.joining_date = joining_date;
        this.flat_rent = flat_rent;
        this.advance = advance;
        this.electricity_rate=electricity_rate;

        this.intial_meter_reading = intial_meter_reading;
    }

    public int getFlat_id() {
        return flat_id;
    }

    public int getAdvance() {
        return advance;
    }

    public void setAdvance(int advance) {
        this.advance = advance;
    }

    public void setFlat_id(int flat_id) {
        this.flat_id = flat_id;
    }

    public int getNo_of_rooms() {
        return no_of_rooms;
    }

    public void setNo_of_rooms(int no_of_rooms) {
        this.no_of_rooms = no_of_rooms;
    }

    public String getJoining_date() {
        return joining_date;
    }

    public void setJoining_date(String joining_date) {
        this.joining_date = joining_date;
    }

    public int getFlat_rent() {
        return flat_rent;
    }

    public void setFlat_rent(int flat_rent) {
        this.flat_rent = flat_rent;
    }

    public float getIntial_meter_reading() {
        return intial_meter_reading;
    }

    public void setIntial_meter_reading(float intial_meter_reading) {
        this.intial_meter_reading = intial_meter_reading;
    }
}
