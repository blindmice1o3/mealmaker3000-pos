package com.jackingaming.mealmaker3000pos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.jackingaming.mealmaker3000pos.recyclerview.MealAdapter;
import com.jackingaming.mealmaker3000pos.recyclerview.RecordOfMealAdapter;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealStagingActivity extends AppCompatActivity {
    private final static String TAG = "MealStagingActivity";
    private final String URL_POST_MEAL_AS_JSON_STRING = "http://192.168.1.143:8080/kafka/publish_jsonmeal";

    private RecyclerView rvMealStaging;
    private MealAdapter adapter;
    private Button buttonPostMealToKafka;

    private Button buttonBread;
    private Button buttonWater;

    private Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_staging);

        meal = new Meal();
        meal.addMenuItem(new Bread());

        // Lookup the recyclerview in activity layout
        rvMealStaging = findViewById(R.id.rv_meal_staging);
        // Create adapter passing in the meal as the rv's data source
        adapter = new MealAdapter(meal,
                new MealAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int position) {
                        Log.i(TAG, "onItemClick(View, int)");
                        meal.removeMenuItem(position);
                        adapter.notifyItemRemoved(position);
                    }
                });
        // Attach the adapter to the recyclerview to populate items
        rvMealStaging.setAdapter(adapter);
        // Set layout manager to position the items
        rvMealStaging.setLayoutManager(new LinearLayoutManager(this));
        // Set decorator to display dividers between each item within the list
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this,
                        DividerItemDecoration.VERTICAL);
        rvMealStaging.addItemDecoration(itemDecoration);


        buttonPostMealToKafka = findViewById(R.id.button_post_meal_to_kafka);
        buttonPostMealToKafka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject mealAsJSONObject = meal.toJSON();
                String mealAsJSONString = mealAsJSONObject.toString();

                postMealAsJSONString(mealAsJSONString);

                meal.clearMenuItems();
                adapter.notifyDataSetChanged();
            }
        });

        buttonBread = findViewById(R.id.button_bread);
        buttonBread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meal.addMenuItem(new Bread());
                // TODO: adapter.notifyItemInserted(int position)
                adapter.notifyDataSetChanged();
            }
        });

        buttonWater = findViewById(R.id.button_water);
        buttonWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meal.addMenuItem(new Water());
                // TODO: adapter.notifyItemInserted(int position)
                adapter.notifyDataSetChanged();
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

    private void postMealAsJSONString(String mealAsJSONString) {
        Toast.makeText(this, "postMealAsJSONString(String) called", Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URL_POST_MEAL_AS_JSON_STRING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "onResponse(String)", Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "onResponse(String): " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "onErrorResponse(VolleyError)", Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "onErrorResponse(VolleyError): " + error.toString());
                    }
                }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //params.put("message", "Green");
                params.put("meal", mealAsJSONString);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0, 0));
        AppController.getInstance(this).addToRequestQueue(stringRequest);
        Log.i(TAG, String.format("stringRequest (%s) added to request queue.",
                stringRequest.toString())
        );
    }
}