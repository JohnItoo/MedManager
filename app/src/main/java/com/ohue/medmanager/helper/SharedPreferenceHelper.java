package com.ohue.medmanager.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SharedPreferenceHelper {

    public void saveName (Context context, String firstName , String lastName) {
        SharedPreferences.Editor editor = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE).edit();
        editor.putString("firstName", firstName);
        editor.putString("lastName", lastName);
        editor.apply();
    }

    public HashMap<String, String> getNames(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
       String firstName = preferences.getString("firstName", " Null");
       String lastName = preferences.getString("lastName", "Null");
        HashMap<String, String> mapNames = new HashMap<>();
        mapNames.put("firstName", firstName);
        mapNames.put("lastName", lastName);
        return mapNames;
    }
}