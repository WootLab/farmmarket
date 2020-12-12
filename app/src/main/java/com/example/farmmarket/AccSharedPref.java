package com.example.farmmarket;

import android.content.Context;
import android.preference.PreferenceManager;

public class AccSharedPref {
    private static final String PREF_EMAIL = "";

    public static String getStoredEmail(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_EMAIL, null);
    }

    public static void setStoredEmail(Context context, String query) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_EMAIL, query)
                .apply();
    }
}
