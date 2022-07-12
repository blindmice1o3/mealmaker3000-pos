package com.jackingaming.mealmaker3000pos.views.dialogfragments.drinkdecorators.addins.powders;

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

public class AddPowderDialogFragment extends DialogFragment {
    private final String TAG = "AddPowderDialog";
    public static final String CHOCOLATE_MALT = "chocolate malt";
    public static final String VANILLA_BEAN = "vanilla bean";

    public interface OnItemSelectedListener {
        public void onAddPowderItemSelected(String selectedItem);
    }
    private OnItemSelectedListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement AddPowderDialogFragment.OnItemSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialogfragment_addpowder, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonChocolateMalt = view.findViewById(R.id.button_chocolatemalt);
        buttonChocolateMalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "buttonChocolateMalt -> onClick(View)");
                listener.onAddPowderItemSelected(CHOCOLATE_MALT);
                dismiss();
            }
        });

        Button buttonVanillaBean = view.findViewById(R.id.button_vanillabean);
        buttonVanillaBean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "buttonVanillaBean -> onClick(View)");
                listener.onAddPowderItemSelected(VANILLA_BEAN);
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