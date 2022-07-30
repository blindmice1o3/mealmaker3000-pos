package com.jackingaming.mealmaker3000pos.views.fragments.tablayout;

import android.util.Log;

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

    private int NUMBER_OF_ROWS_FOODS_DEFAULT = 4;
    private int NUMBER_OF_COLUMNS_FOODS_DEFAULT = 3;
    private int NUMBER_OF_ROWS_DRINKS_DEFAULT = 5;
    private int NUMBER_OF_COLUMNS_DRINKS_DEFAULT = 5;
    private int NUMBER_OF_ROWS_SIDES_DEFAULT = 2;
    private int NUMBER_OF_COLUMNS_SIDES_DEFAULT = 3;

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.i(TAG, "createFragment(int) position: " + position);

        switch (position) {
            case 0:
                return FoodsInputFragment.newInstance(NUMBER_OF_ROWS_FOODS_DEFAULT, NUMBER_OF_COLUMNS_FOODS_DEFAULT);
            case 1:
                return DrinksInputFragment.newInstance(NUMBER_OF_ROWS_DRINKS_DEFAULT, NUMBER_OF_COLUMNS_DRINKS_DEFAULT);
            case 2:
                return SidesInputFragment.newInstance(NUMBER_OF_ROWS_SIDES_DEFAULT, NUMBER_OF_COLUMNS_SIDES_DEFAULT);
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return numberOfTabs;
    }
}