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
import com.jackingaming.mealmaker3000pos.models.menuitems.Bread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private final String URL_POST = "http://192.168.1.143:8080/kafka/publish";
    private final String URL_GET_AS_JSON_OBJECT = "http://192.168.1.143:8080/kafka/receive_jsonobject";
    private final String URL_GET_AS_JSON_ARRAY = "http://192.168.1.143:8080/kafka/receive_jsonarray";

    private TextView textViewViewportForGetButton;
    private Button buttonPost;
    private Button buttonGetAsJSONObject;
    private Button buttonGetAsJSONArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewViewportForGetButton = findViewById(R.id.tv_viewport_for_get_button);
        buttonPost = findViewById(R.id.button_post);
        buttonGetAsJSONObject = findViewById(R.id.button_get_as_jsonobject);
        buttonGetAsJSONArray = findViewById(R.id.button_get_as_jsonarray);

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postData();
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

    private void postData() {
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
                params.put("message", "Mom is the best");
                //params.put("message", "Green");

                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, 0, 0));
        Log.i("stringRequest::", stringRequest.toString());
        AppController.getInstance(this).addToRequestQueue(stringRequest);
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

                        List<Bread> breads = new ArrayList<Bread>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Bread bread = new Bread(jsonObject);
                                breads.add(bread);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        double totalPrice = 0;
                        for (Bread bread : breads) {
                            totalPrice += bread.getPrice();
                        }

                        textViewViewportForGetButton.setText(
                                "price: " + totalPrice
                        );
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
                        textViewViewportForGetButton.setText(
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