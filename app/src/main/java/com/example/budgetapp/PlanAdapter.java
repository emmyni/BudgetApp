package com.example.budgetapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlanAdapter extends
        RecyclerView.Adapter<PlanAdapter.ViewHolder> {

    private Context mContext;
    // Store a member variable for the plans
    private List<Plan> mPlans;

    private boolean mIsExpense;

    private String mDate;

    private Class[] mClassesExpense = {
            Food.class,
            Home.class,
            Transportation.class,
            Entertainment.class,
            OtherExpenses.class,
    };

    private Class[] mClassesIncome = {
            Income.class,
    };

    // Pass in the plans array into the constructor
    public PlanAdapter(Context context, List<Plan> plans, boolean isExpense, String date) {
        mContext = context;
        mPlans = plans;
        mIsExpense = isExpense;
        mDate = date;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public PlanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View planView = inflater.inflate(R.layout.item_plan, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(planView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(PlanAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Plan plan = mPlans.get(position);
        final int index = position;

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(plan.getName());
        TextView valueTextView = holder.valueTextView;
        valueTextView.setText("$"+plan.getValue().toString());
        Button button = holder.messageButton;
        button.setText("Edit");
        button.setEnabled(true);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Button Clicked");

                Class[] chosenType = mIsExpense ? mClassesExpense : mClassesIncome;
                Intent activity2Intent = new Intent(mContext, chosenType[index]);
                activity2Intent.putExtra("date", mDate);
                mContext.startActivity(activity2Intent);
            }

        });

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mPlans.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView valueTextView;
        public Button messageButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.plan_name);
            valueTextView = (TextView) itemView.findViewById(R.id.cost);
            messageButton = (Button) itemView.findViewById(R.id.message_button);
        }
    }
}
