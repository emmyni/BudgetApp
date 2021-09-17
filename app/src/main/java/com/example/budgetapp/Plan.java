package com.example.budgetapp;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Plan {
    private String mName;
    private Double mValue;

    public Plan(String name, Double value) {
        mName = name;
        mValue = value;
    }

    public String getName() {
        return mName;
    }

    public Double getValue() {
        return mValue;
    }

    public static ArrayList<Plan> createPlansList(int numPlans, boolean isExpense, Double[] values, String date) {
        ArrayList<Plan> plans = new ArrayList<Plan>();
        String[] typeExpense = {"Grocery", "House", "Transportation", "Entertainment", "Other"};
        String[] typeIncome = {"Income"};

        String[] chosenType = isExpense ? typeExpense : typeIncome;

        for (int i = 1; i <= numPlans; i++) {
            plans.add(new Plan(chosenType[i-1], values[i-1]));
        }

        return plans;
    }
}
