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
import com.jackingaming.mealmaker3000pos.models.menuitems.MenuItem;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Drink;
import com.jackingaming.mealmaker3000pos.models.menuitems.foods.Bread;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Water;
import com.jackingaming.mealmaker3000pos.views.recyclerview.CustomizationsAdapter;
import com.jackingaming.mealmaker3000pos.views.recyclerview.MenuItemAdapter;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MealStagingActivity extends AppCompatActivity {
    private final static String TAG = "MealStagingActivity";
    private final String URL_POST_MEAL_AS_JSON_STRING = "http://192.168.1.143:8080/kafka/publish_jsonmeal";

    private TextView tvSelectedMenuItemViewer;

    private RecyclerView rvMealStaging;
    private MenuItemAdapter menuItemAdapter;
    private Button buttonPostMealToKafka;
    private Button buttonRemoveMenuItem;

    private Button buttonBread;
    private Button buttonWater;

    private Meal meal;
    private int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_staging);
        Log.i(TAG, "onCreate(Bundle)");

        tvSelectedMenuItemViewer = findViewById(R.id.tv_selected_menu_item_index);

        meal = new Meal();
        meal.addMenuItem(new Bread());
        setSelectedIndexToIndexOfLastElement();

        // Lookup the recyclerview in activity layout
        rvMealStaging = findViewById(R.id.rv_meal_staging);
        // Create adapter passing in the meal as the rv's data source
        menuItemAdapter = new MenuItemAdapter(meal.getMenuItems(),
                new MenuItemAdapter.OnItemClickListener() {
                    @Override
                    public void onMenuItemClick(int positionAbsoluteAdapter) {
                        Log.i(TAG, "onMenuItemClick(int)");
                        // TODO: update selectedIndex and its displayer.
                        selectedIndex = positionAbsoluteAdapter;
                        updateTvSelectedMenuItemView();
                    }
                },
                new CustomizationsAdapter.OnItemClickListener() {
                    @Override
                    public void onCustomizationClick(Drink drink, int positionAbsoluteAdapter) {
                        Log.i(TAG, "onCustomizationClick(Drink, int)");

                        Log.i(TAG, "positionAbsoluteAdapter: " + positionAbsoluteAdapter);
                        if (!drink.getCustomizations().isEmpty()) {
                            Log.i(TAG, "onCustomizationClick(Drink, int) customizations is NOT empty");
                            drink.getCustomizations().remove(positionAbsoluteAdapter);
                            menuItemAdapter.notifyDataSetChanged();
                            resetSelectedIndex();
                        } else {
                            Log.i(TAG, "onCustomizationClick(Drink, int) customizations is empty");
                        }
                    }
                });
        // Attach the adapter to the recyclerview to populate items
        rvMealStaging.setAdapter(menuItemAdapter);
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
                Log.i(TAG, "buttonPostMealToKafka -> onClick(View)");
                JSONObject mealAsJSONObject = meal.toJSON();
                String mealAsJSONString = mealAsJSONObject.toString();

                postMealAsJSONString(mealAsJSONString);

                clearMenuItems();
            }
        });

        buttonRemoveMenuItem = findViewById(R.id.button_remove_selected_menu_item);
        buttonRemoveMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "buttonRemoveMenuItem -> onClick(View)");
                if (selectedIndex >= 0) {
                    Log.i(TAG, "selectedIndex: " + selectedIndex);
                    removeSelectedMenuItem();
                } else {
                    Log.i(TAG, "selectedIndex < 0");
                    Toast.makeText(getApplicationContext(), "selectedIndex < 0", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonBread = findViewById(R.id.button_bread);
        buttonBread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "buttonBread -> onClick(View)");
                addMenuItem(new Bread());
            }
        });

        buttonWater = findViewById(R.id.button_water);
        buttonWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "buttonWater -> onClick(View)");
                addMenuItem(new Water());
            }
        });
    }

    private void addMenuItem(MenuItem menuItem) {
        meal.addMenuItem(menuItem);
        // TODO: adapter.notifyItemInserted(int position)
        menuItemAdapter.notifyDataSetChanged();
        setSelectedIndexToIndexOfLastElement();
    }

    private void removeSelectedMenuItem() {
        meal.removeMenuItem(selectedIndex);
        menuItemAdapter.notifyItemRemoved(selectedIndex);
        resetSelectedIndex();
    }

    private void clearMenuItems() {
        meal.clearMenuItems();
        menuItemAdapter.notifyDataSetChanged();
        resetSelectedIndex();
    }

    private static final String TV_SELECTED_MENU_ITEM_VIEWER_PREFIX = "selectedMenuItemIndex == ";

    private void resetSelectedIndex() {
        selectedIndex = -1;
        updateTvSelectedMenuItemView();
    }

    private void setSelectedIndexToIndexOfLastElement() {
        selectedIndex = meal.sizeOfMenuItems() - 1;
        updateTvSelectedMenuItemView();
    }

    private void updateTvSelectedMenuItemView() {
        tvSelectedMenuItemViewer.setText(TV_SELECTED_MENU_ITEM_VIEWER_PREFIX + selectedIndex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu_meal_staging_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull android.view.MenuItem item) {
        Log.i(TAG, "onOptionsItemSelected(android.view.MenuItem)");
        switch (item.getItemId()) {
            case R.id.menu_item_change_to_queue_viewer:
                Toast.makeText(this,
                        "queue viewer options menu item selected.",
                        Toast.LENGTH_SHORT).show();
                Intent intentMealQueueViewer = new Intent(this, MealQueueViewerActivity.class);
                startActivity(intentMealQueueViewer);
                return true;
            case R.id.menu_item_change_to_tab_experiment:
                Toast.makeText(this,
                        "tab experiment options menu item selected.",
                        Toast.LENGTH_SHORT).show();
                Intent intentTabExperiment = new Intent(this, TabExperimentActivity.class);
                startActivity(intentTabExperiment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void postMealAsJSONString(String mealAsJSONString) {
        Log.i(TAG, "postMealAsJSONString(String)");
        Toast.makeText(this, "postMealAsJSONString(String) called", Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URL_POST_MEAL_AS_JSON_STRING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MealStagingActivity.this, "onResponse(String)", Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "onResponse(String): " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MealStagingActivity.this, "onErrorResponse(VolleyError)", Toast.LENGTH_SHORT).show();
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