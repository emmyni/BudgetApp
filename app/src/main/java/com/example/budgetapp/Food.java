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

public class Food extends AppCompatActivity {

    Button mButton;
    EditText mEditGrocery;
    EditText mEditRestaurant;
    EditText mEditOther;

    private Context mContext;

    private Double grocery;
    private Double restaurant;
    private Double other;

    public Double getGrocery() {
        return grocery;
    }
    public Double getRestaurant() {
        return restaurant;
    }
    public Double getOther() {
        return other;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        mContext = this;

        mButton = (Button)findViewById(R.id.button);
        mEditGrocery   = (EditText)findViewById(R.id.editGrocery);
        mEditRestaurant   = (EditText)findViewById(R.id.editRestaurant);
        mEditOther   = (EditText)findViewById(R.id.editOther);

        EditText[] fields = {mEditGrocery, mEditRestaurant, mEditOther};
        String[] details= {"grocery", "restaurant", "other"};

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(user.getUid());

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("Grocery").exists()) {
                    for (int j=0; j < details.length; j++) {
                        if(dataSnapshot.child("Grocery").child(details[j]).exists()) {
                            fields[j].setText(dataSnapshot.child("Grocery").child(details[j]).getValue().toString());
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
                        grocery = 0.0;
                        restaurant = 0.0;
                        other = 0.0;

                        String strGrocery = mEditGrocery.getText().toString();
                        String strRestaurant = mEditRestaurant.getText().toString();
                        String strOther = mEditOther.getText().toString();

                        if (!"".equals(strGrocery)){
                            grocery = Double.parseDouble(strGrocery);
                        }
                        if (!"".equals(strRestaurant)){
                            restaurant = Double.parseDouble(strRestaurant);
                        }
                        if (!"".equals(strOther)){
                            other = Double.parseDouble(strOther);
                        }

                        myRef.child("Grocery").child("grocery").setValue(grocery);
                        myRef.child("Grocery").child("restaurant").setValue(restaurant);
                        myRef.child("Grocery").child("other").setValue(other);

                        Intent activity2Intent = new Intent(mContext, CalendarPage.class);
                        mContext.startActivity(activity2Intent);
                    }
                });
    }
}
