package com.example.budgetapp;

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

    public static ArrayList<Plan> createPlansList(int numPlans, boolean isExpense, String uid, String date) {
        ArrayList<Plan> plans = new ArrayList<Plan>();
        String[] typeExpense = {"Grocery", "House", "Transportation", "Entertainment", "Other"};
        String[] typeIncome = {"Income"};

        Double[] valueExpense = {110.45, 1433.2, 11.00, 45.3, 50. };
        Double[] valueIncome = {11000.45};

        String[] chosenType = isExpense ? typeExpense : typeIncome;
        Double[] chosenValueType = isExpense ? valueExpense : valueIncome;

        for (int i = 1; i <= numPlans; i++) {
            plans.add(new Plan(chosenType[i-1], chosenValueType[i-1]));
        }

        return plans;
    }
}
