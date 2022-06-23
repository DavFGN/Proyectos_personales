package com.example.david.ejerecup;

import android.content.Intent;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

public class Preferencias extends PreferenceActivity {

    @Override protected void onCreate(final Bundle savedInstanceState)
    { super.onCreate(savedInstanceState);
    getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()) .commit();
    }

    public static class MyPreferenceFragment extends PreferenceFragment {
        @Override public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_main);
           } }
}
