package com.jackingaming.mealmaker3000pos.views.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jackingaming.mealmaker3000pos.R;
import com.jackingaming.mealmaker3000pos.models.menuitems.MenuItem;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Drink;

import java.util.List;

public class MenuItemAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_DRINK = 0;
    private static final int VIEW_TYPE_NOT_DRINK = 1;

    public interface OnItemClickListener {
        void onMenuItemClick(int positionAbsoluteAdapter);
    }
    private OnItemClickListener menuItemClickListener;

    public class ViewHolderNotDrink extends RecyclerView.ViewHolder {
        private TextView tvMenuItemPosition;
        private TextView tvMenuItemPrice;
        private TextView tvMenuItemName;

        public ViewHolderNotDrink(@NonNull View itemView) {
            super(itemView);
            tvMenuItemPosition = (TextView) itemView.findViewById(R.id.tv_menuitem_position);
            tvMenuItemPrice = (TextView) itemView.findViewById(R.id.tv_menuitem_price);
            tvMenuItemName = (TextView) itemView.findViewById(R.id.tv_menuitem_name);
        }

        public void bindData(RecyclerView.ViewHolder viewHolderNotDrink, int position) {
            MenuItem menuItem = menuItems.get(position);

            tvMenuItemPosition.setText(Integer.toString(position));
            tvMenuItemPrice.setText(Double.toString(menuItem.getPrice()));
            tvMenuItemName.setText(menuItem.getName());

            viewHolderNotDrink.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("MenuItemAdapter", "ViewHolderNotDrink onClick(View)");
                    if (menuItemClickListener != null) {
                        int positionAbsoluteAdapter = getAbsoluteAdapterPosition(); // gets item position
                        Log.i("MenuItemAdapter", "positionAbsoluteAdapter: " + positionAbsoluteAdapter);
                        if (positionAbsoluteAdapter != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                            menuItemClickListener.onMenuItemClick(positionAbsoluteAdapter);
                        }
                    }
                }
            });
        }
    }

    public class ViewHolderDrink extends RecyclerView.ViewHolder {
        private TextView tvMenuItemPosition;
        private TextView tvMenuItemPrice;
        private TextView tvMenuItemName;
        private RecyclerView rvChildCustomizations;

        public ViewHolderDrink(@NonNull View itemView) {
            super(itemView);
            tvMenuItemPosition = (TextView) itemView.findViewById(R.id.tv_menuitem_position);
            tvMenuItemPrice = (TextView) itemView.findViewById(R.id.tv_menuitem_price);
            tvMenuItemName = (TextView) itemView.findViewById(R.id.tv_menuitem_name);
            rvChildCustomizations = (RecyclerView) itemView.findViewById(R.id.rv_child_customizations);
        }

        public void bindData(RecyclerView.ViewHolder viewHolderDrink, int position) {
            // ViewHolderDrink means menuItem is a Drink (checked in [getItemViewType]).
            Drink drink = (Drink) menuItems.get(position);

            tvMenuItemPosition.setText(Integer.toString(position));
            tvMenuItemPrice.setText(Double.toString(drink.getPrice()));
            tvMenuItemName.setText(drink.getName());

            CustomizationsAdapter customizationsAdapter = new CustomizationsAdapter(drink, customizationClickListener);
            rvChildCustomizations.setAdapter(customizationsAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(rvChildCustomizations.getContext());
            rvChildCustomizations.setLayoutManager(layoutManager);

            viewHolderDrink.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("MenuItemAdapter", "ViewHolderDrink onClick(View)");
                    if (menuItemClickListener != null) {
                        int positionAbsoluteAdapter = getAbsoluteAdapterPosition(); // gets item position
                        Log.i("MenuItemAdapter", "positionAbsoluteAdapter: " + positionAbsoluteAdapter);
                        if (positionAbsoluteAdapter != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                            menuItemClickListener.onMenuItemClick(positionAbsoluteAdapter);
                        }
                    }
                }
            });
        }
    }

    private List<MenuItem> menuItems;
    private CustomizationsAdapter.OnItemClickListener customizationClickListener;

    public MenuItemAdapter(List<MenuItem> menuItems,
                           OnItemClickListener menuItemClickListener,
                           CustomizationsAdapter.OnItemClickListener customizationClickListener) {
        this.menuItems = menuItems;
        this.menuItemClickListener = menuItemClickListener;
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
            itemView = inflater.inflate(R.layout.rv_menuitem_drink, parent, false);
            viewHolder = new ViewHolderDrink(itemView);
        } else {
            itemView = inflater.inflate(R.layout.rv_menuitem_notdrink, parent, false);
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