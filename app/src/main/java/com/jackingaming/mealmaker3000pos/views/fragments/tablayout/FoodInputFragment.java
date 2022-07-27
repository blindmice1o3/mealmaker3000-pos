package com.jackingaming.mealmaker3000pos.views.fragments.tablayout;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.TypedValue;
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

    private static final String ARG_NUMBER_OF_ROWS = "number of rows";
    private static final String ARG_NUMBER_OF_COLUMNS = "number of columns";

    private int numberOfRows;
    private int numberOfColumns;

    public interface ClickListener {
        void onBreadButtonClicked();
    }

    private ClickListener clickListener;

    public FoodInputFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param numberOfRows    Parameter 1.
     * @param numberOfColumns Parameter 2.
     * @return A new instance of fragment Tab1Fragment.
     */
    public static FoodInputFragment newInstance(int numberOfRows, int numberOfColumns) {
        FoodInputFragment fragment = new FoodInputFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_NUMBER_OF_ROWS, numberOfRows);
        args.putInt(ARG_NUMBER_OF_COLUMNS, numberOfColumns);
        fragment.setArguments(args);
        return fragment;
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
        if (getArguments() != null) {
            numberOfRows = getArguments().getInt(ARG_NUMBER_OF_ROWS);
            numberOfColumns = getArguments().getInt(ARG_NUMBER_OF_COLUMNS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_input, container, false);
        ConstraintLayout constraintLayout = view.findViewById(R.id.constraintlayout_food_input);
        Button[][] buttons = new Button[numberOfRows][numberOfColumns];
        View buttonPrevious = null;
        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfColumns; column++) {
                // Define the new Button and add it to the ConstraintLayout.
                // Without constraints, this view will be positioned at (0,0).
                Button buttonNew = new Button(getContext());
                buttonNew.setId(View.generateViewId());
                buttonNew.setText("(row:" + row + "|\ncolumn: " + column + ")");
                buttonNew.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12.0f);
                buttons[row][column] = buttonNew;

                float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics());
                float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics());
                ConstraintLayout.LayoutParams layoutParams =
                        new ConstraintLayout.LayoutParams(
                                (int) width,
                                (int) height);
                constraintLayout.addView(buttonNew, layoutParams);

                // Move the new view into place by applying constraints.
                ConstraintSet constraintSet = new ConstraintSet();
                // Get existing constraints. This will be the base for modification.
                constraintSet.clone(constraintLayout);
                // Set up the connections for the new view. Constrain its top to the bottom of the top view.
                // VERTICAL CONSTRAINTS
                if (row == 0) { // first row
                    constraintSet.connect(buttonNew.getId(), ConstraintSet.TOP,
                            constraintLayout.getId(), ConstraintSet.TOP);
                } else { // not first row
                    if (row == numberOfRows - 1) { // last row
                        constraintSet.connect(buttons[row - 1][column].getId(), ConstraintSet.BOTTOM,
                                buttonNew.getId(), ConstraintSet.TOP);
                        constraintSet.connect(buttonNew.getId(), ConstraintSet.TOP,
                                buttons[row - 1][column].getId(), ConstraintSet.BOTTOM);
                        constraintSet.connect(buttonNew.getId(), ConstraintSet.BOTTOM,
                                constraintLayout.getId(), ConstraintSet.BOTTOM);
                    } else { // not last row (aka middle rows)
                        constraintSet.connect(buttons[row - 1][column].getId(), ConstraintSet.BOTTOM,
                                buttonNew.getId(), ConstraintSet.TOP);
                        constraintSet.connect(buttonNew.getId(), ConstraintSet.TOP,
                                buttons[row - 1][column].getId(), ConstraintSet.BOTTOM);
                    }
                }

                // HORIZONTAL CONSTRAINTS
                if (buttonPrevious == null) { // first column
                    Log.d(TAG, "buttonPrevious == null [FIRST ELEMENT IN ROW] column: " + column);
                    constraintSet.connect(buttonNew.getId(), ConstraintSet.LEFT,
                            constraintLayout.getId(), ConstraintSet.LEFT);
                } else { // not first column
                    Log.d(TAG, "buttonPrevious != null [not FIRST ELEMENT IN ROW] column: " + column);
                    if (column == numberOfColumns - 1) { // last column
                        constraintSet.connect(buttons[row][column - 1].getId(), ConstraintSet.RIGHT,
                                buttonNew.getId(), ConstraintSet.LEFT);
                        constraintSet.connect(buttonNew.getId(), ConstraintSet.LEFT,
                                buttons[row][column - 1].getId(), ConstraintSet.RIGHT);
                        constraintSet.connect(buttonNew.getId(), ConstraintSet.RIGHT,
                                constraintLayout.getId(), ConstraintSet.RIGHT);
                    } else { // not last column (aka middle columns)
                        constraintSet.connect(buttons[row][column - 1].getId(), ConstraintSet.RIGHT,
                                buttonNew.getId(), ConstraintSet.LEFT);
                        constraintSet.connect(buttonNew.getId(), ConstraintSet.LEFT,
                                buttons[row][column - 1].getId(), ConstraintSet.RIGHT);
                    }
                }

                // Finally, apply our good work to the layout.
                constraintSet.applyTo(constraintLayout);
                buttonPrevious = buttonNew;

                // last column
                if (column == numberOfColumns - 1) {
                    buttonPrevious = null;
                }
            }
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clickListener = null;
    }
}