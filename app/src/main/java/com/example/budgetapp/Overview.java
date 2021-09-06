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
        RecyclerView rvPlans = (RecyclerView) findViewById(R.id.rvPlans);

        // Initialize plans
        plans = Plan.createPlansList(5);
        // Create adapter passing in the sample user data
        PlanAdapter adapter = new PlanAdapter(this, plans);
        // Attach the adapter to the recyclerview to populate items
        rvPlans.setAdapter(adapter);
        // Set layout manager to position the items
        rvPlans.setLayoutManager(new LinearLayoutManager(this));

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
