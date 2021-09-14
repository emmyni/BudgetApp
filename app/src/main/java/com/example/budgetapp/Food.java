package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Food extends AppCompatActivity {

    Button mButton;
    EditText mEditGrocery;
    EditText mEditRestaurant;
    EditText mEditOther;

    String mUid;

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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mUid = extras.getString("uid");
        }

        mButton = (Button)findViewById(R.id.button);
        mEditGrocery   = (EditText)findViewById(R.id.editGrocery);
        mEditRestaurant   = (EditText)findViewById(R.id.editRestaurant);
        mEditOther   = (EditText)findViewById(R.id.editOther);

        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference(mUid);

                        myRef.setValue("Hello, World!");

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
                        Log.v("EditText grocery", "grocery " + getGrocery());
                        Log.v("EditText restaurant", "restaurant " + getRestaurant());
                        Log.v("EditText other", "other " + getOther());
                    }
                });
    }
}
