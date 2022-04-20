package com.jackingaming.mealmaker3000pos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MealQueueViewerActivity extends AppCompatActivity {
    private TextView textViewMealQueueViewer;
    private Button buttonRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_queue_viewer);

        textViewMealQueueViewer = findViewById(R.id.textView_meal_queue_viewer);
        buttonRefresh = findViewById(R.id.button_refresh);

        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "refresh button clicked.",
                        Toast.LENGTH_SHORT)
                        .show();

                
            }
        });
    }
}