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
import android.widget.TextView;

import com.jackingaming.mealmaker3000pos.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab3Fragment extends Fragment {
    private static final String TAG = "Tab3Fragment";
    private static final String TAB_TITLE = "tab_title";
    private static final String CONTENT = "content";

    public interface ClickListener {
        void onCustomizationButtonClicked();
    }
    private ClickListener clickListener;

    private String tabTitle;
    private String content;

    private TextView tvContent;
    private Button buttonCustomization;

    public Tab3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param tabTitle Parameter 1.
     * @param content Parameter 2.
     * @return A new instance of fragment Tab3Fragment.
     */
    public static Tab3Fragment newInstance(String tabTitle, String content) {
        Tab3Fragment fragment = new Tab3Fragment();
        Bundle args = new Bundle();
        args.putString(TAB_TITLE, tabTitle);
        args.putString(CONTENT, content);
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
            tabTitle = getArguments().getString(TAB_TITLE);
            content = getArguments().getString(CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);
        tvContent = view.findViewById(R.id.tv_content_tab3);
        buttonCustomization = view.findViewById(R.id.button_customization);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvContent.setText(content);

        buttonCustomization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "buttonCustomization onClick(View)");
                clickListener.onCustomizationButtonClicked();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clickListener = null;
    }
}