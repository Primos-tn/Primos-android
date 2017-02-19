/**
 * File HomePagerAdapter
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 * Layout manager that allows the user to flip left and right through pages of data.
 */
package pingo.mobile.com.ui.brands.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import pingo.mobile.com.R;
import pingo.mobile.com.ui.common.BasePagerAdapter;

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

    /**
     * Build a Constructor and assign the passed Values to appropriate values in the class
     */
    public ProfilePagerAdapter(int brandId, FragmentManager fm, CharSequence titles[], int numbOfTabsumb) {
        super(fm);
        this.titles = titles;
        this.brandId = brandId;
        this.numboftabs = numbOfTabsumb;

    }

    /**
     * @param position
     * @return Fragment for each position
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new InfoFragment();
            case 1:
                return new ProductsFragment();
            case 2:
                return new StoresFragment();
            default:
                return null;

        }

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
        return numboftabs;
    }

    @Override
    public int getDrawableId(int i) {
        return R.drawable.ic_favorite;
    }

}