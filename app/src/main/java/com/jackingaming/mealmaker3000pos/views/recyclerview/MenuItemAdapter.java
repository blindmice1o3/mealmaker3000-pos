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
        void onMenuItemClick(View itemView, int position);
    }

    private OnItemClickListener listener;

    public class ViewHolderNotDrink extends RecyclerView.ViewHolder {
        private TextView tvMenuItemPosition;
        private TextView tvMenuItemPrice;
        private TextView tvMenuItemName;
        private RecyclerView rvChildCustomizationDecorators;

        public ViewHolderNotDrink(@NonNull View itemView) {
            super(itemView);
            tvMenuItemPosition = (TextView) itemView.findViewById(R.id.tv_menuitem_position);
            tvMenuItemPrice = (TextView) itemView.findViewById(R.id.tv_menuitem_price);
            tvMenuItemName = (TextView) itemView.findViewById(R.id.tv_menuitem_name);
            rvChildCustomizationDecorators = (RecyclerView) itemView.findViewById(R.id.rv_child_customizationdecorators);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("MenuItemAdapter", "ViewHolderNotDrink onClick(View)");
                    if (listener != null) {
                        int position = getBindingAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onMenuItemClick(itemView, position);
                        }
                    }
                }
            });
        }

        public void bindData(int position, MenuItem menuItem) {
            tvMenuItemPosition.setText(Integer.toString(position));
            tvMenuItemPrice.setText(Double.toString(menuItem.getPrice()));
            tvMenuItemName.setText(menuItem.getName());
            tvMenuItemName.setBackgroundResource(R.color.white);

            if (menuItem.hasCustomizationDecorators()) {
                CustomizationDecoratorAdapter customizationDecoratorAdapter
                        = new CustomizationDecoratorAdapter(menuItem.getCustomizationDecorators(),
                        customizationDecoratorClickListener);
                rvChildCustomizationDecorators.setAdapter(customizationDecoratorAdapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(rvChildCustomizationDecorators.getContext());
                rvChildCustomizationDecorators.setLayoutManager(layoutManager);
            }
        }
    }

    public class ViewHolderDrink extends RecyclerView.ViewHolder {
        private TextView tvMenuItemPosition;
        private TextView tvMenuItemPrice;
        private TextView tvMenuItemName;
        private RecyclerView rvChildCustomizationDecorators;

        public ViewHolderDrink(@NonNull View itemView) {
            super(itemView);
            tvMenuItemPosition = (TextView) itemView.findViewById(R.id.tv_menuitem_position);
            tvMenuItemPrice = (TextView) itemView.findViewById(R.id.tv_menuitem_price);
            tvMenuItemName = (TextView) itemView.findViewById(R.id.tv_menuitem_name);
            rvChildCustomizationDecorators = (RecyclerView) itemView.findViewById(R.id.rv_child_customizationdecorators);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("MenuItemAdapter", "ViewHolderDrink onClick(View)");
                    if (listener != null) {
                        int position = getBindingAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onMenuItemClick(itemView, position);
                        }
                    }
                }
            });
        }

        public void bindData(int position, MenuItem menuItem) {
            tvMenuItemPosition.setText(Integer.toString(position));
            tvMenuItemPrice.setText(Double.toString(menuItem.getPrice()));
            tvMenuItemName.setText(menuItem.getName());
            tvMenuItemName.setBackgroundResource(R.color.light_green);

            if (menuItem.hasCustomizationDecorators()) {
                CustomizationDecoratorAdapter customizationDecoratorAdapter
                        = new CustomizationDecoratorAdapter(menuItem.getCustomizationDecorators(),
                        customizationDecoratorClickListener);
                rvChildCustomizationDecorators.setAdapter(customizationDecoratorAdapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(rvChildCustomizationDecorators.getContext());
                rvChildCustomizationDecorators.setLayoutManager(layoutManager);
            }
        }
    }

    private List<MenuItem> menuItems;
    private CustomizationDecoratorAdapter.OnItemClickListener customizationDecoratorClickListener;

    public MenuItemAdapter(List<MenuItem> menuItems,
                           OnItemClickListener listener,
                           CustomizationDecoratorAdapter.OnItemClickListener customizationDecoratorClickListener) {
        this.menuItems = menuItems;
        this.listener = listener;
        this.customizationDecoratorClickListener = customizationDecoratorClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        MenuItem menuItem = menuItems.get(position);
        if (menuItem instanceof Drink) {
            return VIEW_TYPE_DRINK;
        } else {
            return VIEW_TYPE_NOT_DRINK;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == VIEW_TYPE_NOT_DRINK) {
            View itemViewNotDrink = inflater.inflate(R.layout.rv_item_parent_menuitem, parent, false);
            viewHolder = new ViewHolderNotDrink(itemViewNotDrink);
        } else {
            View itemViewDrink = inflater.inflate(R.layout.rv_item_parent_menuitem, parent, false);
            viewHolder = new ViewHolderDrink(itemViewDrink);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Populate data into the item through holder
        MenuItem menuItem = menuItems.get(position);

        if (holder.getItemViewType() == VIEW_TYPE_NOT_DRINK) {
            ViewHolderNotDrink viewHolderNotDrink = (ViewHolderNotDrink) holder;
            viewHolderNotDrink.bindData(position, menuItem);
        } else {
            ViewHolderDrink viewHolderDrink = (ViewHolderDrink) holder;
            viewHolderDrink.bindData(position, menuItem);
        }
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }
}