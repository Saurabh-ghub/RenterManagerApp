package com.example.renter_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Flat_MainPage extends AppCompatActivity {
    TextView flat_id_heading;
    TextView flatNickName,flatRent,flatElectricRate,flat_num_rooms,flat_advance,flat_joindate,flat_intialReading;
    CardView renter_details,payment_details,meter_details, month_wise_payment;
    MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_main_page);
        renter_details = findViewById(R.id.Renters);
        payment_details = findViewById(R.id.Payment_history_card);
        meter_details = findViewById(R.id.meter_card);
        flatNickName = findViewById(R.id.insert_fnickname);
         flatRent = findViewById(R.id.insert_frent);
          flatElectricRate = findViewById(R.id.insert_felectricrate);
           flat_num_rooms = findViewById(R.id.insert_fno_rooms);
           flat_advance = findViewById(R.id.insert_fadvance);
           flat_joindate = findViewById(R.id.insert_fjoinDate);
           flat_intialReading = findViewById(R.id.insert_finitialMeterRead);
         month_wise_payment = findViewById(R.id.MonthlyCard);


//        getflatdetails_btn = findViewById(R.id.flat_details_btn);
        db = MyDatabase.getInstance(this);
        int id = getIntent().getIntExtra("id",0);
//        Toast.makeText(this, Integer.toString(id), Toast.LENGTH_SHORT).show();
        flat_id_heading = findViewById(R.id.flatid_text);
        flat_id_heading.setText("Flat Id: " + Integer.toString(id));
        String nickNam = getIntent().getStringExtra("nickname");
        int rent = getIntent().getIntExtra("rent",0);
        float electricRate = getIntent().getFloatExtra("electricrate",0);
        int advnce = getIntent().getIntExtra("advance",0);
        String joindate = getIntent().getStringExtra("joindate");
        int numrooms = getIntent().getIntExtra("numrooms",0);
        float initialmeterReading = getIntent().getFloatExtra("initialmeterReading",0);
        flatNickName.setText(nickNam);
        flatRent.setText(Integer.toString(rent));
        flatElectricRate.setText(Float.toString(electricRate));
        flat_num_rooms.setText(Integer.toString(numrooms));
        flat_advance.setText(Integer.toString(advnce));
        flat_joindate.setText(joindate);
        flat_intialReading.setText(Float.toString(initialmeterReading));

        //FOR MONTH-WISE PAYMENT CARD CLICKED
        month_wise_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Flat_MainPage.this,MonthWisePaymentMainPage.class);
                intent.putExtra("flatID",id);
                startActivity(intent);
            }
        });

        //FOR RENTER DETAILS CARD CLICKED
        renter_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Flat_MainPage.this,RenterMainPage.class);
                intent.putExtra("flatID",id);
                startActivity(intent);
            }
        });

        payment_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Flat_MainPage.this,PaymentHistory.class);
                intent.putExtra("flatID",id);
                startActivity(intent);
            }
        });

        meter_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Flat_MainPage.this,MeterMainPage.class);
                intent.putExtra("flatID",id);
                startActivity(intent);
            }
        });


    }
}