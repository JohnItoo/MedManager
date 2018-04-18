package com.ohue.medmanager.category;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.ohue.medmanager.base.BaseFragment;
import com.ohue.medmanager.base.BaseView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itoo.ohue.medmanager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseFragment<CategoryContract.Presenter>
                implements CategoryContract.View {
//    @BindView(R.id.category_expand_list)
    ExpandableListView categoryExpandList;
 private static CategoryFragment instance = null;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader = new ArrayList<>();
    HashMap<String, Cursor> listDataChild;
    CategoryPresenter presenter;




    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment getCategotryFragmentInstance() {
        if (instance == null) {
            instance = new CategoryFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        presenter= new CategoryPresenter(getActivity());
        listDataHeader = Arrays.asList(getResources().getStringArray(R.array.month_array));
        listDataChild = new HashMap<>();
        for (int i = 0; i<listDataHeader.size(); i++) {
            listDataChild.put(listDataHeader.get(i), presenter.getMedsForMonth(i+1));
        }
       expListView= (ExpandableListView) view.findViewById(R.id.category_expand_list);
        listAdapter = new CategoryExpandableAdapter(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        return view;
    }

}
