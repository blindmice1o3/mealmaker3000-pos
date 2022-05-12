package com.jackingaming.mealmaker3000pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.jackingaming.mealmaker3000pos.models.Meal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MealQueueViewerActivity extends AppCompatActivity {
    private final static String TAG = "MealQueueViewerActivity";
    private final String URL_GET_NEW_MEALS_AS_JSON_ARRAY = "http://192.168.1.143:8080/kafka/receive_new_meals_as_jsonarray";
    private final String PREFERENCE_CONTENT_OF_SB = "preferenceContentOfSB";
    private final String KEY_SB = "keySB";

    private TextView textViewMealQueueViewer;
    private Button buttonRefresh;

    private StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_queue_viewer);

        textViewMealQueueViewer = findViewById(R.id.textView_meal_queue_viewer);
        buttonRefresh = findViewById(R.id.button_refresh);

        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "refresh button clicked.",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();

                getNewMealsAsJSONArray();
            }
        });

        loadSB();
    }

    private void getNewMealsAsJSONArray() {
        Log.i(TAG, "getNewMealsAsJSONArray()");

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                URL_GET_NEW_MEALS_AS_JSON_ARRAY,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i(TAG, "jsonArrayRequest:: onResponse(JSONArray)");

                        if (response.length() != 0) {
                            Log.d(TAG, "response.length() != 0");

                            appendNewMealsToSB(response);
                            saveSB();
                            updateTextViewMealQueueViewer();
                        } else {
                            Log.d(TAG, "response.length() == 0");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG, "jsonArrayRequest:: onErrorResponse(VolleyError)");
                    }
                });

        AppController.getInstance(this).addToRequestQueue(jsonArrayRequest);
    }

    private void appendNewMealsToSB(JSONArray response) {
        try {
            for (int i = 0; i < response.length(); i++) {
                String recordOfNewMealsAsJSONString = response.getString(i);
                JSONObject recordOfNewMealsAsJSON = new JSONObject(recordOfNewMealsAsJSONString);

                Long keyNumberOfMealServed = recordOfNewMealsAsJSON.getLong("key");
                String valueMealAsJSONString = recordOfNewMealsAsJSON.getString("value");
                long timestamp = recordOfNewMealsAsJSON.getLong("timestamp");
                String topic = recordOfNewMealsAsJSON.getString("topic");
                int partition = recordOfNewMealsAsJSON.getInt("partition");
                long offset = recordOfNewMealsAsJSON.getLong("offset");
                Log.i(TAG, "timestamp: " + timestamp +
                        ", topic: " + topic +
                        ", partition: " + partition +
                        ", offset: " + offset +
                        ", KEY: " + keyNumberOfMealServed
                );

                JSONObject menuAsJSON = new JSONObject(valueMealAsJSONString);
                Meal meal = new Meal(menuAsJSON);

                int numberOfMenuItemInMeal = meal.getNumberOfMenuItemInMeal();
                Log.d(TAG, "***** this meal has " + numberOfMenuItemInMeal + " menu item(s).");

                List<String> namesOfMenuItem = meal.getNameOfMenuItems();
                for (String name : namesOfMenuItem) {
                    sb.append(keyNumberOfMealServed + ". " + name + "\n");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveSB() {
        SharedPreferences settings = getSharedPreferences(PREFERENCE_CONTENT_OF_SB, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_SB, sb.toString());
        editor.commit();
    }

    private void loadSB() {
        SharedPreferences settings = getSharedPreferences(PREFERENCE_CONTENT_OF_SB, Context.MODE_PRIVATE);
        String stringFromPreviousSB = settings.getString(KEY_SB, "defaultValue");
        if (stringFromPreviousSB.equals("defaultValue")) {
            Log.d(TAG, "nothing [saved in preferences] from the previous sb");
        } else {
            Log.d(TAG, "there is data [saved in preferences] from the previous sb");
            sb.append(stringFromPreviousSB);
        }
    }

    private void updateTextViewMealQueueViewer() {
        textViewMealQueueViewer.setText(sb.toString());
    }
}