package com.example.renter_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PaymentInfo extends AppCompatActivity {
    TextView paidAmt,paidBy,paidDate,remarks,paidRoomAmt,paidElectricAmt;
    MyDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_info);
        db = MyDatabase.getInstance(this);
        int paymentId = getIntent().getIntExtra("PaymentId",-1);

        paidAmt = findViewById(R.id.set_paidAmt);
        paidBy = findViewById(R.id.set_paidBy);
        paidDate = findViewById(R.id.set_paidDate);
        remarks = findViewById(R.id.set_paidRemarks);
        paidRoomAmt = findViewById(R.id.set_paidRoomAmt);
        paidElectricAmt = findViewById(R.id.set_paidElectricAmt);

        Payment payment = db.getOnePayment(paymentId);
        paidAmt.setText(Integer.toString(payment.getPaid_amount()));
        paidBy.setText(payment.getPaid_by());
        paidDate.setText(payment.getPayment_date());
        remarks.setText(payment.getRemarks());
        paidRoomAmt.setText(Integer.toString(payment.getRoom_payment()));
        paidElectricAmt.setText(Integer.toString(payment.getMeter_payment()));



    }
}