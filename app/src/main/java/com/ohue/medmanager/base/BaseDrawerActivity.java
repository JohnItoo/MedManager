package com.ohue.medmanager.base;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.ohue.medmanager.SettingsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.ohue.medmanager.helper.Instantiater;
import com.ohue.medmanager.home.HomeFragment;
import com.ohue.medmanager.main.MainContract;
import itoo.ohue.medmanager.R;
import com.ohue.medmanager.main.MainPresenter;

public class BaseDrawerActivity extends BaseActivity<MainContract.Presenter>
        implements MainContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView (R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    private NavigationView.OnNavigationItemSelectedListener navListener;
    MainContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        setUpUnbinder(ButterKnife.bind(this));
        setSupportActionBar(toolbar);
        new MainPresenter(this);
        inflateFragment(HomeFragment.getInstance());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(navListener);

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(BaseDrawerActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void makeToast(int messageRes) {
        makeToast(getString(messageRes));
    }

    @Override
    public void showEditTextError(String message) {

    }

    @Override
    public void showEditTextError(int message) {

    }

    @Override
    public void setToolbarTitle(int message) {
        if(getSupportActionBar()!= null) {
            getSupportActionBar().setTitle(message);
        }
    }


    @Override
    public void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void inflateFragment(android.support.v4.app.Fragment ft) {

        getSupportFragmentManager().beginTransaction().replace(R.id.view_container ,ft ).commit();


    }

    @Override
    public void setNavListener(NavigationView.OnNavigationItemSelectedListener navListener) {
        this.navListener = navListener;
    }

    @Override
    protected void onDestroy() {
        Instantiater.destroyDbOPs();
        super.onDestroy();
    }
}
