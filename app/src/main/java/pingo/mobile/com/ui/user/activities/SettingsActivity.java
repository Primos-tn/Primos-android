/**
 * File SettingsActivity
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 * Simple Activity to display example preferences.
 */
package pingo.mobile.com.ui.user.activities;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;

import pingo.mobile.com.R;


/**
 *
 */
public  class SettingsActivity extends AppCompatActivity {

    /**
     * Populate the activity with the top-level headers.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PrefsFragment()).commit();
    }
    public static class PrefsFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.prefs);
        }
    }
}
