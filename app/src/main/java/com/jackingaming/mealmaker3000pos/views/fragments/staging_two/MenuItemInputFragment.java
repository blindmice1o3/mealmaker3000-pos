package com.jackingaming.mealmaker3000pos.views.fragments.staging_two;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jackingaming.mealmaker3000pos.R;
import com.jackingaming.mealmaker3000pos.views.fragments.tablayout.FoodInputFragment;
import com.jackingaming.mealmaker3000pos.views.fragments.tablayout.PagerAdapter;
import com.jackingaming.mealmaker3000pos.views.fragments.tablayout.VerticalTextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuItemInputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuItemInputFragment extends Fragment {
    private static final String TAG = "MenuItemInputFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private boolean swipeable;
    private String[] tabTitles;
    private PagerAdapter pagerAdapter;
    private TabLayoutMediator tabLayoutMediator;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private VerticalTextView verticalTextView;

    public MenuItemInputFragment() {
        // Required empty public constructor
        Log.i(TAG, "MenuItemInputFragment() empty public constructor");
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuItemInputFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuItemInputFragment newInstance(String param1, String param2) {
        Log.i(TAG, "MenuItemInputFragment newInstance(String, String)");
        MenuItemInputFragment fragment = new MenuItemInputFragment();
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
        swipeable = true;
        tabTitles = getResources().getStringArray(R.array.tab_meal_staging);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_item_input, container, false);
        tabLayout = view.findViewById(R.id.tablayout);
        viewPager2 = view.findViewById(R.id.viewpager2);
        verticalTextView = view.findViewById(R.id.tv_verticaltextview);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Create an instance of the tab layout from the view.
        for (int i = 0; i < tabTitles.length; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }
        // Set the tabs to fill the entire layout.
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        // Use PagerAdapter to manage page views in fragments.
        // Each page is represented by its own fragment.
        pagerAdapter = new PagerAdapter(getActivity(), tabTitles.length);
        viewPager2.setAdapter(pagerAdapter);
        viewPager2.setUserInputEnabled(false);

        tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTitles[position]);
            }
        });
        tabLayoutMediator.attach();

        verticalTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "vertical text view clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean isSwipeable() {
        return swipeable;
    }

    public void setSwipeable(boolean swipeable) {
        this.swipeable = swipeable;

        viewPager2.setUserInputEnabled(swipeable);
    }
}