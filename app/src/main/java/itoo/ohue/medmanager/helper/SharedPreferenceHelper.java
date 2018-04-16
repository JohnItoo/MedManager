package itoo.ohue.medmanager.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {

    public void saveName (Context context, String firstName , String lastName) {
        SharedPreferences.Editor editor = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE).edit();
        editor.putString("firstName", firstName);
        editor.putString("lastName", lastName);
        editor.apply();
    }
}