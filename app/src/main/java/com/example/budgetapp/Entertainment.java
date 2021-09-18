package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Entertainment extends AppCompatActivity {
    Button mButton;
    EditText mEditSubscriptions;
    EditText mEditShopping;
    EditText mEditActivities;
    EditText mEditOther;

    String mUid;

    private Double subscriptions;
    private Double shopping;
    private Double activities;
    private Double other;

    public Double getSubscriptions() {
        return subscriptions;
    }
    public Double getShopping() {
        return shopping;
    }
    public Double getActivities() {
        return activities;
    }
    public Double getOther() {
        return other;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mUid = extras.getString("uid");
        }

        mButton = (Button)findViewById(R.id.button);
        mEditSubscriptions   = (EditText)findViewById(R.id.editSubscriptions);
        mEditShopping   = (EditText)findViewById(R.id.editShopping);
        mEditActivities   = (EditText)findViewById(R.id.editActivities);
        mEditOther   = (EditText)findViewById(R.id.editOther);

        EditText[] fields = {mEditSubscriptions, mEditShopping, mEditActivities, mEditOther};
        String[] details= {"subscriptions", "shopping", "activities", "other"};

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(mUid);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("Entertainment").exists()) {
                    for (int j=0; j < details.length; j++) {
                        if(dataSnapshot.child("Entertainment").child(details[j]).exists()) {
                            fields[j].setText(dataSnapshot.child("Entertainment").child(details[j]).getValue().toString());
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
                        Log.v("EditText subscriptions", "subscriptions " + getSubscriptions());
                        Log.v("EditText shopping", "shopping " + getShopping());
                        Log.v("EditText activities", "activities " + getActivities());
                        Log.v("EditText other", "other " + getOther());
                    }
                });
    }
}
