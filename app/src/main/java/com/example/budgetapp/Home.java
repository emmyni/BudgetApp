package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity {
    Button mButton;
    EditText mEditSubscriptions;
    EditText mEditShopping;
    EditText mEditActivities;
    EditText mEditOther;

    private Double subscriptions;
    private Double shopping;
    private Double activities;
    private Double other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mButton = (Button)findViewById(R.id.button);
        mEditSubscriptions   = (EditText)findViewById(R.id.editSubscriptions);
        mEditShopping   = (EditText)findViewById(R.id.editShopping);
        mEditActivities   = (EditText)findViewById(R.id.editActivities);
        mEditOther   = (EditText)findViewById(R.id.editOther);

        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {

                        String strSubscriptions = mEditSubscriptions.getText().toString();
                        String strShopping = mEditShopping.getText().toString();
                        String strActivities = mEditActivities.getText().toString();
                        String strOther = mEditOther.getText().toString();

                        if (!"".equals(strSubscriptions)){
                            subscriptions = Double.parseDouble(strSubscriptions);
                        }
                        if (!"".equals(strShopping)){
                            shopping = Double.parseDouble(strShopping);
                        }
                        if (!"".equals(strActivities)){
                            activities = Double.parseDouble(strActivities);
                        }
                        if (!"".equals(strOther)){
                            other = Double.parseDouble(strOther);
                        }
                        Log.v("EditText subscriptions", "subscriptions " + subscriptions);
                        Log.v("EditText shopping", "shopping " + shopping);
                        Log.v("EditText activities", "activities " + activities);
                        Log.v("EditText other", "other " + other);
                    }
                });
    }
}