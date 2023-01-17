package com.example.renter_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PaymentHistory extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    // Make sure to use the FloatingActionButton for all the FABs
    FloatingActionButton mAddPab, mAddPaymentFab;


    // These are taken to make visible and invisible along with FABs
    TextView addPaymentText;

    // to check whether sub FAB buttons are visible or not.
    Boolean isAllFabsVisible;

    int flatID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);
        flatID =  getIntent().getIntExtra("flatID",0);
        MyDatabase db = MyDatabase.getInstance(this);
        List<Payment> paymentList = db.getAllPayment(flatID);

        // Register all the FABs with their IDs This FAB button is the Parent
        mAddPab = findViewById(R.id.add_pab);

//        mRecycler_View = findViewById(R.id.flat_recycle_view);
//        mRecycler_View.setHasFixedSize(true);
//
//        mRecycler_View.setItemAnimator(new DefaultItemAnimator());
//        adapter =new FlatAdapter(flatist);
//        layoutManager = new LinearLayoutManager(this);
        List<Payment> paymentList1 = db.getAllPayment(flatID);

        Log.d("Payment Size", Integer.toString(paymentList1.size()));
//        mRecycler_View.setAdapter(adapter);
//        mRecycler_View.setLayoutManager(layoutManager);
        // Now you can use the adapter to display the data in the RecyclerView
        recyclerView = findViewById(R.id.payment_recycler_view);  /////////////////////////////////
        recyclerView.setAdapter(new PaymentAdapter(paymentList, R.layout.payment_row)); //////////////////////////////
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAddPaymentFab = findViewById(R.id.add_payment_fab);

        // Also register the action name text, of all the FABs.
        addPaymentText = findViewById(R.id.add_payment_text);

        // Now set all the FABs and all the action n mAddAlarmFab.setVisibility(View.GONE);
        mAddPaymentFab.setVisibility(View.GONE);

        addPaymentText.setVisibility(View.GONE);

        // make the boolean variable as false, as all the
        // action name texts and all the sub FABs are invisible
        isAllFabsVisible = false;

        // We will make all the FABs and action name texts
        // visible only when Parent FAB button is clicked So
        // we have to handle the Parent FAB button first, by
        // using setOnClickListener you can see below
        mAddPab.setOnClickListener(view -> {
            if (!isAllFabsVisible) {
                // when isAllFabsVisible becomes true make all
                // the action name texts and FABs VISIBLE
                mAddPaymentFab.show();
                addPaymentText.setVisibility(View.VISIBLE);


                // make the boolean variable true as we
                // have set the sub FABs visibility to GONE
                isAllFabsVisible = true;
            } else {
                // when isAllFabsVisible becomes true make
                // all the action name texts and FABs GONE.
                mAddPaymentFab.hide();

                addPaymentText.setVisibility(View.GONE);

                // make the boolean variable false as we
                // have set the sub FABs visibility to GONE
                isAllFabsVisible = false;
            }
        });
        // below is the sample action to handle add person FAB. Here it shows simple Toast msg.
        // The Toast will be shown only when they are visible and only when user clicks on them
        mAddPaymentFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentHistory.this, Payment_Form.class);
                intent.putExtra("flatID",flatID);
                startActivity(intent);
            }
        });
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