package com.example.renter_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MeterReadingInfo extends AppCompatActivity {
    TextView Nickname,meterReading,date,cost;
    MyDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_reading_info);
        int meterID = getIntent().getIntExtra("id",0);
        db = MyDatabase.getInstance(this);
        Meter meter = db.getOneReading(meterID);
        int flatID = meter.getFlat_id();
        Flat flat = db.getOneFlat(flatID);
        String nickName = flat.getNickName();
        Toast.makeText(this, "Reading" + meter.getCurrent_reading(), Toast.LENGTH_SHORT).show();
        Nickname = findViewById(R.id.set_MeterFlatNickname);
        meterReading = findViewById(R.id.set_MeterReading);
        date = findViewById(R.id.set_MeterReadingDate);
        cost  = findViewById(R.id.set_MeterElectriccost);

        Nickname.setText(nickName);
        meterReading.setText(Float.toString(meter.getCurrent_reading()));
        date.setText(meter.getReading_date());
        cost.setText(Float.toString(meter.getCost()));


    }
}