/**
 * File HomePagerAdapter
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 * Layout manager that allows the user to flip left and right through pages of data.
 */
package pingo.mobile.com.ui.brands;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import pingo.mobile.com.R;
import pingo.mobile.com.api.models.Brand;
import pingo.mobile.com.ui.brands.fragments.InfoFragment;
import pingo.mobile.com.ui.brands.fragments.ProductsFragment;
import pingo.mobile.com.ui.brands.fragments.StoresFragment;
import pingo.mobile.com.ui.common.BasePagerAdapter;
import pingo.mobile.com.utils.constants.Bundles;

/**
 *
 */
public class ProfilePagerAdapter extends BasePagerAdapter {
    private final int brandId;
    /**
     * This will Store the titles of the Tabs which are Going to be passed when HomePagerAdapter is created
     */
    CharSequence titles[];
    /**
     * Store the number of tabs, this will also be passed when the HomePagerAdapter is created
     */
    int numboftabs;
    private int[] drawables = {
            R.drawable.ic_add,
            R.drawable.ic_bubble_white,
            R.drawable.ic_map_white
    };
    private int[] drawablesOff = {
            R.drawable.ic_add,
            R.drawable.ic_bubble_black,
            R.drawable.ic_map
    };

    /**
     * Build a Constructor and assign the passed Values to appropriate values in the class
     */
    public ProfilePagerAdapter(int brandId, FragmentManager fm, CharSequence titles[]) {
        super(fm);
        this.titles = titles;
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
            default:
                return null;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(Bundles.OPENED_BRAND_ID, brandId);
        fragment.setArguments(bundle);
        return fragment;
    }


    /**
     * @param position
     * @return the titles for the Tabs in the Tab Strip
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    /**
     * @return This method return the Number of tabs for the tabs Strip
     */
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public int getDrawableId(int position) {
        return drawables[position];
    }

    @Override
    public int getDrawableOffId(int i) {
        return drawablesOff[i];
    }


}