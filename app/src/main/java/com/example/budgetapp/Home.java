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

public class Home extends AppCompatActivity {
    Button mButton;
    EditText mEditRent;
    EditText mEditPower;
    EditText mEditInternet;
    EditText mEditOther;

    private Context mContext;

    private Double rent;
    private Double power;
    private Double internet;
    private Double other;

    private String mDate;

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

        mContext = this;

        mButton = (Button)findViewById(R.id.button);
        mEditRent   = (EditText)findViewById(R.id.editRent);
        mEditPower   = (EditText)findViewById(R.id.editPower);
        mEditInternet   = (EditText)findViewById(R.id.editInternet);
        mEditOther   = (EditText)findViewById(R.id.editOther);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mDate = extras.getString("date");
        }

        EditText[] fields = {mEditRent, mEditPower, mEditInternet, mEditOther};
        String[] details= {"rent", "hydro", "internet", "other"};

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(user.getUid());

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(mDate).child("House").exists()) {
                    for (int j=0; j < details.length; j++) {
                        if(dataSnapshot.child(mDate).child("House").child(details[j]).exists()) {
                            fields[j].setText(dataSnapshot.child(mDate).child("House").child(details[j]).getValue().toString());
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
                        rent = 0.0;
                        power = 0.0;
                        internet = 0.0;
                        other = 0.0;

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

                        myRef.child(mDate).child("House").child("rent").setValue(rent);
                        myRef.child(mDate).child("House").child("hydro").setValue(power);
                        myRef.child(mDate).child("House").child("internet").setValue(internet);
                        myRef.child(mDate).child("House").child("other").setValue(other);

                        Intent activity2Intent = new Intent(mContext, CalendarPage.class);
                        mContext.startActivity(activity2Intent);
                    }
                });
    }
}
