package com.example.renter_management;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class Flat_details extends AppCompatActivity {
    EditText NO_OF_ROOMS,room_rent,meter_reading,electricity_charge,joining_date,advance_paid,nickname_txt;
    Button add_renter,save;
    ImageButton pick_date_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_details);
        MyDatabase myDatabase = MyDatabase.getInstance(Flat_details.this);
        nickname_txt = (EditText) findViewById(R.id.idNickName);
        NO_OF_ROOMS = findViewById(R.id.idNo_of_Rooms);
        room_rent = findViewById(R.id.idRoomRent);
        meter_reading = findViewById(R.id.idInitialMeterl);
        electricity_charge = findViewById(R.id.idUnitRate);
        joining_date = findViewById(R.id.joiningdate);
        advance_paid = findViewById(R.id.idAdvance);
//        add_renter =findViewById(R.id.add_renter);
        save = findViewById(R.id.idBtnAddContact);
        pick_date_btn =(ImageButton) findViewById(R.id.calender_view);

        pick_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        Flat_details.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                joining_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickName = nickname_txt.getText().toString();
                Integer no_of_rooms = Integer.parseInt(NO_OF_ROOMS.getText().toString());
                Integer monthly_room_rent = Integer.parseInt(room_rent.getText().toString());
                Float meter_read = Float.parseFloat(meter_reading.getText().toString());
                Float electric_rate = Float.parseFloat(electricity_charge.getText().toString());
                String joining_Date = joining_date.getText().toString();
                Integer advance_amt = Integer.parseInt(advance_paid.getText().toString());
                Log.d("DEBUG","Save button clicked");
                Flat flat = new Flat(-1,no_of_rooms,nickName,joining_Date,advance_amt,monthly_room_rent,electric_rate,meter_read);
                Toast.makeText(Flat_details.this, flat.getNickName(), Toast.LENGTH_SHORT).show();
                int flatID = (int)myDatabase.addFlat(flat);

                if(flatID == -1){
                    Toast.makeText(Flat_details.this, "Flat details not added", Toast.LENGTH_SHORT).show();
//                    List<Flat>flatList = myDatabase.getAllFlats();


                }
                else{
                    Toast.makeText(Flat_details.this, "Details Added Succesfully", Toast.LENGTH_SHORT).show();
                    Meter meter = new Meter(-1,meter_read,0,joining_Date,flatID);
                    myDatabase.addMeterReading(meter);
                }
            }
        });



    }
}