/**
 * File HomePagerAdapter
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 * Layout manager that allows the user to flip left and right through pages of data.
 */
package pingo.mobile.com.ui.brands;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import pingo.mobile.com.R;
import pingo.mobile.com.ui.brands.fragments.InfoFragment;
import pingo.mobile.com.ui.brands.fragments.ProductsFragment;
import pingo.mobile.com.ui.brands.fragments.StoresFragment;
import pingo.mobile.com.ui.common.BasePagerAdapter;
import pingo.mobile.com.utils.constants.Bundles;

/**
 *
 */
public class BrandProfilePagerAdapter extends BasePagerAdapter {
    private final int brandId;
    final int[] listTabsIcons= {
            R.drawable.ic_note_white,
            R.drawable.ic_products_white,
            R.drawable.ic_pins_white,
            R.drawable.ic_network_white,
            R.drawable.ic_comment_white
    };
    /**
     *
     */
    final int[] listTabsIconsOff  = {
            R.drawable.ic_note_black,
            R.drawable.ic_products_black,
            R.drawable.ic_pins_black,
            R.drawable.ic_network_black,
            R.drawable.ic_comment_black
    };

    final int[] tabTitles = {
            R.string.brand_profile_tab_info,
            R.string.brand_profile_tab_products,
            R.string.brand_profile_tab_locations
    };


    /**
     * Build a Constructor and assign the passed Values to appropriate values in the class
     */
    public BrandProfilePagerAdapter(int brandId, FragmentManager fm) {
        super(fm);
        this.brandId = brandId;

    }

    /**
     * @param position
     * @return Fragment for each position
     */
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new InfoFragment();
                break;
            case 1:
                fragment = new ProductsFragment();
                break;
            case 2:
                fragment = new StoresFragment();
                break;
            case 3:
                fragment = new StoresFragment();
                break;
            case 4:
                fragment = new StoresFragment();
                break;
            default:
                return null;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(Bundles.OPENED_BRAND_ID, brandId);
        fragment.setArguments(bundle);
        return fragment;
    }


    /**
     * @return This method return the Number of tabs for the tabs Strip
     */
    @Override
    public int getCount() {
        return 5;
    }

    /**
     *
     * @param context
     * @param position
     * @return
     */
    public View getTabView(Context context, int position) {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        View v = LayoutInflater.from(context).inflate(R.layout.custom_tab_view, null);
        /*TextView tv = (TextView) v.findViewById(R.id.textView);
        tv.setText(context.getResources().getString(tabTitles[position]));*/
        ImageView img = (ImageView) v.findViewById(R.id.imgView);
        img.setImageResource(listTabsIcons[position]);
        return v;
    }

    public int getIconOff(int index) {
        return listTabsIconsOff[index];
    }

    public int getIconOn(int index) {
        return listTabsIcons[index];
    }
}