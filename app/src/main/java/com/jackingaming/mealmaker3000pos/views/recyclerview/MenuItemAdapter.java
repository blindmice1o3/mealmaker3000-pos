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

import java.util.List;

public class MenuItemAdapter
        extends RecyclerView.Adapter<MenuItemAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onMenuItemClick(View itemView, int position);
    }

    private OnItemClickListener listener;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMenuItemPosition;
        private TextView tvMenuItemPrice;
        private TextView tvMenuItemName;
        private RecyclerView rvChildCustomizationDecorators;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMenuItemPosition = (TextView) itemView.findViewById(R.id.tv_menuitem_position);
            tvMenuItemPrice = (TextView) itemView.findViewById(R.id.tv_menuitem_price);
            tvMenuItemName = (TextView) itemView.findViewById(R.id.tv_menuitem_name);
            rvChildCustomizationDecorators = (RecyclerView) itemView.findViewById(R.id.rv_child_customizationdecorators);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("MenuItemAdapter", "ViewHolder onClick(View)");
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

            if (menuItem.hasCustomizationDecorators()) {
                CustomizationDecoratorAdapter customizationDecoratorAdapter
                        = new CustomizationDecoratorAdapter(menuItem.getCustomizationDecorators(),
                        new CustomizationDecoratorAdapter.OnItemClickListener() {
                            @Override
                            public void onCustomizationDecoratorClick(View itemView, int position) {
                                Log.i("MenuItemAdapter", "onCustomizationDecoratorClick(View, int)");
                                // TODO: remove [CustomizationDecorator] specified by position.
                                menuItem.getCustomizationDecorators().remove(position);
                                notifyDataSetChanged();
                            }
                        });
                rvChildCustomizationDecorators.setAdapter(customizationDecoratorAdapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(rvChildCustomizationDecorators.getContext());
                rvChildCustomizationDecorators.setLayoutManager(layoutManager);
            }
        }
    }

    private List<MenuItem> menuItems;

    public MenuItemAdapter(List<MenuItem> menuItems, OnItemClickListener listener) {
        this.menuItems = menuItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.rv_item_parent_menuitem, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Populate data into the item through holder
        MenuItem menuItem = menuItems.get(position);
        holder.bindData(position, menuItem);
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }
}