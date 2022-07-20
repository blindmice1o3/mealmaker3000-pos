package com.jackingaming.mealmaker3000pos.views.tabfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jackingaming.mealmaker3000pos.R;
import com.jackingaming.mealmaker3000pos.views.VerticalTextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab1Fragment extends Fragment {
    private static final String TAB_TITLE = "tab_title";
    private static final String CONTENT = "content";

    private String tabTitle;
    private String content;

    public Tab1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param tabTitle Parameter 1.
     * @param content  Parameter 2.
     * @return A new instance of fragment Tab1Fragment.
     */
    public static Tab1Fragment newInstance(String tabTitle, String content) {
        Tab1Fragment fragment = new Tab1Fragment();
        Bundle args = new Bundle();
        args.putString(TAB_TITLE, tabTitle);
        args.putString(CONTENT, content);
        fragment.setArguments(args);
        return fragment;
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

        TextView tvContent = view.findViewById(R.id.tv_content_tab1);
        tvContent.setText(content);

        VerticalTextView verticalTextView = view.findViewById(R.id.tv_verticaltextview);
        verticalTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "vertical text view clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}