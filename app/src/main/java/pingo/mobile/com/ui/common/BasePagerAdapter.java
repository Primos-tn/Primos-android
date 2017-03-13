package pingo.mobile.com.ui.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by houssem.fathallah on 18/09/2016.
 */
public abstract class BasePagerAdapter extends FragmentPagerAdapter {
    public BasePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

}
