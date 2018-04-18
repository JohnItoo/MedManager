package com.ohue.medmanager.overview;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ohue.medmanager.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import itoo.ohue.medmanager.R;

public class OverviewFragment extends BaseFragment<OverviewContract.Presenter> implements OverviewContract.View {
    @BindView(R.id.empty_meds_layout)
    LinearLayout emptyLayout;
    @BindView(R.id.med_recycler_view)
    RecyclerView medRecyclerView;
    LinearLayoutManager linearLayoutManager;
    Parcelable listState;
    public static final String LIST_STATE_KEY = "recycler_list_state";
    OverviewAdapter overviewAdapter;
    OverviewPresenter presenter;
    public  static OverviewFragment overviewFragmentInstance = null;



    public static OverviewFragment getInstance() {
        if(overviewFragmentInstance == null){
            overviewFragmentInstance = new OverviewFragment();
            return overviewFragmentInstance;
        }
        return overviewFragmentInstance;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reminder , container , false);
        ButterKnife.bind(this , view);
        presenter = new OverviewPresenter(getActivity() );
        overviewAdapter = new OverviewAdapter(getActivity() , presenter.getAllMeds());
        linearLayoutManager =  new LinearLayoutManager(getActivity());
      medRecyclerView.setLayoutManager(linearLayoutManager);
      medRecyclerView.setHasFixedSize(true);
        medRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
       medRecyclerView.setAdapter(overviewAdapter);

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
        overviewAdapter.swapCursor(presenter.getAllMeds());
        initViews();
    }

    public void  initViews() {
        if(presenter.getAllMeds().getCount() >0) {
            emptyLayout.setVisibility(View.GONE);
        }
        else{
            emptyLayout.setVisibility(View.VISIBLE);
        }

        setToolbarTitle(R.string.overview_nav);
    }
}
