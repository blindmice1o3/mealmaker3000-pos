package com.jackingaming.mealmaker3000pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealQueueViewerActivity extends AppCompatActivity {
    private final static String TAG = "MealQueueViewerActivity";
    private final String URL_GET_NEW_MEALS_AS_JSON_ARRAY = "http://192.168.1.143:8080/kafka/receive_new_meals_as_jsonarray";
    private final String PREFERENCE_CONTENT_OF_SB = "preferenceContentOfSB";

    private TextView textViewMealQueueViewer;
    private Button buttonRefresh;

    private StringBuilder sb = new StringBuilder();

    private boolean setPreference(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_CONTENT_OF_SB, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    private String getPreference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_CONTENT_OF_SB, Context.MODE_PRIVATE);
        return settings.getString(key, "defaultValue");
    }

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

                getNewMealsAsJSONArray();
            }
        });

        String stringFromPreviousSB = getPreference(getApplicationContext(), "keySB");
        if (stringFromPreviousSB.equals("defaultValue")) {
            Toast.makeText(getApplicationContext(), "nothing saved in preferences", Toast.LENGTH_SHORT).show();
        } else {
            sb.append(stringFromPreviousSB);
        }
    }

    private void getNewMealsAsJSONArray() {
        Toast.makeText(this, "getMealAsJSONString() called", Toast.LENGTH_SHORT).show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                URL_GET_NEW_MEALS_AS_JSON_ARRAY,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("jsonArrayRequest::", response.toString());

                        if (response.length() != 0) {
                            appendNewMealsToSB(response);

                            textViewMealQueueViewer.setText(sb.toString());
                            setPreference(getApplicationContext(), "keySB", sb.toString());
//                            setPreference(getApplicationContext(), "keySB", "");
                        } else {
                            Log.d(TAG, "meals.size <= 0");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("jsonArrayRequest::", "onErrorResponse(VolleyError)");
                    }
                });

        AppController.getInstance(this).addToRequestQueue(jsonArrayRequest);
    }

    private void appendNewMealsToSB(JSONArray response) {
        try {
            for (int i = 0; i < response.length(); i++) {
                String recordOfNewMealsAsJSONString = response.getString(i);
                JSONObject recordOfNewMealsAsJSON = new JSONObject(recordOfNewMealsAsJSONString);

//                String mealAsJSONString = response.getString(i);
                Long keyNumberOfMealServed = recordOfNewMealsAsJSON.getLong("key");
                String valueMealAsJSONString = recordOfNewMealsAsJSON.getString("value");
                int partition = recordOfNewMealsAsJSON.getInt("partition");
                long offset = recordOfNewMealsAsJSON.getLong("offset");
                Log.i(TAG, "KEY: " + keyNumberOfMealServed + ", partition: " + partition + ", offset: " + offset);

//                JSONObject menuAsJSON = new JSONObject(mealAsJSONString);
                JSONObject menuAsJSON = new JSONObject(valueMealAsJSONString);
                Meal meal = new Meal(menuAsJSON);

                int numberOfMenuItemInMeal = meal.getNumberOfMenuItemInMeal();
                Log.i(TAG, "***** this meal has " + numberOfMenuItemInMeal + " menu item(s).");

                List<String> namesOfMenuItem = meal.getNameOfMenuItems();
                for (String name : namesOfMenuItem) {
                    sb.append(name + "\n");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}