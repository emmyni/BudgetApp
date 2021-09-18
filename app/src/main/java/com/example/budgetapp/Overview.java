package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Overview extends AppCompatActivity {
    ArrayList<Plan> expenses;
    ArrayList<Plan> income;

    String mUid;
    String mDate;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mUid = extras.getString("uid");
            mDate = extras.getString("date");
            mDate  = mDate.substring(0, mDate.length() - 3);

            TextView title = (TextView) findViewById(R.id.title);
            title.setText(mDate);
        }

        mContext = this;

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(user.getUid());

        Double[] valueExpense = {0.00, 0.00, 0.00, 0.00, 0.00};
        Double[] valueIncome = {0.00};

        String[] typeExpense = {"Grocery", "House", "Transportation", "Entertainment", "Other"};
        String[] typeIncome = {"Income"};

        String[][] detailsExpenses = {
                {"grocery", "restaurant", "other"},
                {"rent", "hydro", "internet", "other"},
                {"gas", "maintenance", "publicTransport", "other"},
                {"subscriptions", "shopping", "activities", "other"},
                {"vacation", "emergencies", "savings"}
        };
        String[][] detailsIncome = {
                {"salary", "investment", "other"}
        };

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (int i = 0; i < typeExpense.length; i++) {
                    if(dataSnapshot.child(typeExpense[i]).exists()) {
//                        Log.d("values: ", dataSnapshot.getValue().toString());

                        for (int j=0; j < detailsExpenses[i].length; j++) {
                            if(dataSnapshot.child(typeExpense[i]).child(detailsExpenses[i][j]).exists()) {
                                valueExpense[i] += Double.parseDouble(dataSnapshot.child(typeExpense[i]).child(detailsExpenses[i][j]).getValue().toString());
                            }
                        }
                        Log.d("Success: ", valueExpense[i].toString());
                    }
                }

                for (int i = 0; i < typeIncome.length; i++) {
                    if(dataSnapshot.child(typeIncome[i]).exists()) {
                        for (int j=0; j < detailsIncome[i].length; j++) {
                            if(dataSnapshot.child(typeIncome[i]).child(detailsIncome[i][j]).exists()) {
                                valueIncome[i] += Double.parseDouble(dataSnapshot.child(typeIncome[i]).child(detailsIncome[i][j]).getValue().toString());
                            }
                        }
                        Log.d("Success: ", valueIncome[i].toString());
                    }
                }

                // Lookup the recyclerview in activity layout
                RecyclerView rvIncome = (RecyclerView) findViewById(R.id.rvExpenses);

                // Initialize plans
                expenses = Plan.createPlansList(5, true, valueExpense, mDate);
                // Create adapter passing in the sample user data
                PlanAdapter adapter = new PlanAdapter(mContext, expenses, true, mUid, mDate);
                // Attach the adapter to the recyclerview to populate items
                rvIncome.setAdapter(adapter);
                // Set layout manager to position the items
                rvIncome.setLayoutManager(new LinearLayoutManager(mContext));

                // Lookup the recyclerview in activity layout
                RecyclerView rvExpenses = (RecyclerView) findViewById(R.id.rvIncome);

                // Initialize plans
                income = Plan.createPlansList(1, false, valueIncome, mDate);
                // Create adapter passing in the sample user data
                PlanAdapter adapterExpenses = new PlanAdapter(mContext, income, false, mUid, mDate);
                // Attach the adapter to the recyclerview to populate items
                rvExpenses.setAdapter(adapterExpenses);
                // Set layout manager to position the items
                rvExpenses.setLayoutManager(new LinearLayoutManager(mContext));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
