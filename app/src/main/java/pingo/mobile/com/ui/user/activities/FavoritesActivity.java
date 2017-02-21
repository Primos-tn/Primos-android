package pingo.mobile.com.ui.user.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import pingo.mobile.com.R;

/**
 * Created by houssem.fathallah on 04/10/2016.
 */
public class FavoritesActivity extends AppCompatActivity {
    /**
     * Set Content View main_activity.xml
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_brands);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}