/**
 * File SettingsActivity
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 *  Simple Activity to display example preferences.
 */
package pingo.mobile.com.ui.user.activities;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import pingo.mobile.com.R;


public class SettingsActivity extends Activity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(android.R.id.content, new SettingsFragment())
                    .commit();
        }
    }

    /**
     *
     */
    public static class SettingsFragment extends PreferenceFragment {
        @Override public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.prefs);
        }
    }
}