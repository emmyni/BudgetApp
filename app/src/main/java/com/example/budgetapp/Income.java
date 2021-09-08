package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Income extends AppCompatActivity {

    Button mButton;
    EditText mEditSalary;
    EditText mEditInvestment;
    EditText mEditOther;

    private Double salary;
    private Double investment;
    private Double other;

    public Double getSalary() {
        return salary;
    }
    public Double getInvestment() {
        return investment;
    }
    public Double getOther() {
        return other;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        mButton = (Button)findViewById(R.id.button);
        mEditSalary   = (EditText)findViewById(R.id.editSalary);
        mEditInvestment   = (EditText)findViewById(R.id.editInvestments);
        mEditOther   = (EditText)findViewById(R.id.editOther);

        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        String strSalary = mEditSalary.getText().toString();
                        String strInvestment = mEditInvestment.getText().toString();
                        String strOther = mEditOther.getText().toString();

                        if (!"".equals(strSalary)){
                            salary = Double.parseDouble(strSalary);
                        }
                        if (!"".equals(strInvestment)){
                            investment = Double.parseDouble(strInvestment);
                        }
                        if (!"".equals(strOther)){
                            other = Double.parseDouble(strOther);
                        }
                        Log.v("EditText ", "salary " + getSalary());
                        Log.v("EditText ", "investment " + getInvestment());
                        Log.v("EditText other", "other " + getOther());
                    }
                });

    }
}
