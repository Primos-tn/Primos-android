/**
 * File BrandProfileActivity
 * Project Pingo
 * Created by Tarek .. at 10:09
 * (c) Pingo tn
 * *
 * The base fragment wrapper of application.
 */
package pingo.mobile.com.ui.brands.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;


import pingo.mobile.com.R;
import pingo.mobile.com.ui.brands.fragments.ProductsFragment;
import pingo.mobile.com.ui.brands.fragments.StoresFragment;
import pingo.mobile.com.ui.common.BasePagerAdapter;
import pingo.mobile.com.ui.common.TabLayout;
import pingo.mobile.com.utils.storage.Preferences;


public class BrandProfileActivity extends AppCompatActivity {
    /**
     * Set Content View main_activity.xml
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_profile_base);
    }
}
