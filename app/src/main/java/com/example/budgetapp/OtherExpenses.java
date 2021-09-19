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

public class OtherExpenses extends AppCompatActivity {
    Button mButton;
    EditText mEditVacation;
    EditText mEditEmergency;
    EditText mEditSavings;

    private Context mContext;

    private Double vacation;
    private Double emergency;
    private Double savings;

    private String mDate;

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

        mContext = this;

        mButton = (Button)findViewById(R.id.button);
        mEditVacation   = (EditText)findViewById(R.id.editVacation);
        mEditEmergency   = (EditText)findViewById(R.id.editEmergency);
        mEditSavings   = (EditText)findViewById(R.id.editSavings);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mDate = extras.getString("date");
        }

        EditText[] fields = {mEditVacation, mEditEmergency, mEditSavings};
        String[] details= {"vacation", "emergencies", "savings"};

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(user.getUid());

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(mDate).child("Other").exists()) {
                    for (int j=0; j < details.length; j++) {
                        if(dataSnapshot.child(mDate).child("Other").child(details[j]).exists()) {
                            fields[j].setText(dataSnapshot.child(mDate).child("Other").child(details[j]).getValue().toString());
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

                        myRef.child(mDate).child("Other").child("vacation").setValue(vacation);
                        myRef.child(mDate).child("Other").child("emergency").setValue(emergency);
                        myRef.child(mDate).child("Other").child("savings").setValue(savings);

                        Intent activity2Intent = new Intent(mContext, Overview.class);
                        activity2Intent.putExtra("date", mDate);
                        mContext.startActivity(activity2Intent);
                    }
                });
    }
}
