package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Income extends AppCompatActivity {

    Button mButton;
    EditText mEditSalary;
    EditText mEditInvestment;
    EditText mEditOther;

    private Context mContext;

    private Double salary;
    private Double investment;
    private Double other;

    private String mDate;

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

        mContext = this;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mDate = extras.getString("date");
        }

        mButton = (Button)findViewById(R.id.button);
        mEditSalary   = (EditText)findViewById(R.id.editSalary);
        mEditInvestment   = (EditText)findViewById(R.id.editInvestments);
        mEditOther   = (EditText)findViewById(R.id.editOther);

        EditText[] fields = {mEditSalary, mEditInvestment, mEditOther};
        String[] details= {"salary", "investment", "other"};

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(user.getUid());

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(mDate).child("Income").exists()) {
                    for (int j=0; j < details.length; j++) {
                        if(dataSnapshot.child(mDate).child("Income").child(details[j]).exists()) {
                            fields[j].setText(dataSnapshot.child(mDate).child("Income").child(details[j]).getValue().toString());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        salary = 0.0;
                        investment = 0.0;
                        other = 0.0;

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

                        myRef.child(mDate).child("Income").child("salary").setValue(salary);
                        myRef.child(mDate).child("Income").child("investment").setValue(investment);
                        myRef.child(mDate).child("Income").child("other").setValue(other);

                        Log.v("EditText ", "salary " + getSalary());
                        Log.v("EditText ", "investment " + getInvestment());
                        Log.v("EditText other", "other " + getOther());

                        Intent activity2Intent = new Intent(mContext, CalendarPage.class);
                        mContext.startActivity(activity2Intent);
                    }
                });

    }
}
