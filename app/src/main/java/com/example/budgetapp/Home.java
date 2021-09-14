package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity {
    Button mButton;
    EditText mEditRent;
    EditText mEditPower;
    EditText mEditInternet;
    EditText mEditOther;

    String mUid;

    private Double rent;
    private Double power;
    private Double internet;
    private Double other;

    public Double getRent() {
        return rent;
    }
    public Double getPower() {
        return power;
    }
    public Double getInternet() {
        return internet;
    }
    public Double getOther() {
        return other;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mUid = extras.getString("uid");
        }

        mButton = (Button)findViewById(R.id.button);
        mEditRent   = (EditText)findViewById(R.id.editRent);
        mEditPower   = (EditText)findViewById(R.id.editPower);
        mEditInternet   = (EditText)findViewById(R.id.editInternet);
        mEditOther   = (EditText)findViewById(R.id.editOther);

        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        String strRent = mEditRent.getText().toString();
                        String strPower = mEditPower.getText().toString();
                        String strInternet = mEditInternet.getText().toString();
                        String strOther = mEditOther.getText().toString();

                        if (!"".equals(strRent)){
                            rent = Double.parseDouble(strRent);
                        }
                        if (!"".equals(strPower)){
                            power = Double.parseDouble(strPower);
                        }
                        if (!"".equals(strInternet)){
                            internet = Double.parseDouble(strInternet);
                        }
                        if (!"".equals(strOther)){
                            other = Double.parseDouble(strOther);
                        }
                        Log.v("EditText ", "rent " + getRent());
                        Log.v("EditText ", "power " + getPower());
                        Log.v("EditText ", "internet " + getInternet());
                        Log.v("EditText other", "other " + getOther());
                    }
                });
    }
}
