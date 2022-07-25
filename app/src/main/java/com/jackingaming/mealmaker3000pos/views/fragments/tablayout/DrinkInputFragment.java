package com.jackingaming.mealmaker3000pos.views.fragments.tablayout;

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
 * Use the {@link DrinkInputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrinkInputFragment extends Fragment {
    private static final String TAG = "Tab2Fragment";

    public interface ClickListener {
        void onWaterButtonClicked();
    }
    private ClickListener clickListener;

    private Button buttonWater;

    public DrinkInputFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     * @return A new instance of fragment Tab2Fragment.
     */
    public static DrinkInputFragment newInstance() {
        return new DrinkInputFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ClickListener) {
            clickListener = (ClickListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement ClickListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drink_input, container, false);
        buttonWater = view.findViewById(R.id.button_water);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "buttonWater onClick(View)");
                clickListener.onWaterButtonClicked();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clickListener = null;
    }
}