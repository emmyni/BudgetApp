package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Overview extends AppCompatActivity {
    ArrayList<Plan> plans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        // Lookup the recyclerview in activity layout
        RecyclerView rvIncome = (RecyclerView) findViewById(R.id.rvIncome);

        // Initialize plans
        plans = Plan.createPlansList(5);
        // Create adapter passing in the sample user data
        PlanAdapter adapter = new PlanAdapter(this, plans);
        // Attach the adapter to the recyclerview to populate items
        rvIncome.setAdapter(adapter);
        // Set layout manager to position the items
        rvIncome.setLayoutManager(new LinearLayoutManager(this));

        // Lookup the recyclerview in activity layout
        RecyclerView rvExpenses = (RecyclerView) findViewById(R.id.rvExpenses);

        // Initialize plans
        plans = Plan.createPlansList(5);
        // Create adapter passing in the sample user data
        PlanAdapter adapterExpenses = new PlanAdapter(this, plans);
        // Attach the adapter to the recyclerview to populate items
        rvExpenses.setAdapter(adapterExpenses);
        // Set layout manager to position the items
        rvExpenses.setLayoutManager(new LinearLayoutManager(this));
    }
}
