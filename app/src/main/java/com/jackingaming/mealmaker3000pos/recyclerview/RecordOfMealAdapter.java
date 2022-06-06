package com.jackingaming.mealmaker3000pos.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jackingaming.mealmaker3000pos.R;
import com.jackingaming.mealmaker3000pos.models.RecordOfMeal;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class RecordOfMealAdapter extends
        RecyclerView.Adapter<RecordOfMealAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView keyTextView;
        public TextView valueTextView;
        public TextView timestampTextView;
        public TextView topicTextView;
        public TextView offsetTextView;
        public TextView partitionTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            keyTextView = (TextView) itemView.findViewById(R.id.tv_key);
            valueTextView = (TextView) itemView.findViewById(R.id.tv_value);
            timestampTextView = (TextView) itemView.findViewById(R.id.tv_timestamp);
            topicTextView = (TextView) itemView.findViewById(R.id.tv_topic);
            offsetTextView = (TextView) itemView.findViewById(R.id.tv_offset);
            partitionTextView = (TextView) itemView.findViewById(R.id.tv_partition);
        }
    }

    // Store a member variable for the records of meal
    private List<RecordOfMeal> recordsOfMeal;

    // Pass in the records of meal list into the constructor
    public RecordOfMealAdapter(List<RecordOfMeal> recordsOfMeal) {
        this.recordsOfMeal = recordsOfMeal;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View recordOfMealView = inflater.inflate(R.layout.item_recordofmeal, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(recordOfMealView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        RecordOfMeal recordOfMeal = recordsOfMeal.get(position);

        // Set item views based on your views and data model
        holder.keyTextView.setText("KEY: " + Long.toString(recordOfMeal.getKey()));
        holder.valueTextView.setText("VALUE: " + recordOfMeal.getValue());
        holder.timestampTextView.setText("TIMESTAMP: " + Long.toString(recordOfMeal.getTimestamp()));
        holder.topicTextView.setText("TOPIC: " + recordOfMeal.getTopic());
        holder.offsetTextView.setText("OFFSET: " + Long.toString(recordOfMeal.getOffset()));
        holder.partitionTextView.setText("PARTITION: " + Integer.toString(recordOfMeal.getPartition()));
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return recordsOfMeal.size();
    }
}
