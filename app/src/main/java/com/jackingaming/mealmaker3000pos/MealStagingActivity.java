package com.jackingaming.mealmaker3000pos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jackingaming.mealmaker3000pos.models.Meal;
import com.jackingaming.mealmaker3000pos.models.menuitems.Bread;
import com.jackingaming.mealmaker3000pos.models.menuitems.Water;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealStagingActivity extends AppCompatActivity {
    private final String URL_POST_MEAL_AS_JSON_STRING = "http://192.168.1.143:8080/kafka/publish_jsonmeal";

    private TextView textViewMealViewer;
    private Button buttonPostMealToKafka;

    private Button buttonBread;
    private Button buttonWater;

    private Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_staging);

        meal = new Meal();

        textViewMealViewer = findViewById(R.id.textView_meal_viewer);
        buttonPostMealToKafka = findViewById(R.id.button_post_meal_to_kafka);
        buttonBread = findViewById(R.id.button_bread);
        buttonWater = findViewById(R.id.button_water);

        buttonPostMealToKafka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject mealAsJSONObject = meal.toJSON();
                String mealAsJSONString = mealAsJSONObject.toString();

                postMealAsJSONString(mealAsJSONString);
                meal.clearMenuItems();
                updateTextView();
            }
        });

        buttonBread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meal.addMenuItem(new Bread());
                updateTextView();
            }
        });

        buttonWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meal.addMenuItem(new Water());
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
        List<String> nameOfMenuItems = meal.getNameOfMenuItems();
        for (String nameOfMenuItem : nameOfMenuItems) {
            sb.append(nameOfMenuItem + "\n");
        }

        textViewMealViewer.setText(sb.toString());
    }

    private void postMealAsJSONString(String mealToPostAsJSONString) {
        Toast.makeText(this, "postMealAsJSONString(String) called", Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URL_POST_MEAL_AS_JSON_STRING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        Log.d("PostResponse:::", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //params.put("message", "Green");
                params.put("meal", mealToPostAsJSONString);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0, 0));
        Log.i("stringRequest::", stringRequest.toString());
        AppController.getInstance(this).addToRequestQueue(stringRequest);
    }
}