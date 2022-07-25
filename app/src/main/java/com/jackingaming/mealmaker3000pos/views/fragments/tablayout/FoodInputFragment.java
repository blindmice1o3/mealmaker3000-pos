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
 * Use the {@link FoodInputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodInputFragment extends Fragment {
    private static final String TAG = "Tab1Fragment";

    public interface ClickListener {
        void onBreadButtonClicked();
    }
    private ClickListener clickListener;

    private Button buttonBread;

    public FoodInputFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     * @return A new instance of fragment Tab1Fragment.
     */
    public static FoodInputFragment newInstance() {
        return new FoodInputFragment();
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
        View view = inflater.inflate(R.layout.fragment_food_input, container, false);
        buttonBread = view.findViewById(R.id.button_bread);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonBread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "buttonBread onClick(View)");
                clickListener.onBreadButtonClicked();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clickListener = null;
    }
}