package com.jackingaming.mealmaker3000pos.views.fragments.staging_two;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jackingaming.mealmaker3000pos.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuItemInputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuItemInputFragment extends Fragment {
    private static final String TAG = "MenuItemInputFragment";

    public interface OnButtonClickListener {
        void onBreadButtonClicked();
        void onWaterButtonClicked();
        void onCustomizationButtonClicked();
    }
    private OnButtonClickListener listener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button buttonBread;
    private Button buttonWater;
    private Button buttonCustomization;

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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnButtonClickListener) {
            listener = (OnButtonClickListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implemenet MenuItemInputFragment.OnButtonClickListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_item_input, container, false);
        buttonBread = view.findViewById(R.id.button_bread);
        buttonWater = view.findViewById(R.id.button_water);
        buttonCustomization = view.findViewById(R.id.button_customization);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonBread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "buttonBread onClick(View)");
                listener.onBreadButtonClicked();
            }
        });

        buttonWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "buttonWater onClick(View)");
                listener.onWaterButtonClicked();
            }
        });

        buttonCustomization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "buttonCustomization onClick(View)");
                listener.onCustomizationButtonClicked();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}