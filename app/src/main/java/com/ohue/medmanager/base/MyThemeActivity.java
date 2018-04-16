package com.ohue.medmanager.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import itoo.ohue.medmanager.R;

/**
 * Created by OHUE JOHN on 3/31/2018.
 */

public abstract  class MyThemeActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    boolean darkTheme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(this);
        sharedPref.registerOnSharedPreferenceChangeListener(this);
        darkTheme = sharedPref.getBoolean(getString(R.string.key_dark_theme),false);
        setTheme(darkTheme? R.style.darkTheme:R.style.lightTheme);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        recreate();
    }

    @Override
    protected void onDestroy() {
        android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }
}
