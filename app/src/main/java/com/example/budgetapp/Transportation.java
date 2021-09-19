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

public class Transportation extends AppCompatActivity {

    Button mButton;
    EditText mEditGas;
    EditText mEditMaintenance;
    EditText mEditPublicTransport;
    EditText mEditOther;

    private Context mContext;

    private Double gas;
    private Double maintenance;
    private Double publicTransport;
    private Double other;

    private String mDate;

    public Double getGas() {
        return gas;
    }
    public Double getMaintenance() {
        return maintenance;
    }
    public Double getPublicTransport() {
        return publicTransport;
    }
    public Double getOther() {
        return other;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportation);

        mContext = this;

        mButton = (Button)findViewById(R.id.button);
        mEditGas   = (EditText)findViewById(R.id.editGas);
        mEditMaintenance   = (EditText)findViewById(R.id.editMaintenance);
        mEditPublicTransport   = (EditText)findViewById(R.id.editPublicTransport);
        mEditOther   = (EditText)findViewById(R.id.editOther);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mDate = extras.getString("date");
        }

        EditText[] fields = {mEditGas, mEditMaintenance, mEditPublicTransport, mEditOther};
        String[] details= {"gas", "maintenance", "publicTransport", "other"};

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(user.getUid());

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(mDate).child("Transportation").exists()) {
                    for (int j=0; j < details.length; j++) {
                        if(dataSnapshot.child(mDate).child("Transportation").child(details[j]).exists()) {
                            fields[j].setText(dataSnapshot.child(mDate).child("Transportation").child(details[j]).getValue().toString());
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
                        gas = 0.0;
                        maintenance = 0.0;
                        publicTransport = 0.0;
                        other = 0.0;

                        String strGas = mEditGas.getText().toString();
                        String strMaintenance = mEditMaintenance.getText().toString();
                        String strPublicTransport = mEditPublicTransport.getText().toString();
                        String strOther = mEditOther.getText().toString();

                        if (!"".equals(strGas)){
                            gas = Double.parseDouble(strGas);
                        }
                        if (!"".equals(strMaintenance)){
                            maintenance = Double.parseDouble(strMaintenance);
                        }
                        if (!"".equals(strPublicTransport)){
                            publicTransport = Double.parseDouble(strPublicTransport);
                        }
                        if (!"".equals(strOther)){
                            other = Double.parseDouble(strOther);
                        }

                        myRef.child(mDate).child("Transportation").child("gas").setValue(gas);
                        myRef.child(mDate).child("Transportation").child("maintenance").setValue(maintenance);
                        myRef.child(mDate).child("Transportation").child("publicTransport").setValue(publicTransport);
                        myRef.child(mDate).child("Transportation").child("other").setValue(other);

                        Log.v("EditText gas", "gas " + getGas());
                        Log.v("EditText maintenance", "maintenance " + getMaintenance());
                        Log.v("EditText pTransport", "publicTransport " + getPublicTransport());
                        Log.v("EditText other", "other " + getOther());

                        Intent activity2Intent = new Intent(mContext, CalendarPage.class);
                        mContext.startActivity(activity2Intent);
                    }
                });
    }
}
