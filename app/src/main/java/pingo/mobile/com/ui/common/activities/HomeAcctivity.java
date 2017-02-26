package pingo.mobile.com.ui.common.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import pingo.mobile.com.R;

public class HomeAcctivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

///**
// * File HomeActivity
// * Project Pingo
// * Created by Pingo Team
// * (c) Pingo tn
// * This activity inflate menu and set content view to main_activity.xml
// */
//package pingo.mobile.com.activities;
//
//import android.Manifest;
//import android.app.SearchManager;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.os.Build;
//import android.support.v4.app.ActivityCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.SearchView;
//import android.telephony.TelephonyManager;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.Window;
//import android.view.WindowManager;
//
//import pingo.mobile.com.R;
//import pingo.mobile.com.utils.constants.Permissions;
//import pingo.mobile.com.utils.fcm.PingoFireBaseInstanceIdService;
//import pingo.mobile.com.utils.storage.Preferences;
//
//import static pingo.mobile.com.utils.constants.Bundles.PASS_PUSH_KEY_TOKEN;
//
//
//public class HomeAcctivity extends AppCompatActivity {
//    /**
//     * Set Content View main_activity.xml
//     */
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main_activity);
//        Window window = getWindow();
//
//        window.setSoftInputMode(
//                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//
//// finally change the color
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//// clear FLAG_TRANSLUCENT_STATUS flag:
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//
//// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(getApplicationContext().getResources().getColor(R.color.ColorPrimary));
//        }
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Preferences.getInstance(this).setLasActiveActivity(getClass().getName());
//    }
//
//    /**
//     * Inflate the menu; this adds items to the action bar if it is present.
//     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        // Inflate menu to add items to action bar if it is present.
//        inflater.inflate(R.menu.menu_home, menu);
//        // Associate searchable menu_home with the SearchView
//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView =
//                (SearchView) menu.findItem(R.id.menu_search).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));
//
//        return true;
//    }
//
//    /**
//     * Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button
//     */
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//}
