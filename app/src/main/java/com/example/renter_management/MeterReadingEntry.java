package com.example.renter_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MeterReadingEntry extends AppCompatActivity {
    EditText meterReading;
    Button save_btn;
    MyDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_reading_entry);
        db = MyDatabase.getInstance(this);
        int flatId = getIntent().getIntExtra("flatID",0);

        meterReading = findViewById(R.id.edit_currentMeterReading);
        save_btn = findViewById(R.id.readingSave_btn);

        Flat flat = db.getOneFlat(flatId);
        Float unit_rate = flat.getElectricity_rate();
      List<Meter>meterList =  db.getAllMeterReading(flatId);
      int Flatmeter_reading_entry_size = meterList.size();
      Meter lastReadingMeter = meterList.get(Flatmeter_reading_entry_size-1);
      float lastReadingInUnit = lastReadingMeter.getCurrent_reading();

      save_btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Float reading = Float.parseFloat(meterReading.getText().toString());
              Toast.makeText(MeterReadingEntry.this, "Reading: "+Float.toString(reading), Toast.LENGTH_SHORT).show();
              float cost = (reading - lastReadingInUnit)*unit_rate;
              String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
              Meter meter = new Meter(-1,reading,cost,date,flatId);
              db.addMeterReading(meter);
              Toast.makeText(MeterReadingEntry.this, "Saved Succesfully", Toast.LENGTH_SHORT).show();
          }
      });

    }
}