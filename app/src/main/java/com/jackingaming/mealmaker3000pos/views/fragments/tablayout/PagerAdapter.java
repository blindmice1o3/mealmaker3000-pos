package com.jackingaming.mealmaker3000pos.views.fragments.tablayout;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {
    private static final String TAG = "PagerAdapter";
    private String[] tabTitles;
    private String[] contents;

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity, String[] tabTitles, String[] contents) {
        super(fragmentActivity);
        this.tabTitles = tabTitles;
        this.contents = contents;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.i(TAG, "createFragment(int) position: " + position);

        switch (position) {
            case 0:
                return Tab1Fragment.newInstance(tabTitles[position], contents[position]);
            case 1:
                return Tab2Fragment.newInstance(tabTitles[position], contents[position]);
            case 2:
                return Tab3Fragment.newInstance(tabTitles[position], contents[position]);
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return contents.length;
    }
}