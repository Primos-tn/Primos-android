/**
 * File BrandProfileActivity
 * Project Pingo
 * Created by Tarek .. at 10:09
 * (c) Pingo tn
 * *
 * The base fragment wrapper of application.
 */
package pingo.mobile.com.ui.products.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import pingo.mobile.com.R;


public class ProductDetailsActivity extends AppCompatActivity {
    /**
     * Set Content View main_activity.xml
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_profile_base);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
