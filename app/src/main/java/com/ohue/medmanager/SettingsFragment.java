package com.ohue.medmanager;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import itoo.ohue.medmanager.R;

/**
 * Created by OHUE JOHN on 3/31/2018.
 */

public class SettingsFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.user_preferences);
    }
}
