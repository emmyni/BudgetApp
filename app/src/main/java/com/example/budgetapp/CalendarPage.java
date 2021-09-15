package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarPage extends AppCompatActivity {
    Button mButton;
    Context mContext;
    TextView incomeText;
    TextView expensesText;
    TextView savingsText;

    CalendarView myCalendar;

    String mUid;
    String mCurDate;

    private Double income;
    private Double expenses;
    private Double savings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_page);

        incomeText = (TextView)findViewById(R.id.textViewIncome);
        expensesText = (TextView)findViewById(R.id.textViewExpenses);
        savingsText = (TextView)findViewById(R.id.textViewSavings);

        income = 11200.;
        expenses = 1000.21;
        savings = 10001.75;

        incomeText.setText(income.toString());
        expensesText.setText(expenses.toString());
        savingsText.setText(savings.toString());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mUid = extras.getString("uid");
        }

        mContext = this;

        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Button Clicked");

                Intent intent = new Intent(mContext, Overview.class);
                intent.putExtra("uid", mUid);
                intent.putExtra("date", mCurDate);
                mContext.startActivity(intent);
            }

        });

        myCalendar = (CalendarView) findViewById(R.id.calendarView);

        Calendar date = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        mCurDate = sdf.format(date.getTime());

        CalendarView.OnDateChangeListener myCalendarListener = new CalendarView.OnDateChangeListener(){

            public void onSelectedDayChange(CalendarView view, int year, int month, int day){

                // add one because month starts at 0
                month = month + 1;
                // output to log cat **not sure how to format year to two places here**
                mCurDate = year+"-"+month+"-"+day;
            }
        };

        myCalendar.setOnDateChangeListener(myCalendarListener);

    }
}
