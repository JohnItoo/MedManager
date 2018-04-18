package com.ohue.medmanager.main;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;

import android.view.MenuItem;


import itoo.ohue.medmanager.R;

import com.ohue.medmanager.ProfileFragment;
import com.ohue.medmanager.category.CategoryFragment;
import com.ohue.medmanager.category.CategoryPresenter;
import com.ohue.medmanager.home.HomeFragment;
import com.ohue.medmanager.overview.OverviewFragment;
import com.ohue.medmanager.search.SearchFragment;

/**
 * Created by OHUE JOHN on 3/31/2018.
 */

public class MainPresenter implements MainContract.Presenter,NavigationView.OnNavigationItemSelectedListener {

    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        this.view.setNavListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            view.inflateFragment(HomeFragment.getInstance());
            } else if (id == R.id.nav_overview) {
           view.inflateFragment(OverviewFragment.getInstance());
           } else if (id == R.id.nav_search) {
            view.inflateFragment(SearchFragment.getSearchFragmentInstance());
            } else if (id == R.id.nav_profile) {
            view.inflateFragment(ProfileFragment.getInstance());
            }
            else if (id == R.id.nav_category) {
                  view.inflateFragment(CategoryFragment.getCategotryFragmentInstance());
        }


      view.closeDrawer();
        return true;
    }


}
