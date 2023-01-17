package com.example.renter_management;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class Payment_Form extends AppCompatActivity {
    EditText payRoomtRentAmt,payMeterAmt,payBy,payDate,payRemarks,payMode;
    Button savePayment;
    ImageView dateView;
    Flat flat;
    MyDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_form);
        db = MyDatabase.getInstance(this);
        int flatID = getIntent().getIntExtra("flatID",0);

        payRoomtRentAmt = findViewById(R.id.paidRoomRentAmt);
        payMeterAmt = findViewById(R.id.paidMeterAmt);
        payBy = findViewById(R.id.paidBy);
        payDate = findViewById(R.id.paidDate);
        payRemarks = findViewById(R.id.paidRemark);
        payMode = findViewById(R.id.payMode);
        savePayment = findViewById(R.id.save_payment);
        dateView = findViewById(R.id.dateView);

        savePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pRoomamt = payRoomtRentAmt.getText().toString();
                String pMeteramt = payMeterAmt.getText().toString();
                int Roomamt=0,Meteramt=0,paidAmt=0;
                if(pRoomamt.equals("")){
                    Roomamt=0;
                }
                if(pMeteramt.equals("")){
                    Meteramt=0;
                }

                    Roomamt = Integer.parseInt(pRoomamt);
                    Meteramt = Integer.parseInt(pMeteramt);
                    String payby = payBy.getText().toString();
                    String date = payDate.getText().toString();
                    String remarks = payRemarks.getText().toString();
                    String mode = payMode.getText().toString();
                    paidAmt = Meteramt+Roomamt;
                if( payby.equals("") || date.equals("") || mode.equals("")){
                    Toast.makeText(Payment_Form.this, "Fill All the Details", Toast.LENGTH_SHORT).show();
                }
                else{
                    Payment payment = new Payment(-1,payby,paidAmt,Roomamt,Meteramt,mode,date,remarks,flatID);
                    db.addPayment(payment);

                     flat = db.getOneFlat(flatID);
                      int monthly_flatRent = flat.getFlat_rent();
                    List<MonthlyRent>monthlyRentList = db.getAllMonthlyRent(flatID);
                    if(monthlyRentList.size() == 0 ){
                            String joining_date = flat.getJoining_date();
                            String formating_joiningDate = joining_date + "_" +joining_date;
                           String newDate = incrementMonth(formating_joiningDate);
                            int dividend = Roomamt/monthly_flatRent;
                            int remainder = Roomamt%monthly_flatRent;
                          for(int i=0;i<dividend;i++){
                              MonthlyRent monthlyRent = new MonthlyRent(-1,newDate,monthly_flatRent,0,date,flatID);
                              db.addMonthlyRent(monthlyRent);
                              newDate = incrementMonth(newDate);
                          }
                          if(remainder > 0){

                              MonthlyRent monthlyRent = new MonthlyRent(-1,newDate,remainder,monthly_flatRent-remainder,date,flatID);
                              db.addMonthlyRent(monthlyRent);
                          }
                    }
                    else{
                        int total_months = monthlyRentList.size();
                        MonthlyRent last_monthlyRent = monthlyRentList.get(total_months-1);
                        int lastMonth_due = last_monthlyRent.getDueAmount();
                        if(lastMonth_due>0){
                            if(Roomamt>lastMonth_due){
                                MonthlyRent newmonthlyRent = new MonthlyRent(last_monthlyRent.getId(),last_monthlyRent.getMonth(),monthly_flatRent,0,date,flatID);
                                Roomamt = Roomamt-lastMonth_due;
                                 db.updateMonthlyRent(newmonthlyRent);
                            }
                            else{
                                int due = lastMonth_due-Roomamt;
                                int paid = last_monthlyRent.recvAmount + Roomamt;
                                Roomamt = 0;
                                MonthlyRent newmonthlyRent = new MonthlyRent(last_monthlyRent.getId(),last_monthlyRent.getMonth(),paid,due,date,flatID);
                                db.updateMonthlyRent(newmonthlyRent);
                            }

                        }
                        if(Roomamt>0){
                            String newDate = incrementMonth(last_monthlyRent.month);

                            int dividend = Roomamt/monthly_flatRent;
                            int remainder = Roomamt%monthly_flatRent;
                          for(int i=0;i<dividend;i++){
                              MonthlyRent monthlyRent = new MonthlyRent(-1,newDate,monthly_flatRent,0,date,flatID);
                              db.addMonthlyRent(monthlyRent);
                              newDate = incrementMonth(newDate);
                          }
                          if(remainder > 0){
                              MonthlyRent monthlyRent = new MonthlyRent(-1,newDate,remainder,monthly_flatRent-remainder,date,flatID);
                              db.addMonthlyRent(monthlyRent);
                          }

                        }
                    }

                    Toast.makeText(Payment_Form.this, payment.getPayment_date(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Payment_Form.this, "Payment Added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dateView.setOnClickListener(new View.OnClickListener() {
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
                        Payment_Form.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                payDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

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

    }

    public String incrementMonth(String date){
        String[] dates  = date.split("_");
        String [] split_ddmmyyy = dates[1].split("-");
        int mm = Integer.parseInt(split_ddmmyyy[1]);
        int yy = Integer.parseInt(split_ddmmyyy[2]);
        mm += 1;
        if(mm == 13){
            mm =1;
            yy +=1;
        }
        String newDate = dates[1] + "_" + split_ddmmyyy[0] + "-" + Integer.toString(mm) +"-" + Integer.toString(yy);
        return newDate;
    }
}