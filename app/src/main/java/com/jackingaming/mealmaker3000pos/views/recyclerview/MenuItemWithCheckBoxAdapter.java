package com.jackingaming.mealmaker3000pos.views.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jackingaming.mealmaker3000pos.R;
import com.jackingaming.mealmaker3000pos.models.menuitems.MenuItem;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Drink;

import java.util.List;

public class MenuItemWithCheckBoxAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_DRINK = 0;
    private static final int VIEW_TYPE_NOT_DRINK = 1;

    public interface MenuItemWithCheckBoxClickListener {
        void onItemClick(View view, int positionAbsoluteAdapter);
    }

    private MenuItemWithCheckBoxClickListener menuItemWithCheckBoxClickListener;

    public class ViewHolderNotDrink extends RecyclerView.ViewHolder {
        private CheckBox cbMenuItemName;
        private TextView tvMenuItemPrice;

        public ViewHolderNotDrink(@NonNull View itemView) {
            super(itemView);
            cbMenuItemName = (CheckBox) itemView.findViewById(R.id.cb_menuitem_name);
            tvMenuItemPrice = (TextView) itemView.findViewById(R.id.tv_menuitem_price);
        }

        public void bindData(RecyclerView.ViewHolder viewHolderNotDrink, int position) {
            MenuItem menuItem = menuItems.get(position);

            cbMenuItemName.setText(menuItem.getName());
            tvMenuItemPrice.setText(Double.toString(menuItem.getPrice()));

            cbMenuItemName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @SuppressLint("LongLogTag")
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    Log.i("MenuItemWithCheckBoxAdapter", "ViewHolderNotDrink onCheckedChanged(CompoundButton, boolean) isChecked: " + isChecked);
                    if (isChecked) {
                        // TODO: validate checked state of other menuitems in this meal.
                    }
                }
            });

            viewHolderNotDrink.itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("LongLogTag")
                @Override
                public void onClick(View view) {
                    Log.i("MenuItemWithCheckBoxAdapter", "ViewHolderNotDrink onClick(View)");
                    if (menuItemWithCheckBoxClickListener != null) {
                        int positionAbsoluteAdapter = getAbsoluteAdapterPosition(); // gets item position
                        Log.i("MenuItemWithCheckBoxAdapter", "positionAbsoluteAdapter: " + positionAbsoluteAdapter);
                        if (positionAbsoluteAdapter != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                            menuItemWithCheckBoxClickListener.onItemClick(view, positionAbsoluteAdapter);
                        }
                    }
                }
            });
        }
    }

    public class ViewHolderDrink extends RecyclerView.ViewHolder {
        private CheckBox cbMenuItemName;
        private TextView tvMenuItemPrice;
        private RecyclerView rvChildCustomizations;

        public ViewHolderDrink(@NonNull View itemView) {
            super(itemView);
            cbMenuItemName = (CheckBox) itemView.findViewById(R.id.cb_menuitem_name);
            tvMenuItemPrice = (TextView) itemView.findViewById(R.id.tv_menuitem_price);
            rvChildCustomizations = (RecyclerView) itemView.findViewById(R.id.rv_child_customizations);
        }

        public void bindData(RecyclerView.ViewHolder viewHolderDrink, int position) {
            // ViewHolderDrink means menuItem is a Drink (checked in [getItemViewType]).
            Drink drink = (Drink) menuItems.get(position);

            cbMenuItemName.setText(drink.getName());
            tvMenuItemPrice.setText(Double.toString(drink.getPrice()));

            CustomizationsAdapter customizationsAdapter = new CustomizationsAdapter(drink, customizationClickListener);
            rvChildCustomizations.setAdapter(customizationsAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(rvChildCustomizations.getContext());
            rvChildCustomizations.setLayoutManager(layoutManager);

            cbMenuItemName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @SuppressLint("LongLogTag")
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    Log.i("MenuItemWithCheckBoxAdapter", "ViewHolderDrink onCheckedChanged(CompoundButton, boolean) isChecked: " + isChecked);
                    if (isChecked) {
                        // TODO: validate checked state of other menuitems in this meal.
                    }
                }
            });

            viewHolderDrink.itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("LongLogTag")
                @Override
                public void onClick(View view) {
                    Log.i("MenuItemWithCheckBoxAdapter", "ViewHolderDrink onClick(View)");
                    if (menuItemWithCheckBoxClickListener != null) {
                        int positionAbsoluteAdapter = getAbsoluteAdapterPosition(); // gets item position
                        Log.i("MenuItemWithCheckBoxAdapter", "positionAbsoluteAdapter: " + positionAbsoluteAdapter);
                        if (positionAbsoluteAdapter != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                            menuItemWithCheckBoxClickListener.onItemClick(view, positionAbsoluteAdapter);
                        }
                    }
                }
            });
        }
    }

    private List<MenuItem> menuItems;
    private CustomizationsAdapter.OnItemClickListener customizationClickListener;

    public MenuItemWithCheckBoxAdapter(List<MenuItem> menuItems,
                                       MenuItemWithCheckBoxClickListener menuItemWithCheckBoxClickListener,
                                       CustomizationsAdapter.OnItemClickListener customizationClickListener) {
        this.menuItems = menuItems;
        this.menuItemWithCheckBoxClickListener = menuItemWithCheckBoxClickListener;
        this.customizationClickListener = customizationClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        MenuItem menuItem = menuItems.get(position);
        return (menuItem instanceof Drink) ? VIEW_TYPE_DRINK : VIEW_TYPE_NOT_DRINK;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = null;
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == VIEW_TYPE_DRINK) {
            itemView = inflater.inflate(R.layout.rv_menuitem_drink_w_checkbox, parent, false);
            viewHolder = new ViewHolderDrink(itemView);
        } else {
            itemView = inflater.inflate(R.layout.rv_menuitem_notdrink_w_checkbox, parent, false);
            viewHolder = new ViewHolderNotDrink(itemView);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_DRINK) {
            ViewHolderDrink viewHolderDrink = (ViewHolderDrink) holder;
            viewHolderDrink.bindData(viewHolderDrink, position);
        } else {
            ViewHolderNotDrink viewHolderNotDrink = (ViewHolderNotDrink) holder;
            viewHolderNotDrink.bindData(viewHolderNotDrink, position);
        }
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }
}