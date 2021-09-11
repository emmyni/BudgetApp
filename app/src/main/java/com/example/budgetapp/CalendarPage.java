package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalendarPage extends AppCompatActivity {
    Button mButton;
    Context mContext;
//    TextView incomeText = (TextView)findViewById(R.id.textViewIncome);
//    TextView expensesText = (TextView)findViewById(R.id.textViewExpenses);
//    TextView savingsText = (TextView)findViewById(R.id.textViewSavings);

    private Double income;
    private Double expenses;
    private Double savings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_page);

        income = 11200.;
        expenses = 1000.21;
        savings = 10001.75;

//        incomeText.setText(income.toString());
//        expensesText.setText(expenses.toString());
//        savingsText.setText(savings.toString());
//        incomeText.setText("Hello");
        mContext = this;

        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Button Clicked");

                Intent activity2Intent = new Intent(mContext, Overview.class);
                mContext.startActivity(activity2Intent);
            }

        });
    }
}
