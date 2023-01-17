package com.example.renter_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MonthWisePaymentMainPage extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_wise_payment_main_page);
        int flatID = getIntent().getIntExtra("flatID",0);
        MyDatabase db = MyDatabase.getInstance(this);
        List<MonthlyRent> monthlyRentList = db.getAllMonthlyRent(flatID);


        List<MonthlyRent> monthlyRentList1 = db.getAllMonthlyRent(flatID);
        Log.d("monthly Rent List Size", Integer.toString(monthlyRentList1.size()));

        RecyclerView recyclerView = findViewById(R.id.monthwise_recycler_view);
        recyclerView.setAdapter(new MonthlyPaymentAdapter(monthlyRentList, R.layout.monthly_payment_row));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}