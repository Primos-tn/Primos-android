package pingo.mobile.com.ui.user.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import pingo.mobile.com.R;
import pingo.mobile.com.utils.storage.Preferences;

/**
 * Created by houssem.fathallah on 04/10/2016.
 */
public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_base);
    }
}
