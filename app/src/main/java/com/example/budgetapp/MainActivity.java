package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Plan> plans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lookup the recyclerview in activity layout
        RecyclerView rvPlans = (RecyclerView) findViewById(R.id.rvPlans);

        // Initialize plans
        plans = Plan.createPlansList(5);
        // Create adapter passing in the sample user data
        PlanAdapter adapter = new PlanAdapter(plans);
        // Attach the adapter to the recyclerview to populate items
        rvPlans.setAdapter(adapter);
        // Set layout manager to position the items
        rvPlans.setLayoutManager(new LinearLayoutManager(this));
    }
}
