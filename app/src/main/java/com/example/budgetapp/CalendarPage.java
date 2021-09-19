package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarPage extends AppCompatActivity {
    Button mButton;
    Context mContext;
    TextView incomeText;
    TextView expensesText;

    CalendarView myCalendar;

    String mUid;
    String mCurDate;
    String mSelected = "";

    final Double[] valueExpense = {0.00};
    final Double[] valueIncome = {0.00};

    String[] typeExpense = {"Grocery", "House", "Transportation", "Entertainment", "Other"};
    String[] typeIncome = {"Income"};

    String[][] detailsExpenses = {
            {"grocery", "restaurant", "other"},
            {"rent", "hydro", "internet", "other"},
            {"gas", "maintenance", "publicTransport", "other"},
            {"subscriptions", "shopping", "activities", "other"},
            {"vacation", "emergencies", "savings"}
    };
    String[][] detailsIncome = {
            {"salary", "investment", "other"}
    };

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(user.getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_page);

        incomeText = (TextView)findViewById(R.id.textViewIncome);
        expensesText = (TextView)findViewById(R.id.textViewExpenses);

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
                intent.putExtra("date", mCurDate);
                mContext.startActivity(intent);
            }

        });

        myCalendar = (CalendarView) findViewById(R.id.calendarView);

        Calendar date = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        mCurDate = sdf.format(date.getTime());
        mCurDate  = mCurDate.substring(0, mCurDate.lastIndexOf("-"));

        if (mSelected.equals("")) {
            Log.d("initialize: ", mSelected);
            mSelected = mCurDate;
        }

        CalendarView.OnDateChangeListener myCalendarListener = new CalendarView.OnDateChangeListener(){

            public void onSelectedDayChange(CalendarView view, int year, int month, int day){

//                // add one because month starts at 0
                month = month + 1;
                mCurDate = year+"-"+ month;
                if (month < 10) {
                    mCurDate = year+"-0"+ month;
                }

                if (!mSelected.equals(mCurDate)) {
                    Log.d("old: ", mSelected);
                    Log.d("new: ", mCurDate);
                    mSelected = mCurDate;
                    valueExpense[0] = 0.00;
                    valueIncome[0] = 0.00;
                    getData();
                }
            }
        };

        myCalendar.setOnDateChangeListener(myCalendarListener);
        getData();
    }

    public void getData() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(mCurDate).exists()) {
                    for (int i = 0; i < typeExpense.length; i++) {
                        if(dataSnapshot.child(mCurDate).child(typeExpense[i]).exists()) {
                            for (int j=0; j < detailsExpenses[i].length; j++) {
                                if(dataSnapshot.child(mCurDate).child(typeExpense[i]).child(detailsExpenses[i][j]).exists()) {
                                    valueExpense[0] += Double.parseDouble(dataSnapshot.child(mCurDate).child(typeExpense[i]).child(detailsExpenses[i][j]).getValue().toString());
                                }
                            }
                        }
                    }

                    for (int i = 0; i < typeIncome.length; i++) {
                        if(dataSnapshot.child(mCurDate).child(typeIncome[i]).exists()) {
                            for (int j=0; j < detailsIncome[i].length; j++) {
                                if(dataSnapshot.child(mCurDate).child(typeIncome[i]).child(detailsIncome[i][j]).exists()) {
                                    valueIncome[0] += Double.parseDouble(dataSnapshot.child(mCurDate).child(typeIncome[i]).child(detailsIncome[i][j]).getValue().toString());
                                }
                            }
                        }
                    }
                }
                expensesText.setText(valueExpense[0].toString());
                incomeText.setText(valueIncome[0].toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}
