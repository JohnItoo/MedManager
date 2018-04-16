package com.ohue.medmanager.search;


import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ohue.medmanager.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import itoo.ohue.medmanager.R;

import com.ohue.medmanager.overview.OverviewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment<SearchContract.Presenter> implements
                SearchContract.VIew {
    @BindView(R.id.search_recycler_view)
    RecyclerView searchRecyclerView;
    @BindView(R.id.search_layout)
    LinearLayout searchLayout;
    @BindView(R.id.med_name_edit)
    EditText medNameQuery;
    LinearLayoutManager linearLayoutManager;
    Parcelable listState;
    public static final String LIST_STATE_KEY = "recycler_list_state";
    private OverviewAdapter searchAdapter; //Present implementation of OverviewAdapter suits Search
    private static SearchFragment searchFragmentInstance = null;
    private  SearchPresenter presenter;

    public static SearchFragment getSearchFragmentInstance() {
        if(searchFragmentInstance == null){
            searchFragmentInstance = new SearchFragment();
            return searchFragmentInstance;
        }
        return  searchFragmentInstance;
    }

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_search, container, false);
       ButterKnife.bind(this, view);
       presenter =  new SearchPresenter(getActivity(), this);
       searchAdapter = new OverviewAdapter(getActivity());
        linearLayoutManager =  new LinearLayoutManager(getActivity());
        searchRecyclerView.setLayoutManager(linearLayoutManager);
        searchRecyclerView.setHasFixedSize(true);
        searchRecyclerView.setAdapter(searchAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        listState = linearLayoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, listState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            savedInstanceState.getParcelable(LIST_STATE_KEY);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (listState != null) {
            linearLayoutManager.onRestoreInstanceState(listState);
        }
    }

    @Override
    public void showSearchResults(Cursor cursor) {
            searchAdapter.swapCursor(cursor);
            if(searchLayout.getVisibility() == View.GONE) {
                searchLayout.setVisibility(View.VISIBLE);
            }
    }

    @OnClick(R.id.search_button)
    public void searchMedWithName() {
        presenter.search(medNameQuery.getText().toString());
    }

    @OnClick(R.id.reset_search)
    public void resetSearchLayout() {
        searchLayout.setVisibility(View.GONE);
    }

}