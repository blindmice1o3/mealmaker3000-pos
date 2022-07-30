package com.jackingaming.mealmaker3000pos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.jackingaming.mealmaker3000pos.models.menuitems.foods.Bread;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Water;
import com.jackingaming.mealmaker3000pos.views.fragments.staging_two.input.MenuItemInputFragment;
import com.jackingaming.mealmaker3000pos.views.fragments.staging_two.viewport.MenuItemViewportFragment;
import com.jackingaming.mealmaker3000pos.views.fragments.tablayout.FoodsInputFragment;
import com.jackingaming.mealmaker3000pos.views.fragments.tablayout.DrinksInputFragment;
import com.jackingaming.mealmaker3000pos.views.fragments.tablayout.CustomizationInputFragment;
import com.jackingaming.mealmaker3000pos.views.fragments.tablayout.MilkInputFragment;
import com.jackingaming.mealmaker3000pos.views.fragments.tablayout.SidesInputFragment;
import com.jackingaming.mealmaker3000pos.views.fragments.tablayout.SyrupInputFragment;

public class MealStagingActivity extends AppCompatActivity
        implements FoodsInputFragment.FoodsClickListener,
        DrinksInputFragment.DrinksClickListener,
        SidesInputFragment.SidesClickListener,
        SyrupInputFragment.SyrupClickListener,
        MilkInputFragment.MilkClickListener,
        CustomizationInputFragment.CustomizationClickListener {
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
            case R.id.menu_item_toggle_swipeable:
                toggleSwipeable();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void toggleSwipeable() {
        boolean toggledValue = (menuItemInputFragment.isSwipeable()) ? false : true;
        menuItemInputFragment.setSwipeable(toggledValue);
    }

    @Override
    public void onBreadButtonClicked(View view) {
        menuItemViewportFragment.addMenuItem(new Bread());
    }

    @Override
    public void onEmptyButtonClicked(View view) {
        Log.d(TAG, "onEmptyButtonClicked()");
        String tagOfButtonClicked = (String) view.getTag();
        String[] rowAndColumnAsStringArray = tagOfButtonClicked.split(",");
        String rowAsString = rowAndColumnAsStringArray[0];
        String columnAsString = rowAndColumnAsStringArray[1];
        String tagAsString = rowAndColumnAsStringArray[2];
        Toast.makeText(this, rowAsString + ", " + columnAsString + ", " + tagAsString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWaterButtonClicked(View view) {
        menuItemViewportFragment.addMenuItem(new Water());
    }

    @Override
    public void onCustomizationButtonClicked(View view) {
        menuItemViewportFragment.customizeSelectedMenuItem();
    }

    @Override
    public void onTwoPercentMilkButtonClicked(View view) {
        Toast.makeText(this, "onTwoPercentMilkButtonCliked(View)", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSteamedVegetableButtonClicked(View view) {
        Toast.makeText(this, "onSteamedVegetableButtonCliked(View)", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onToffeeNutButtonClicked(View view) {
        Toast.makeText(this, "onToffeeNutButtonCliked(View)", Toast.LENGTH_SHORT).show();
    }
}