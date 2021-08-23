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

    public boolean isOnline() {
        return mOnline;
    }

    public static ArrayList<Plan> createPlansList(int numPlans) {
        ArrayList<Plan> plans = new ArrayList<Plan>();
        String[] type = {"Grocery", "House", "Transportation", "Entertainment", "Other"};

        for (int i = 1; i <= numPlans; i++) {
            plans.add(new Plan(type[i-1], i <= numPlans / 2));
        }

        return plans;
    }
}
