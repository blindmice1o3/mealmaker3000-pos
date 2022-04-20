package com.jackingaming.mealmaker3000pos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jackingaming.mealmaker3000pos.models.menuitems.Bread;
import com.jackingaming.mealmaker3000pos.models.menuitems.MenuItem;
import com.jackingaming.mealmaker3000pos.models.menuitems.Water;

import java.util.ArrayList;
import java.util.List;

public class MealStagingActivity extends AppCompatActivity {
    private TextView textViewMealViewer;
    private Button buttonBread;
    private Button buttonWater;

    private List<MenuItem> menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_staging);

        menuItems = new ArrayList<MenuItem>();

        textViewMealViewer = findViewById(R.id.textView_meal_viewer);
        buttonBread = findViewById(R.id.button_bread);
        buttonWater = findViewById(R.id.button_water);

        buttonBread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuItems.add(new Bread());
                updateTextView();
            }
        });

        buttonWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuItems.add(new Water());
                updateTextView();
            }
        });
    }

    public void updateTextView() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < menuItems.size(); i++) {
            String nameOfMenuItem = menuItems.get(i).getName();
            String newLine = nameOfMenuItem + "\n";
            sb.append(newLine);
        }

        textViewMealViewer.setText(sb.toString());
    }
}