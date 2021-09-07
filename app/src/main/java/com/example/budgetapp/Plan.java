package com.example.budgetapp;

import java.util.ArrayList;

public class Plan {
    private String mName;
    private boolean mOnline;

    public Plan(String name, boolean online) {
        mName = name;
        mOnline = online;
    }

    public String getName() {
        return mName;
    }

    public static ArrayList<Plan> createPlansList(int numPlans, boolean isExpense) {
        ArrayList<Plan> plans = new ArrayList<Plan>();
        String[] typeExpense = {"Grocery", "House", "Transportation", "Entertainment", "Other"};
        String[] typeIncome = {"Income"};

        String[] chosenType = isExpense ? typeExpense : typeIncome;

        for (int i = 1; i <= numPlans; i++) {
            plans.add(new Plan(chosenType[i-1], i <= numPlans / 2));
        }

        return plans;
    }
}
