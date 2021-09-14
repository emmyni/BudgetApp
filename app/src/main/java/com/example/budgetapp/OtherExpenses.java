package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OtherExpenses extends AppCompatActivity {
    Button mButton;
    EditText mEditVacation;
    EditText mEditEmergency;
    EditText mEditSavings;

    String mUid;

    private Double vacation;
    private Double emergency;
    private Double savings;

    public Double getVacation() {
        return vacation;
    }
    public Double getEmergency() {
        return emergency;
    }
    public Double getSavings() {
        return savings;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_expenses);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mUid = extras.getString("uid");
        }

        mButton = (Button)findViewById(R.id.button);
        mEditVacation   = (EditText)findViewById(R.id.editVacation);
        mEditEmergency   = (EditText)findViewById(R.id.editEmergency);
        mEditSavings   = (EditText)findViewById(R.id.editSavings);

        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {

                        String strVacation = mEditVacation.getText().toString();
                        String strEmergency = mEditEmergency.getText().toString();
                        String strSavings = mEditSavings.getText().toString();

                        if (!"".equals(strVacation)){
                            vacation = Double.parseDouble(strVacation);
                        }
                        if (!"".equals(strEmergency)){
                            emergency = Double.parseDouble(strEmergency);
                        }
                        if (!"".equals(strSavings)){
                            savings = Double.parseDouble(strSavings);
                        }
                        Log.v("EditText grocery", "grocery " + getVacation());
                        Log.v("EditText restaurant", "restaurant " + getEmergency());
                        Log.v("EditText other", "other " + getSavings());
                    }
                });
    }
}
