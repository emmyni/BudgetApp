package com.example.budgetapp;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

public class PieMarker extends MarkerView {

    private TextView pieContent;
    private String[] expenses;

    public PieMarker(Context context, int layoutResource, String[] typeExpense) {
        super(context, layoutResource);
        pieContent = (TextView) findViewById(R.id.pieContent);
        expenses = typeExpense;
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        if ((int)e.getData() < expenses.length) {
            pieContent.setText(expenses[(int)e.getData()] + "\n$" + e.getY() );
        } else {
            pieContent.setText("Income\n$" + e.getY() );
        }


        // this will perform necessary layouting
        super.refreshContent(e, highlight);
    }

    private MPPointF mOffset;

    @Override
    public MPPointF getOffset() {

        if(mOffset == null) {
            // center the marker horizontally and vertically
            mOffset = new MPPointF(-(getWidth() / 2), -getHeight());
        }

        return mOffset;
    }
}
