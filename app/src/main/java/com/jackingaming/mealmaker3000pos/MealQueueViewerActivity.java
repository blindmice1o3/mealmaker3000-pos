package com.jackingaming.mealmaker3000pos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.jackingaming.mealmaker3000pos.models.RecordOfMeal;
import com.jackingaming.mealmaker3000pos.recyclerview.RecordOfMealAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MealQueueViewerActivity extends AppCompatActivity {
    private final static String TAG = "MealQueueViewerActivity";
    private final String URL_GET_NEW_MEALS_AS_JSON_ARRAY = "http://192.168.1.143:8080/kafka/receive_new_meals_as_jsonarray";
    private final String PREFERENCE_CONTENT_OF_SB = "preferenceContentOfSB";
    private final String KEY_RECORDS_OF_MEAL = "keyRecordsOfMeal";

    private List<RecordOfMeal> recordsOfMeal;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rvMealQueueViewer;
    private RecordOfMealAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_queue_viewer);
        Log.i(TAG, "onCreate(Bundle)");

        setTitle("MealMaker3000QueueViewer");

        // Initialize recordsOfMeal
        recordsOfMeal = new ArrayList<RecordOfMeal>();
        for (long i = 1L; i <= 5L; i++) {
            recordsOfMeal.add(new RecordOfMeal(i, "HelloWorld", 1620L, "myTopic", 3, 25L));
        }
        loadRecordsOfMeal();

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        // Sets up a SwipeRefreshLayout.OnRefreshListener that is invoked when the user
        // performs a swipe-to-refresh gesture.
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        Log.i(TAG, "onRefresh called from SwipeRefreshLayout");
                        refreshViaSwipe();
                    }
                });

        // Lookup the recyclerview in activity layout
        rvMealQueueViewer = findViewById(R.id.rv_meal_queue_viewer);
        // Create adapter passing in the records of meal data
        adapter = new RecordOfMealAdapter(recordsOfMeal,
                new RecordOfMealAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int position) {
                        Log.i(TAG, "onItemClick(View, int)");
                        recordsOfMeal.remove(position);
                        adapter.notifyItemRemoved(position);
                    }
                });
        // Attach the adapter to the recyclerview to populate items
        rvMealQueueViewer.setAdapter(adapter);
        // Set layout manager to position the items
        rvMealQueueViewer.setLayoutManager(new LinearLayoutManager(this));
        // Set decorator to display dividers between each item within the list
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this,
                        DividerItemDecoration.VERTICAL);
        rvMealQueueViewer.addItemDecoration(itemDecoration);
    }

    private void showProgressBar() {
        progressbarActionViewMenuItem.setVisible(true);
    }

    private void hideProgressBar() {
        progressbarActionViewMenuItem.setVisible(false);
    }

    private MenuItem refreshMenuItem;
    private MenuItem progressbarActionViewMenuItem;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        Log.i(TAG, "onCreateOptionsMenu(Menu)");

        getMenuInflater().inflate(R.menu.options_menu_meal_queue_viewer_activity, menu);

        // Get the MenuItem for the action item
        refreshMenuItem = menu.findItem(R.id.menu_item_refresh);
        progressbarActionViewMenuItem = menu.findItem(R.id.menu_item_actionview_progressbar);

        MenuItem customActionViewMenuItem = menu.findItem(R.id.menu_item_actionview_custom);
        View v = customActionViewMenuItem.getActionView();
        ImageView imageView = (ImageView) v.findViewById(R.id.ivCustomAction);

        MenuItem.OnActionExpandListener expandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                Log.i(TAG, "onMenuItemActionExpand(MenuItem)");
                // Do something when expanded
                Log.i(TAG, "menuItem.getItemId(): " + menuItem.getItemId());
                Log.i(TAG, "R.id.menu_item_actionview_custom: " + R.id.menu_item_actionview_custom);
                imageView.setImageResource(R.drawable.ic_menu_add);
                return true; // Return true to expand action view
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                Log.i(TAG, "onMenuItemActionCollapse(MenuItem)");
                // Do something when action item collapses
                return true; // Return true to collapse action view
            }
        };

        // Assign the listener to that action item
        customActionViewMenuItem.setOnActionExpandListener(expandListener);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.i(TAG, "onOptionsItemSelected(MenuItem)");

        switch (item.getItemId()) {
            case R.id.menu_item_refresh:
                Log.i(TAG, "Refresh menu item selected");
                refreshViaMenuItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void refreshViaSwipe() {
        refreshMenuItem.setVisible(false);

        String tagGetNewMealsAsJSONArrayRequest = "get_new_meals_as_jsonarray";
        makeJSONArrayWebServiceRequest(Request.Method.GET,
                URL_GET_NEW_MEALS_AS_JSON_ARRAY,
                tagGetNewMealsAsJSONArrayRequest,
                null,
                new VolleyResponseListener() {
                    @Override
                    public void onVolleySuccess(String url, JSONArray serverResponse) {
                        Log.i(TAG, "volleyResponseListener:: onVolleySuccess(String, JSONArray)");

                        if (serverResponse.length() != 0) {
                            Log.d(TAG, "serverResponse.length() != 0");

                            appendNewMealsToRecordsOfMeal(serverResponse);
                            saveRecordsOfMeal();
                            adapter.notifyDataSetChanged();
                        } else {
                            Log.d(TAG, "serverResponse.length() == 0");
                        }

                        swipeRefreshLayout.setRefreshing(false);
                        refreshMenuItem.setVisible(true);
                    }

                    @Override
                    public void onVolleyFailure(String url) {
                        Log.i(TAG, "volleyResponseListener:: onVolleyFailure(String)");

                        swipeRefreshLayout.setRefreshing(false);
                        refreshMenuItem.setVisible(true);
                    }
                });
    }

    private void refreshViaMenuItem() {
        refreshMenuItem.setVisible(false);
        showProgressBar();

        String tagGetNewMealsAsJSONArrayRequest = "get_new_meals_as_jsonarray";
        makeJSONArrayWebServiceRequest(Request.Method.GET,
                URL_GET_NEW_MEALS_AS_JSON_ARRAY,
                tagGetNewMealsAsJSONArrayRequest,
                null,
                new VolleyResponseListener() {
                    @Override
                    public void onVolleySuccess(String url, JSONArray serverResponse) {
                        Log.i(TAG, "volleyResponseListener:: onVolleySuccess(String, JSONArray)");

                        if (serverResponse.length() != 0) {
                            Log.d(TAG, "serverResponse.length() != 0");

                            appendNewMealsToRecordsOfMeal(serverResponse);
                            saveRecordsOfMeal();
                            adapter.notifyDataSetChanged();
                        } else {
                            Log.d(TAG, "serverResponse.length() == 0");
                        }

                        refreshMenuItem.setVisible(true);
                        hideProgressBar();
                    }

                    @Override
                    public void onVolleyFailure(String url) {
                        Log.i(TAG, "volleyResponseListener:: onVolleyFailure(String)");

                        refreshMenuItem.setVisible(true);
                        hideProgressBar();
                    }
                });
    }

    private interface VolleyResponseListener {
        void onVolleySuccess(String url, JSONArray serverResponse);

        void onVolleyFailure(String url);
    }

    private void makeJSONArrayWebServiceRequest(int method, String url, String tag, JSONArray jsonRequest, VolleyResponseListener listener) {
        Log.i(TAG, "makeJSONArrayWebServiceRequest(int, String, String, JSONArray, VolleyResponseListener)");

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(method,
                url,
                jsonRequest,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i(TAG, "jsonArrayRequest:: onResponse(JSONArray)");
                        listener.onVolleySuccess(url, response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG, "jsonArrayRequest:: onErrorResponse(VolleyError)");
                        listener.onVolleyFailure(url);
                    }
                });

        AppController.getInstance(this).addToRequestQueueWithTag(jsonArrayRequest, tag);
    }

    private void appendNewMealsToRecordsOfMeal(JSONArray serverResponse) {
        try {
            for (int i = 0; i < serverResponse.length(); i++) {
                String recordOfNewMealsAsJSONString = serverResponse.getString(i);
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

                RecordOfMeal recordOfMeal = new RecordOfMeal(keyNumberOfMealServed,
                        valueMealAsJSONString, timestamp, topic, partition, offset);

                recordsOfMeal.add(recordOfMeal);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveRecordsOfMeal() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < recordsOfMeal.size(); i++) {
            jsonArray.put(
                    recordsOfMeal.get(i).toJSON()
            );
        }

        SharedPreferences settings = getSharedPreferences(PREFERENCE_CONTENT_OF_SB, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_RECORDS_OF_MEAL, jsonArray.toString());
        editor.commit();
    }

    private void loadRecordsOfMeal() {
        SharedPreferences settings = getSharedPreferences(PREFERENCE_CONTENT_OF_SB, Context.MODE_PRIVATE);
        String stringFromPreviousRecordsOfMeal = settings.getString(KEY_RECORDS_OF_MEAL, "defaultValue");
        if (stringFromPreviousRecordsOfMeal.equals("defaultValue")) {
            Log.d(TAG, "nothing [saved in preferences] from the previous recordsOfMeal");
        } else {
            Log.d(TAG, "there is data [saved in preferences] from the previous recordsOfMeal");
            try {
                JSONArray jsonArray = new JSONArray(stringFromPreviousRecordsOfMeal);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    RecordOfMeal recordOfMeal = new RecordOfMeal(jsonObject);
                    recordsOfMeal.add(recordOfMeal);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}