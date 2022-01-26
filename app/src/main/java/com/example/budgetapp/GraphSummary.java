package com.example.budgetapp;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

public class GraphSummary extends Fragment {
    Context mContext;

    public GraphSummary() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_graph_summary, container, false);
        mContext = getContext();


        if (getArguments() != null) {
            String[] valueExpense = getArguments().getStringArray("valueExpense");
            String[] valueIncome = getArguments().getStringArray("valueIncome");
            String[] typeExpense = getArguments().getStringArray("typeExpense");
            String[] typeIncome = getArguments().getStringArray("typeIncome");

            float totalExpense = 0.0F;

            ArrayList<PieEntry> yvalues = new ArrayList<>();
            for (int i = 0; i < valueExpense.length; i++) {
                Log.d("Curr----------------", valueExpense[i]);
                totalExpense += Float.parseFloat(valueExpense[i]);
                yvalues.add(new PieEntry(Float.parseFloat(valueExpense[i]), typeExpense[i], i));
            }

            float totalIncome = 0.0F;

            for (String val : valueIncome) {
                totalIncome += Float.parseFloat(val);
                Log.d("Curr----------------", val);
            }

            if (totalIncome > totalExpense) {
                yvalues.add(new PieEntry((totalIncome - totalExpense), "income", typeExpense.length));
            }

            drawChart(view, yvalues, typeExpense);
        }

        return view;
    }

    private void drawChart(View view, ArrayList<PieEntry> yvalues,String[] typeExpense) {
        PieChart pieChart = view.findViewById(R.id.pieChart);
        pieChart.setUsePercentValues(true);

        PieDataSet dataSet = new PieDataSet(yvalues, "Expenses");
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(pieChart));

        pieChart.setUsePercentValues(true);

        pieChart.setData(data);

        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(55f);
        pieChart.setHoleRadius(50f);
        dataSet.setColors(Colours.PIE_COLORS);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
        pieChart.setDrawEntryLabels(false);

        pieChart.setHoleColor(Color.TRANSPARENT);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setRotationEnabled(true);
        pieChart.setDragDecelerationFrictionCoef(0.9f);
        pieChart.setRotationAngle(0);

        pieChart.setTouchEnabled(true);

        PieMarker marker = new PieMarker(mContext, R.layout.activity_pie_marker, typeExpense);
        pieChart.setMarker(marker);

        pieChart.setHighlightPerTapEnabled(true);
        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
}

