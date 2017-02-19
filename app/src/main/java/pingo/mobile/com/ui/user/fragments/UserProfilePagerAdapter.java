/**
 * File HomePagerAdapter
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 * Layout manager that allows the user to flip left and right through pages of data.
 */
package pingo.mobile.com.ui.user.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


import pingo.mobile.com.ui.brands.fragments.MineFragment;
import pingo.mobile.com.ui.common.BasePagerAdapter;

/**
 *
 */
public class UserProfilePagerAdapter extends BasePagerAdapter {
    /**
     *  This will Store the titles of the Tabs which are Going to be passed when HomePagerAdapter is created
     */
    CharSequence titles[];
    /**
     * Store the number of tabs, this will also be passed when the HomePagerAdapter is created
     */
    int numboftabs;
    /**
     * Build a Constructor and assign the passed Values to appropriate values in the class
     */
    public UserProfilePagerAdapter(FragmentManager fm, CharSequence titles[], int numbOfTabsumb) {
        super(fm);
        this.titles = titles;
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
                return new MineFragment();
            case 1:
                return new MineFragment();
           default:
               return null;

        }

    }


    /**
     *
     * @param position
     * @return  the titles for the Tabs in the Tab Strip
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    /**
     *
     * @return This method return the Number of tabs for the tabs Strip
     */
    @Override
    public int getCount() {
        return numboftabs;
    }

    @Override
    public int getDrawableId(int i) {
        return 0;
    }

}