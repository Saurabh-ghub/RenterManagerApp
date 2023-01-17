package com.example.renter_management;

import java.util.Date;

public class Payment {
    private int id;
    private String paid_by;
    private int paid_amount;
    private int room_payment;
    private int meter_payment;
    private String payment_mode;
    String payment_date;
    String remarks;
    int flat_id;

    public Payment(int id, String paid_by, int paid_amount,int room_payment,int meter_payment, String payment_mode, String payment_date, String remarks, int flat_id) {
        this.id = id;
        this.paid_by = paid_by;
        this.paid_amount = paid_amount;
        this.room_payment = room_payment;
        this.meter_payment = meter_payment;
        this.payment_mode = payment_mode;
        this.payment_date = payment_date;
        this.remarks = remarks;
        this.flat_id = flat_id;
    }

    public int getRoom_payment() {
        return room_payment;
    }

    public void setRoom_payment(int room_payment) {
        this.room_payment = room_payment;
    }

    public int getMeter_payment() {
        return meter_payment;
    }

    public void setMeter_payment(int meter_payment) {
        this.meter_payment = meter_payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaid_by() {
        return paid_by;
    }

    public void setPaid_by(String paid_by) {
        this.paid_by = paid_by;
    }

    public int getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(int paid_amount) {
        this.paid_amount = paid_amount;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getFlat_id() {
        return flat_id;
    }

    public void setFlat_id(int flat_id) {
        this.flat_id = flat_id;
    }
}
