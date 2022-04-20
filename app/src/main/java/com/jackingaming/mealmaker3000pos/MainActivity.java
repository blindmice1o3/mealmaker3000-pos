package com.jackingaming.mealmaker3000pos;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.jackingaming.mealmaker3000pos.models.Meal;
import com.jackingaming.mealmaker3000pos.models.menuitems.Bread;
import com.jackingaming.mealmaker3000pos.models.menuitems.MenuItem;
import com.jackingaming.mealmaker3000pos.models.menuitems.Water;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private final String URL_POST = "http://192.168.1.143:8080/kafka/publish";
    private final String URL_POST_AS_JSON_OBJECT = "http://192.168.1.143:8080/kafka/publish_jsonobject";
    private final String URL_POST_AS_JSON_ARRAY = "http://192.168.1.143:8080/kafka/publish_jsonarray";
    private final String URL_GET_AS_JSON_OBJECT = "http://192.168.1.143:8080/kafka/receive_jsonobject";
    private final String URL_GET_AS_JSON_ARRAY = "http://192.168.1.143:8080/kafka/receive_jsonarray";

    private TextView textViewForGetAsJSONArrayButton;
    private Button buttonPostBread;
    private Button buttonPostWater;
    private Button buttonGetAsJSONObject;
    private Button buttonGetAsJSONArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewForGetAsJSONArrayButton = findViewById(R.id.tv_viewport_for_get_button);
        buttonPostBread = findViewById(R.id.button_post_bread);
        buttonPostWater = findViewById(R.id.button_post_water);
        buttonGetAsJSONObject = findViewById(R.id.button_get_as_jsonobject);
        buttonGetAsJSONArray = findViewById(R.id.button_get_as_jsonarray);

        buttonPostBread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<MenuItem> meal = new ArrayList<MenuItem>();
                meal.add(new Bread());
                meal.add(new Bread());
                meal.add(new Water());
                meal.add(new Water());
                meal.add(new Bread());

                postDataAsJSONArray(meal);
            }
        });

        buttonPostWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postDataAsJSONObject(new Water());
            }
        });

        buttonGetAsJSONObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataAsJSONObject();
            }
        });

        buttonGetAsJSONArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataAsJSONArray();
            }
        });
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

    private void postDataAsJSONObject(MenuItem menuItem) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                URL_POST_AS_JSON_OBJECT,
                menuItem.toJSON(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        AppController.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    private void postData(Meal mealToBeSent) {
        Toast.makeText(this, "postData() called", Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URL_POST,
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

                String menuItemsAsJSONStringSeparatedBySpace = mealToBeSent.getMenuItemsAsJSONStringSeparatedBySpace();
                params.put("meal", menuItemsAsJSONStringSeparatedBySpace);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0, 0));
        Log.i("stringRequest::", stringRequest.toString());
        AppController.getInstance(this).addToRequestQueue(stringRequest);
    }

//    private void postData() {
//        Toast.makeText(this, "postData() called", Toast.LENGTH_SHORT).show();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,
//                URL_POST,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // response
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//                        Log.d("PostResponse:::", response);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        Log.d("Error.Response", error.toString());
//                    }
//                }) {
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("message", "Mom is the best");
//                //params.put("message", "Green");
//
//                return params;
//            }
//        };
//
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0, 0));
//        Log.i("stringRequest::", stringRequest.toString());
//        AppController.getInstance(this).addToRequestQueue(stringRequest);
//    }

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

                            StringBuilder sb = new StringBuilder();
                            for (MenuItem menuItem : menuItems) {
                                sb.append(menuItem.getName() + "\n");
                            }

                            String previousMenuItemsOnTextView = textViewForGetAsJSONArrayButton.getText().toString();
                            String newMenuItemsToAppendToTextView = sb.toString();
                            textViewForGetAsJSONArrayButton.setText(
                                    previousMenuItemsOnTextView + newMenuItemsToAppendToTextView
                            );
//                            double totalPrice = 0;
//                            for (MenuItem menuItem : menuItems) {
//                                totalPrice += menuItem.getPrice();
//                            }
//
//                            textViewViewportForGetButton.setText(
//                                    "price: " + totalPrice
//                            );
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "response.length() == 0", Toast.LENGTH_SHORT).show();
//                            textViewViewportForGetButton.setText(
//                                    "response.length() == 0"
//                            );
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

    private void getDataAsJSONObject() {
        Toast.makeText(this, "getDataAsJSONObject() called", Toast.LENGTH_SHORT).show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                URL_GET_AS_JSON_OBJECT,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("jsonObjectRequest::", response.toString());
                        Bread bread = new Bread(response);
                        textViewForGetAsJSONArrayButton.setText(
                                "price: " + bread.getPrice()
                        );
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("jsonObjectRequest::", "onErrorResponse(VolleyError)");
                    }
                });

        AppController.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}