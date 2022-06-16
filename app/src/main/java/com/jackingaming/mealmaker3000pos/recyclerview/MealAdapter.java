package com.jackingaming.mealmaker3000pos.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jackingaming.mealmaker3000pos.R;
import com.jackingaming.mealmaker3000pos.models.Meal;
import com.jackingaming.mealmaker3000pos.models.menuitems.MenuItem;

public class MealAdapter extends
        RecyclerView.Adapter<MealAdapter.ViewHolder> {

    // Define the listener interface so the parent activity or fragment
    // can implement it (passed into the constructor of MealAdapter).
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    private OnItemClickListener listener;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvMenuItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMenuItem = (TextView) itemView.findViewById(R.id.tv_menuitem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null) {
                        int position = getBindingAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }

        public void bindData(MenuItem menuItem) {
            tvMenuItem.setText(
                    "name: " + menuItem.getName() + " | price: " + menuItem.getPrice()
            );
        }
    }

    private Meal meal;

    public MealAdapter(Meal meal, OnItemClickListener listener) {
        this.meal = meal;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.item_meal, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Populate data into the item through holder
        MenuItem menuItem = meal.getMenuItem(position);
        holder.bindData(menuItem);
    }

    @Override
    public int getItemCount() {
        return meal.sizeOfMenuItems();
    }
}