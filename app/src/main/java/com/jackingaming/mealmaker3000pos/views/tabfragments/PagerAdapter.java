package com.jackingaming.mealmaker3000pos.views.tabfragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {

    private int numOfTabs;

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity, int numOfTabs) {
        super(fragmentActivity);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Tab1Fragment();
            case 1:
                return new Tab2Fragment();
            case 2:
                return new Tab3Fragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return numOfTabs;
    }
}
