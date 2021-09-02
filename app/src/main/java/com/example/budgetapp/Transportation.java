package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Transportation extends AppCompatActivity {

    Button mButton;
    EditText mEditGas;
    EditText mEditMaintenance;
    EditText mEditPublicTransport;
    EditText mEditOther;

    private Double gas;
    private Double maintenance;
    private Double publicTransport;
    private Double other;

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

        mButton = (Button)findViewById(R.id.button);
        mEditGas   = (EditText)findViewById(R.id.editGas);
        mEditMaintenance   = (EditText)findViewById(R.id.editMaintenance);
        mEditPublicTransport   = (EditText)findViewById(R.id.editPublicTransport);
        mEditOther   = (EditText)findViewById(R.id.editOther);

        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {

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
                        Log.v("EditText gas", "gas " + getGas());
                        Log.v("EditText maintenance", "maintenance " + getMaintenance());
                        Log.v("EditText pTransport", "publicTransport " + getPublicTransport());
                        Log.v("EditText other", "other " + getOther());
                    }
                });
    }
}
