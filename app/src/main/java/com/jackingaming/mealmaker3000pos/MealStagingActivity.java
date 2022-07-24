package com.jackingaming.mealmaker3000pos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.jackingaming.mealmaker3000pos.models.menuitems.foods.Bread;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Water;
import com.jackingaming.mealmaker3000pos.views.fragments.staging_two.MenuItemInputFragment;
import com.jackingaming.mealmaker3000pos.views.fragments.staging_two.MenuItemViewportFragment;

public class MealStagingActivity extends AppCompatActivity
        implements MenuItemInputFragment.OnButtonClickListener {
    private final static String TAG = "MealStagingActivity";

    private MenuItemViewportFragment menuItemViewportFragment;
    private MenuItemInputFragment menuItemInputFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_staging_two_point_zero);
        Log.i(TAG, "onCreate(Bundle)");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.viewport_container,
                            MenuItemViewportFragment.newInstance(null, null), "viewport")
                    .add(R.id.input_container,
                            MenuItemInputFragment.newInstance(null, null), "input")
                    .commitNow();
        }

        menuItemViewportFragment = (MenuItemViewportFragment) getSupportFragmentManager().findFragmentByTag("viewport");
        Log.i(TAG, "menuItemViewportFragment findFragmentByTag(String): " + menuItemViewportFragment.toString());
        menuItemInputFragment = (MenuItemInputFragment) getSupportFragmentManager().findFragmentById(R.id.input_container);
        Log.i(TAG, "menuItemInputFragment findFragmentById(int): " + menuItemInputFragment.toString());
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

    @Override
    public void onBreadButtonClicked() {
        menuItemViewportFragment.addMenuItem(new Bread());
    }

    @Override
    public void onWaterButtonClicked() {
        menuItemViewportFragment.addMenuItem(new Water());
    }

    @Override
    public void onCustomizationButtonClicked() {
        menuItemViewportFragment.customizeSelectedMenuItem();
    }
}