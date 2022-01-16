package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Plan extends AppCompatActivity {
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

    public static ArrayList<Plan> createPlansList(int numPlans, boolean isExpense, Double[] values) {
        ArrayList<Plan> plans = new ArrayList<>();
        String[] typeExpense = {"Grocery", "House", "Transportation", "Entertainment", "Other"};
        String[] typeIncome = {"Income"};

        String[] chosenType = isExpense ? typeExpense : typeIncome;

        for (int i = 1; i <= numPlans; i++) {
            plans.add(new Plan(chosenType[i-1], values[i-1]));
        }

        return plans;
    }
}
