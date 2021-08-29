package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Food extends AppCompatActivity {

    Button mButton;
    EditText mEditGrocery;
    EditText mEditRestaurant;
    EditText mEditOther;

    private String grocery;
    private String restaurant;
    private String other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        mButton = (Button)findViewById(R.id.button);
        mEditGrocery   = (EditText)findViewById(R.id.editGrocery);
        mEditRestaurant   = (EditText)findViewById(R.id.editRestaurant);
        mEditOther   = (EditText)findViewById(R.id.editOther);

        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        grocery = mEditGrocery.getText().toString();
                        restaurant = mEditRestaurant.getText().toString();
                        other = mEditOther.getText().toString();
                        Log.v("EditText grocery", grocery);
                        Log.v("EditText restaurant", restaurant);
                        Log.v("EditText other", other);
                    }
                });
    }
}
