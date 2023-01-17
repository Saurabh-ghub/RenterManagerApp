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

public class RenterMainPage extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    // Make sure to use the FloatingActionButton for all the FABs
    FloatingActionButton mAddRab, mAddRenterFab;


    // These are taken to make visible and invisible along with FABs
    TextView addRenterText;

    // to check whether sub FAB buttons are visible or not.
    Boolean isAllFabsVisible;

    int flatID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renter_main_page);
        flatID =  getIntent().getIntExtra("flatID",0);
        MyDatabase db = MyDatabase.getInstance(this);
//        List<Flat> flatist = db.getAllFlats();
//        for (int i = 0; i < flatist.size(); i++) {
//            Toast.makeText(RenterMainPage.this, Integer.toString(flatist.get(i).getFlat_id()), Toast.LENGTH_LONG).show();
//        }
        // Register all the FABs with their IDs This FAB button is the Parent
        mAddRab = findViewById(R.id.add_rab);

//        mRecycler_View = findViewById(R.id.flat_recycle_view);
//        mRecycler_View.setHasFixedSize(true);
//
//        mRecycler_View.setItemAnimator(new DefaultItemAnimator());
//        adapter =new FlatAdapter(flatist);
//        layoutManager = new LinearLayoutManager(this);
        List<Renter> renterList = db.getAllRenters(flatID);
        Log.d("Renters Size", Integer.toString(renterList.size()));
//        mRecycler_View.setAdapter(adapter);
//        mRecycler_View.setLayoutManager(layoutManager);
        // Now you can use the adapter to display the data in the RecyclerView
        recyclerView = findViewById(R.id.renter_recycler_view);  /////////////////////////////////
        recyclerView.setAdapter(new RenterAdapter(renterList, R.layout.renter_row)); //////////////////////////////
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAddRenterFab = findViewById(R.id.add_renter_fab);

        // Also register the action name text, of all the FABs.
        addRenterText = findViewById(R.id.add_renter_text);

        // Now set all the FABs and all the action n mAddAlarmFab.setVisibility(View.GONE);
        mAddRenterFab.setVisibility(View.GONE);

        addRenterText.setVisibility(View.GONE);

        // make the boolean variable as false, as all the
        // action name texts and all the sub FABs are invisible
        isAllFabsVisible = false;

        // We will make all the FABs and action name texts
        // visible only when Parent FAB button is clicked So
        // we have to handle the Parent FAB button first, by
        // using setOnClickListener you can see below
        mAddRab.setOnClickListener(view -> {
            if (!isAllFabsVisible) {
                // when isAllFabsVisible becomes true make all
                // the action name texts and FABs VISIBLE
                mAddRenterFab.show();
                addRenterText.setVisibility(View.VISIBLE);


                // make the boolean variable true as we
                // have set the sub FABs visibility to GONE
                isAllFabsVisible = true;
            } else {
                // when isAllFabsVisible becomes true make
                // all the action name texts and FABs GONE.
                mAddRenterFab.hide();

                addRenterText.setVisibility(View.GONE);

                // make the boolean variable false as we
                // have set the sub FABs visibility to GONE
                isAllFabsVisible = false;
            }
        });
        // below is the sample action to handle add person FAB. Here it shows simple Toast msg.
        // The Toast will be shown only when they are visible and only when user clicks on them
        mAddRenterFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RenterMainPage.this, RenterDetails.class);
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