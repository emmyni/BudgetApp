package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraphSummary extends Fragment {

    public GraphSummary() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_graph_summary, container, false);
//        setContentView(R.layout.activity_graph_summary);


        if (getArguments() != null) {
            String[] valueExpense = getArguments().getStringArray("valueExpense");
            String[] valueIncome = getArguments().getStringArray("valueIncome");
            String[] typeExpense = getArguments().getStringArray("typeExpense");
            String[] typeIncome = getArguments().getStringArray("typeIncome");

            ArrayList<PieEntry> yvalues = new ArrayList<PieEntry>();
            for (int i = 0; i < valueExpense.length; i++) {
                Log.d("Curr----------------", valueExpense[i]);
                yvalues.add(new PieEntry(Float.parseFloat(valueExpense[i]), typeExpense[i], i));
            }

            for (int i = 0; i < valueIncome.length; i++) {
                Log.d("Curr----------------", valueIncome[i]);
            }
            drawChart(view, yvalues);
        }

        return view;
    }

    private void drawChart(View view, ArrayList<PieEntry> yvalues) {
        PieChart pieChart = view.findViewById(R.id.pieChart);
        pieChart.setUsePercentValues(true);

        PieDataSet dataSet = new PieDataSet(yvalues, "Expenses");
        PieData data = new PieData(dataSet);

//        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(55f);
        pieChart.setHoleRadius(58f);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
        pieChart.setHoleColor(Color.TRANSPARENT);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setRotationEnabled(true);
        pieChart.setDragDecelerationFrictionCoef(0.9f);
        pieChart.setRotationAngle(0);
        pieChart.animateY(1400, Easing.EaseInOutQuad);

        pieChart.setHighlightPerTapEnabled(true);
    }
}