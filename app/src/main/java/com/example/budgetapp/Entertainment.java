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

public class Entertainment extends AppCompatActivity {
    Button mButton;
    EditText mEditSubscriptions;
    EditText mEditShopping;
    EditText mEditActivities;
    EditText mEditOther;

    private Context mContext;

    private Double subscriptions;
    private Double shopping;
    private Double activities;
    private Double other;

    private String mDate;

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

        mContext = this;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mDate = extras.getString("date");
        }

        mButton = (Button)findViewById(R.id.button);
        mEditSubscriptions   = (EditText)findViewById(R.id.editSubscriptions);
        mEditShopping   = (EditText)findViewById(R.id.editShopping);
        mEditActivities   = (EditText)findViewById(R.id.editActivities);
        mEditOther   = (EditText)findViewById(R.id.editOther);

        EditText[] fields = {mEditSubscriptions, mEditShopping, mEditActivities, mEditOther};
        String[] details= {"subscriptions", "shopping", "activities", "other"};

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(user.getUid());

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(mDate).child("Entertainment").exists()) {
                    for (int j=0; j < details.length; j++) {
                        if(dataSnapshot.child(mDate).child("Entertainment").child(details[j]).exists()) {
                            fields[j].setText(dataSnapshot.child(mDate).child("Entertainment").child(details[j]).getValue().toString());
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
                        subscriptions = 0.0;
                        shopping = 0.0;
                        activities = 0.0;
                        other = 0.0;

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

                        myRef.child(mDate).child("Entertainment").child("subscriptions").setValue(subscriptions);
                        myRef.child(mDate).child("Entertainment").child("shopping").setValue(shopping);
                        myRef.child(mDate).child("Entertainment").child("activities").setValue(activities);
                        myRef.child(mDate).child("Entertainment").child("other").setValue(other);

                        Log.v("EditText subscriptions", "subscriptions " + getSubscriptions());
                        Log.v("EditText shopping", "shopping " + getShopping());
                        Log.v("EditText activities", "activities " + getActivities());
                        Log.v("EditText other", "other " + getOther());

                        Intent activity2Intent = new Intent(mContext, Overview.class);
                        activity2Intent.putExtra("date", mDate);
                        mContext.startActivity(activity2Intent);
                    }
                });
    }
}
