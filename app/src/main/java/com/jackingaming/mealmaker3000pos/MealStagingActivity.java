package com.jackingaming.mealmaker3000pos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.jackingaming.mealmaker3000pos.models.menuitems.Bread;
import com.jackingaming.mealmaker3000pos.models.menuitems.MenuItem;
import com.jackingaming.mealmaker3000pos.models.menuitems.Water;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MealStagingActivity extends AppCompatActivity {
    private final String URL_POST_AS_JSON_ARRAY = "http://192.168.1.143:8080/kafka/publish_jsonarray";

    private TextView textViewMealViewer;
    private Button buttonPostMealToKafka;

    private Button buttonBread;
    private Button buttonWater;

    private List<MenuItem> menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_staging);

        menuItems = new ArrayList<MenuItem>();

        textViewMealViewer = findViewById(R.id.textView_meal_viewer);
        buttonPostMealToKafka = findViewById(R.id.button_post_meal_to_kafka);
        buttonBread = findViewById(R.id.button_bread);
        buttonWater = findViewById(R.id.button_water);

        buttonPostMealToKafka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: double check this functionality.
                postDataAsJSONArray(menuItems);
                menuItems.clear();
                updateTextView();
            }
        });

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu_meal_staging_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull android.view.MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_change_to_queue_viewer:
                Toast.makeText(getApplicationContext(),
                        "queue viewer options menu item selected.",
                        Toast.LENGTH_SHORT).show();
                Intent intentMealQueueViewer = new Intent(this, MealQueueViewerActivity.class);
                startActivity(intentMealQueueViewer);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateTextView() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < menuItems.size(); i++) {
            String nameOfMenuItem = menuItems.get(i).getName();
            String newLine = nameOfMenuItem + "\n";
            sb.append(newLine);
        }

        textViewMealViewer.setText(sb.toString());
    }

    private void postDataAsJSONArray(List<MenuItem> menuItems) {
        JSONArray jsonArray = new JSONArray();
        for (MenuItem menuItem : menuItems) {
            jsonArray.put(menuItem.toJSON());
        }

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST,
                URL_POST_AS_JSON_ARRAY,
                jsonArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance(this).addToRequestQueue(jsonArrayRequest);
    }
}