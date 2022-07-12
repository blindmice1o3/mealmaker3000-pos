package com.jackingaming.mealmaker3000pos.views.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jackingaming.mealmaker3000pos.R;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.CustomizationDecorator;

import java.util.List;

public class CustomizationDecoratorAdapter
        extends RecyclerView.Adapter<CustomizationDecoratorAdapter.ChildViewHolder> {

    public interface OnItemClickListener {
        void onCustomizationDecoratorClick(View itemView, int position);
    }
    private OnItemClickListener listener;

    public class ChildViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCustomizationDecoratorName;

        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCustomizationDecoratorName = (TextView) itemView.findViewById(R.id.tv_customizationdecorator_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("LongLogTag")
                @Override
                public void onClick(View view) {
                    Log.i("CustomizationDecoratorAdapter", "ChildViewHolder onClick(View)");
                    if (listener != null) {
                        int position = getBindingAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onCustomizationDecoratorClick(itemView, position);
                        }
                    }
                }
            });
        }

        public void bindData(CustomizationDecorator customizationDecorator) {
            tvCustomizationDecoratorName.setText(customizationDecorator.getName());
        }
    }

    private List<CustomizationDecorator> customizationDecorators;

    public CustomizationDecoratorAdapter(List<CustomizationDecorator> customizationDecorators,
                                         OnItemClickListener listener) {
        this.customizationDecorators = customizationDecorators;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.rv_item_child_customizationdecorator, parent, false);

        ChildViewHolder childViewHolder = new ChildViewHolder(itemView);
        return childViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {
        CustomizationDecorator customizationDecorator = customizationDecorators.get(position);
        holder.bindData(customizationDecorator);
    }

    @Override
    public int getItemCount() {
        return customizationDecorators.size();
    }
}