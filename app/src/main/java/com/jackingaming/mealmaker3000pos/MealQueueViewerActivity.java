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
import com.jackingaming.mealmaker3000pos.models.menuitems.Bread;
import com.jackingaming.mealmaker3000pos.models.menuitems.MenuItem;
import com.jackingaming.mealmaker3000pos.models.menuitems.Water;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MealQueueViewerActivity extends AppCompatActivity {
    private final String URL_GET_AS_JSON_ARRAY = "http://192.168.1.143:8080/kafka/receive_jsonarray";
    private static final String CONTENT_OF_STRING_BUILDER = "sb";

    private TextView textViewMealQueueViewer;
    private Button buttonRefresh;

    private StringBuilder sb = new StringBuilder();

    private static final String PREFERENCE_CONTENT_OF_SB = "preferenceContentOfSB";

    public static boolean setPreference(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_CONTENT_OF_SB, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static String getPreference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_CONTENT_OF_SB, Context.MODE_PRIVATE);
        return settings.getString(key, "defaultValue");
    }

    @Override
    protected void onPause() {
        super.onPause();
        setPreference(getApplicationContext(), "keySB", sb.toString());
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

                getDataAsJSONArray();
            }
        });

        String stringFromPreviousSB = getPreference(getApplicationContext(), "keySB");
        if (stringFromPreviousSB.equals("defaultValue")) {
            Toast.makeText(getApplicationContext(), "nothing saved in preferences", Toast.LENGTH_SHORT).show();
        } else {
            sb.append(stringFromPreviousSB);
        }
    }

    private void getDataAsJSONArray() {
        Toast.makeText(this, "getDataAsJSONArray() called", Toast.LENGTH_SHORT).show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                URL_GET_AS_JSON_ARRAY,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("jsonArrayRequest::", response.toString());

                        if (response.length() != 0) {
                            List<MenuItem> menuItems = new ArrayList<MenuItem>();
                            try {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    String name = jsonObject.getString("name");

                                    if (name.equals("bread")) {
                                        Bread bread = new Bread(jsonObject);
                                        menuItems.add(bread);
                                    } else if (name.equals("water")) {
                                        Water water = new Water(jsonObject);
                                        menuItems.add(water);
                                    } else {
                                        Log.i("MainActivity", ".getDataAsJSONArray(): Not bread, not water");
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            for (MenuItem menuItem : menuItems) {
                                sb.append(menuItem.getName() + "\n");
                            }

//                            String previousMenuItemsOnTextView = textViewMealQueueViewer.getText().toString();
//                            String newMenuItemsToAppendToTextView = sb.toString();
                            textViewMealQueueViewer.setText(
//                                    previousMenuItemsOnTextView + newMenuItemsToAppendToTextView
                                    sb.toString()
                            );
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "response.length() == 0", Toast.LENGTH_SHORT).show();
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
}