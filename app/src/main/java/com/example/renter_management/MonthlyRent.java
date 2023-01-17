package com.example.renter_management;

public class MonthlyRent {
    int id;
    String month;
    int recvAmount;
    int dueAmount;
     String paidDate;
    int flat_id;

    public MonthlyRent(int id, String month, int recvAmount, int dueAmount, String paidDate, int flat_id) {
        this.id = id;
        this.month = month;
        this.paidDate = paidDate;
        this.recvAmount = recvAmount;
        this.dueAmount = dueAmount;
        this.flat_id = flat_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }

    public int getRecvAmount() {
        return recvAmount;
    }

    public void setRecvAmount(int recvAmount) {
        this.recvAmount = recvAmount;
    }

    public int getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(int dueAmount) {
        this.dueAmount = dueAmount;
    }

    public int getFlat_id() {
        return flat_id;
    }

    public void setFlat_id(int flat_id) {
        this.flat_id = flat_id;
    }
}
