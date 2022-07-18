package com.jackingaming.mealmaker3000pos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.jackingaming.mealmaker3000pos.views.tabfragments.PagerAdapter;

public class TabExperimentActivity extends AppCompatActivity {
    private static final String TAG = "TabExperimentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_experiment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create an instance of the tab layout from the view.
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        // Set the text for each tab.
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab1_label));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab2_label));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab3_label));
        // Set the tabs to fill the entire layout.
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        // Use PagerAdapter to manage page views in fragments.
        // Each page is represented by its own fragment.
        final ViewPager2 viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        // Setting a listener for clicks.
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                Log.i(TAG, "viewPager onPageScrolled(int, float, int)");
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.i(TAG, "viewPager onPageSelected(int)");
                TabLayout.Tab tab = tabLayout.getTabAt(position);
                tab.select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                Log.i(TAG, "viewPager onPageScrollStateChanged(int)");
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i(TAG, "tabLayout onTabSelected(TabLayout.Tab)");
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i(TAG, "tabLayout onTabUnselected(TabLayout.Tab)");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i(TAG, "tabLayout onTabReselected(TabLayout.Tab)");
            }
        });
    }
}