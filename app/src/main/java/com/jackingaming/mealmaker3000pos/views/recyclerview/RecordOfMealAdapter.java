package com.jackingaming.mealmaker3000pos.views.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jackingaming.mealmaker3000pos.R;
import com.jackingaming.mealmaker3000pos.models.Meal;
import com.jackingaming.mealmaker3000pos.models.RecordOfMeal;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Drink;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class RecordOfMealAdapter extends
        RecyclerView.Adapter<RecordOfMealAdapter.ViewHolder> {

    public interface CheckBoxListener {
        void onCheckBoxChecked(int positionAbsoluteAdapter);
        void onAllCheckBoxChecked(Meal meal);
    }

    private CheckBoxListener listener;

    // Provide a direct reference to each of the views within an itemView
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        private TextView keyTextView;
        private RecyclerView valueRecyclerView;
        private TextView timestampTextView;
        private TextView offsetTextView;
        private CheckBox handedOffCheckBox;
        private TextView topicTextView;
        private TextView partitionTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            keyTextView = (TextView) itemView.findViewById(R.id.tv_key);
            valueRecyclerView = (RecyclerView) itemView.findViewById(R.id.rv_value);
            timestampTextView = (TextView) itemView.findViewById(R.id.tv_timestamp);
            offsetTextView = (TextView) itemView.findViewById(R.id.tv_offset);
            handedOffCheckBox = (CheckBox) itemView.findViewById(R.id.cb_handed_off);
            topicTextView = (TextView) itemView.findViewById(R.id.tv_topic);
            partitionTextView = (TextView) itemView.findViewById(R.id.tv_partition);
        }

        public void bindData(RecordOfMeal recordOfMeal) {
            keyTextView.setText("KEY: " + Long.toString(recordOfMeal.getKeyNumberOfMealServed()));

            String mealAsJSONString = recordOfMeal.getValueMealAsJSONString();
            try {
                JSONObject mealAsJSON = new JSONObject(mealAsJSONString);
                Meal meal = new Meal(mealAsJSON);

                MenuItemWithCheckBoxAdapter menuItemWithCheckBoxAdapter = new MenuItemWithCheckBoxAdapter(meal,
                        new MenuItemWithCheckBoxAdapter.CheckBoxListener() {
                            @Override
                            public void onCheckedAllCheckBox(Meal meal) {
                                Log.i("RecordOfMealAdapter", "onCheckedAllCheckBox(Meal)");
                                // TODO:
                                listener.onAllCheckBoxChecked(meal);
                            }
                        },
                        new CustomizationsAdapter.OnItemClickListener() {
                            @Override
                            public void onCustomizationClick(Drink drink, int positionAbsoluteAdapter) {
                                Log.i("RecordOfMealAdapter", "onCustomizationClick(Drink, int)");
                                // TODO:
                            }
                        });
                valueRecyclerView.setAdapter(menuItemWithCheckBoxAdapter);
                valueRecyclerView.setLayoutManager(new LinearLayoutManager(valueRecyclerView.getContext()));
                RecyclerView.ItemDecoration itemDecoration =
                        new DividerItemDecoration(valueRecyclerView.getContext(),
                                DividerItemDecoration.VERTICAL);
                valueRecyclerView.addItemDecoration(itemDecoration);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            timestampTextView.setText("TIMESTAMP: " + Long.toString(recordOfMeal.getTimestamp()));
            offsetTextView.setText("OFFSET: " + Long.toString(recordOfMeal.getOffset()));

            handedOffCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckBox handedOffCheckBox = (CheckBox) view;
                    if (handedOffCheckBox.isChecked()) {
                        Log.i("RecordOfMealAdapter", "handedOffCheckBox's click listener - handedOffCheckBox isChecked()");
                        int positionAbsoluteAdapter = getAbsoluteAdapterPosition();
                        if (positionAbsoluteAdapter != RecyclerView.NO_POSITION) {
                            handedOffCheckBox.setChecked(false);
                            listener.onCheckBoxChecked(positionAbsoluteAdapter);
                        }
                    } else {
                        Log.i("RecordOfMealAdapter", "handedOffCheckBox's click listener - handedOffCheckBox NOT isChecked()");
                    }
                }
            });

            topicTextView.setText("TOPIC: " + recordOfMeal.getTopic());
            partitionTextView.setText("PARTITION: " + Integer.toString(recordOfMeal.getPartition()));
        }
    }

    private List<RecordOfMeal> recordsOfMeal;

    public RecordOfMealAdapter(List<RecordOfMeal> recordsOfMeal, CheckBoxListener listener) {
        this.recordsOfMeal = recordsOfMeal;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.item_recordofmeal, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Populate data into the item through holder
        RecordOfMeal recordOfMeal = recordsOfMeal.get(position);
        holder.bindData(recordOfMeal);
    }

    @Override
    public int getItemCount() {
        return recordsOfMeal.size();
    }
}
