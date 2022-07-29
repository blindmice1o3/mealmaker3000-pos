package com.jackingaming.mealmaker3000pos.views.fragments.staging_two;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jackingaming.mealmaker3000pos.AppController;
import com.jackingaming.mealmaker3000pos.R;
import com.jackingaming.mealmaker3000pos.models.Meal;
import com.jackingaming.mealmaker3000pos.models.menuitems.MenuItem;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Drink;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.AddInCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.foods.Bread;
import com.jackingaming.mealmaker3000pos.views.recyclerview.CustomizationsAdapter;
import com.jackingaming.mealmaker3000pos.views.recyclerview.MenuItemAdapter;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuItemViewportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuItemViewportFragment extends Fragment {
    private static final String TAG = "MenuItemViewportFragment";
    private final String URL_POST_MEAL_AS_JSON_STRING = "http://192.168.1.143:8080/kafka/publish_jsonmeal";
    private final int COLOR_BACKGROUND_SELECTED = Color.YELLOW;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Meal meal;
    private int indexSelectedMenuItem;
    private View viewPrevious;
    private TextView tvIndexSelectedMenuItem;
    private MenuItemAdapter menuItemAdapter;
    private RecyclerView rvMenuItemViewport;
    private Button buttonPostMealToKafka;
    private Button buttonRemoveSelectedMenuItem;

    @SuppressLint("LongLogTag")
    public MenuItemViewportFragment() {
        // Required empty public constructor
        Log.i(TAG, "MenuItemViewportFragment() empty public constructor");
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MealViewportFragment.
     */
    // TODO: Rename and change types and number of parameters
    @SuppressLint("LongLogTag")
    public static MenuItemViewportFragment newInstance(String param1, String param2) {
        Log.i(TAG, "MenuItemViewportFragment newInstance(String, String)");
        MenuItemViewportFragment fragment = new MenuItemViewportFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        meal = new Meal();
        meal.addMenuItem(new Bread());
        indexSelectedMenuItem = -1;
        if (viewPrevious != null) {
            viewPrevious.setAlpha(1.0f);
            viewPrevious = null;
        }

        // Create adapter passing in the meal as the rv's data source
        menuItemAdapter = new MenuItemAdapter(meal.getMenuItems(),
                new MenuItemAdapter.MenuItemClickListener() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onItemClick(View view, int positionAbsoluteAdapter) {
                        Log.i(TAG, "onItemClick(View, int)");
                        if (viewPrevious != null) {
                            viewPrevious.setAlpha(1.0f);
                            viewPrevious = null;
                        }

                        // Update indexSelectedMenuItem and its displayer.
                        indexSelectedMenuItem = positionAbsoluteAdapter;
                        tvIndexSelectedMenuItem.setText(Integer.toString(indexSelectedMenuItem));
                        viewPrevious = view;
                        viewPrevious.setAlpha(0.5f);
                    }
                },
                new CustomizationsAdapter.OnItemClickListener() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onCustomizationClick(Drink drink, int positionAbsoluteAdapter) {
                        Log.i(TAG, "onCustomizationClick(Drink, int)");

                        Log.i(TAG, "positionAbsoluteAdapter: " + positionAbsoluteAdapter);
                        if (!drink.getCustomizations().isEmpty()) {
                            Log.i(TAG, "onCustomizationClick(Drink, int) customizations is NOT empty");
                            drink.getCustomizations().remove(positionAbsoluteAdapter);
                            menuItemAdapter.notifyDataSetChanged();
                            indexSelectedMenuItem = -1;
                            tvIndexSelectedMenuItem.setText(Integer.toString(indexSelectedMenuItem));
                            if (viewPrevious != null) {
                                viewPrevious.setAlpha(1.0f);
                                viewPrevious = null;
                            }
                        } else {
                            Log.i(TAG, "onCustomizationClick(Drink, int) customizations is empty");
                        }
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_item_viewport, container, false);
        tvIndexSelectedMenuItem = view.findViewById(R.id.tv_index_selected_menu_item);
        rvMenuItemViewport = view.findViewById(R.id.rv_menu_item_viewport);
        buttonPostMealToKafka = view.findViewById(R.id.button_post_meal_to_kafka);
        buttonRemoveSelectedMenuItem = view.findViewById(R.id.button_remove_selected_menu_item);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvIndexSelectedMenuItem.setText(Integer.toString(indexSelectedMenuItem));

        // Attach the adapter to the recyclerview to populate items
        rvMenuItemViewport.setAdapter(menuItemAdapter);
        // Set layout manager to position the items
        rvMenuItemViewport.setLayoutManager(new LinearLayoutManager(getContext()));
        // Set decorator to display dividers between each item within the list
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getContext(),
                        DividerItemDecoration.VERTICAL);
        rvMenuItemViewport.addItemDecoration(itemDecoration);

        buttonPostMealToKafka.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View view) {
                Log.i(TAG, "buttonPostMealToKafka onClick(View)");
                JSONObject mealAsJSONObject = meal.toJSON();
                String mealAsJSONString = mealAsJSONObject.toString();

                postMealAsJSONString(mealAsJSONString);

                meal.clearMenuItems();
                menuItemAdapter.notifyDataSetChanged();
                indexSelectedMenuItem = -1;
                tvIndexSelectedMenuItem.setText(Integer.toString(indexSelectedMenuItem));
                if (viewPrevious != null) {
                    viewPrevious.setAlpha(1.0f);
                    viewPrevious = null;
                }
            }
        });

        buttonRemoveSelectedMenuItem.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View view) {
                Log.i(TAG, "buttonRemoveSelectedMenuItem onClick(View)");
                if (indexSelectedMenuItem >= 0) {
                    Log.i(TAG, "indexSelectedMenuItem: " + indexSelectedMenuItem);
                    meal.removeMenuItem(indexSelectedMenuItem);
                    menuItemAdapter.notifyItemRemoved(indexSelectedMenuItem);
                    indexSelectedMenuItem = -1;
                    tvIndexSelectedMenuItem.setText(Integer.toString(indexSelectedMenuItem));
                    if (viewPrevious != null) {
                        viewPrevious.setAlpha(1.0f);
                        viewPrevious = null;
                    }
                } else {
                    Log.i(TAG, "indexSelectedMenuItem < 0");
                    Toast.makeText(getContext(), "indexSelectedMenuItem < 0", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void addMenuItem(MenuItem menuItem) {
        meal.addMenuItem(menuItem);
        menuItemAdapter.notifyDataSetChanged();
        indexSelectedMenuItem = -1;
        tvIndexSelectedMenuItem.setText(Integer.toString(indexSelectedMenuItem));
        if (viewPrevious != null) {
            viewPrevious.setAlpha(1.0f);
            viewPrevious = null;
        }
    }

    @SuppressLint("LongLogTag")
    public void customizeSelectedMenuItem() {
        if (indexSelectedMenuItem >= 0) {
            Log.i(TAG, "indexSelectedMenuItem >= 0");
            Log.i(TAG, "indexSelectedMenuItem: " + indexSelectedMenuItem);
            MenuItem selectedMenuItem = meal.getMenuItem(indexSelectedMenuItem);
            if (selectedMenuItem instanceof Drink) {
                Log.i(TAG, "selectedMenuItem is a Drink");
                Drink selectedDrink = (Drink) selectedMenuItem;
                // TODO: Check if already contains CARAMEL (also, it may
                //  already have MOCHA... DON'T OVERWRITE IT WITHOUT MOCHA)
                selectedDrink.addToCustomizations(
                        new AddInCustomization.Builder()
                                .lineTheCup(AddInCustomization.LineTheCup.CARAMEL)
                                .build());
                menuItemAdapter.notifyDataSetChanged();
                indexSelectedMenuItem = -1;
                tvIndexSelectedMenuItem.setText(Integer.toString(indexSelectedMenuItem));
                if (viewPrevious != null) {
                    viewPrevious.setAlpha(1.0f);
                    viewPrevious = null;
                }
            } else {
                Log.i(TAG, "selectedMenuItem is NOT a Drink");
            }
        } else {
            Log.i(TAG, "indexSelectedMenuItem < 0");
            Toast.makeText(getContext(), "indexSelectedMenuItem < 0", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("LongLogTag")
    private void postMealAsJSONString(String mealAsJSONString) {
        Log.i(TAG, "postMealAsJSONString(String)");
        Toast.makeText(getContext(), "postMealAsJSONString(String) called", Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URL_POST_MEAL_AS_JSON_STRING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), "onResponse(String)", Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "onResponse(String): " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "onErrorResponse(VolleyError)", Toast.LENGTH_SHORT).show();
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
        AppController.getInstance(getContext()).addToRequestQueue(stringRequest);
        Log.i(TAG, String.format("stringRequest (%s) added to request queue.",
                stringRequest.toString())
        );
    }
}