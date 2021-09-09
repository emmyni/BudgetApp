package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class calendar extends AppCompatActivity {
    TextView incomeText = (TextView)findViewById(R.id.textViewIncome);
    TextView expensesText = (TextView)findViewById(R.id.textViewExpenses);
    TextView savingsText = (TextView)findViewById(R.id.textViewSavings);

    private Double income;
    private Double expenses;
    private Double savings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        income = 11200.;
        expenses = 1000.21;
        savings = 10001.75;

        incomeText.setText(income.toString());
        expensesText.setText(expenses.toString());
        savingsText.setText(savings.toString());
    }
}
