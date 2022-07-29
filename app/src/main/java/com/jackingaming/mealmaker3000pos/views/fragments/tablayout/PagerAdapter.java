package com.jackingaming.mealmaker3000pos.views.fragments.tablayout;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {
    private static final String TAG = "PagerAdapter";
    private int numberOfTabs;

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity, int numberOfTabs) {
        super(fragmentActivity);
        this.numberOfTabs = numberOfTabs;
    }

    private int NUMBER_OF_ROWS_FOOD_DEFAULT = 4;
    private int NUMBER_OF_COLUMNS_FOOD_DEFAULT = 3;
    private int NUMBER_OF_ROWS_DRINK_DEFAULT = 5;
    private int NUMBER_OF_COLUMNS_DRINK_DEFAULT = 4;
    private int NUMBER_OF_ROWS_CUSTOMIZATION_DEFAULT = 6;
    private int NUMBER_OF_COLUMNS_CUSTOMIZATION_DEFAULT = 5;

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.i(TAG, "createFragment(int) position: " + position);

        switch (position) {
            case 0:
                return FoodInputFragment.newInstance(NUMBER_OF_ROWS_FOOD_DEFAULT, NUMBER_OF_COLUMNS_FOOD_DEFAULT);
            case 1:
                return DrinkInputFragment.newInstance(NUMBER_OF_ROWS_DRINK_DEFAULT, NUMBER_OF_COLUMNS_DRINK_DEFAULT);
            case 2:
                return CustomizationInputFragment.newInstance(NUMBER_OF_ROWS_CUSTOMIZATION_DEFAULT, NUMBER_OF_COLUMNS_CUSTOMIZATION_DEFAULT);
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return numberOfTabs;
    }
}