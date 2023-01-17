package com.example.renter_management;

public class Meter {
    private int id;
    private float current_reading;
    private float cost;
    private String reading_date;
    private int flat_id;

    public String getReading_date() {
        return reading_date;
    }

    public void setReading_date(String reading_date) {
        this.reading_date = reading_date;
    }

    public Meter(int id, float current_reading, float cost, String reading_date, int flat_id) {
        this.id = id;
        this.current_reading = current_reading;
        this.cost = cost;
        this.reading_date = reading_date;
        this.flat_id = flat_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCurrent_reading() {
        return current_reading;
    }

    public void setCurrent_reading(float current_reading) {
        this.current_reading = current_reading;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getFlat_id() {
        return flat_id;
    }

    public void setFlat_id(int flat_id) {
        this.flat_id = flat_id;
    }
}
