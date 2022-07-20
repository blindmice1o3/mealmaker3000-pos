package com.jackingaming.mealmaker3000pos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jackingaming.mealmaker3000pos.views.VerticalTextView;
import com.jackingaming.mealmaker3000pos.views.tabfragments.PagerAdapter;

public class TabExperimentActivity extends AppCompatActivity {
    private static final String TAG = "TabExperimentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_experiment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] tabTitles = getResources().getStringArray(R.array.tab_titles);
        String[] contents = getResources().getStringArray(R.array.contents);
        // Create an instance of the tab layout from the view.
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        for (int i = 0; i < tabTitles.length; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }
        // Set the tabs to fill the entire layout.
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        // Use PagerAdapter to manage page views in fragments.
        // Each page is represented by its own fragment.
        final ViewPager2 viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(this, tabTitles, contents);
        viewPager.setAdapter(adapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTitles[position]);
            }
        });
        tabLayoutMediator.attach();

        VerticalTextView verticalTextView = findViewById(R.id.tv_verticaltextview);
        verticalTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TabExperimentActivity.this, "vertical text view clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}