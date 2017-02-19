package pingo.mobile.com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pingo.mobile.com.utils.storage.Preferences;

/**
 * Created by houssem.fathallah on 05/10/2016.
 */
public class SplashScreenActivity extends AppCompatActivity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Class<?> activityClass;
        try {
            Preferences prefs = Preferences.getInstance(getApplicationContext());
            activityClass = Class.forName(prefs.getLasActiveActivity());
        } catch(ClassNotFoundException | ClassCastException ex) {
            activityClass = TourActivity.class;
        }
        Intent i = new Intent(SplashScreenActivity.this, activityClass);

        startActivity(i);
        //close this activity
        finish();
    }
}
