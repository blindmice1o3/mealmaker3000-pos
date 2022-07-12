package com.jackingaming.mealmaker3000pos.views.dialogfragments.drinkdecorators.addins.linethecup;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.jackingaming.mealmaker3000pos.R;

public class LineTheCupDialogFragment extends DialogFragment {
    private final String TAG = "LineTheCupDialog";
    public static final String CARAMEL = "caramel";
    public static final String MOCHA = "mocha";
    public static final String NOTHING = "nothing";

    public interface OnItemSelectedListener {
        public void onLineTheCupItemSelected(String selectedItem);
    }
    private OnItemSelectedListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement LineTheCupDialogFragment.OnItemSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialogfragment_linethecup, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonCaramel = view.findViewById(R.id.button_caramel);
        buttonCaramel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "buttonCaramel -> onClick(View)");
                listener.onLineTheCupItemSelected(CARAMEL);
                dismiss();
            }
        });

        Button buttonMocha = view.findViewById(R.id.button_mocha);
        buttonMocha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "buttonMocha -> onClick(View)");
                listener.onLineTheCupItemSelected(MOCHA);
                dismiss();
            }
        });

        Button buttonNothing = view.findViewById(R.id.button_nothing);
        buttonNothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "buttonNothing -> onClick(View)");
                listener.onLineTheCupItemSelected(NOTHING);
                dismiss();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}