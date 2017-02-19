/**
 * File BrandProfileActivity
 * Project Pingo
 * Created by Tarek .. at 10:09
 * (c) Pingo tn
 * *
 * The base fragment wrapper of application.
 */
package pingo.mobile.com.ui.user.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pingo.mobile.com.R;
import pingo.mobile.com.api.models.BrandShortInfo;
import pingo.mobile.com.ui.common.TabLayout;


public class ContainerFragment extends Fragment implements View.OnClickListener {
    /**
     *Setter of base fragment toolbar.
     */
    ViewPager pager;
    UserProfilePagerAdapter adapter;
    TabLayout tabs;
    CharSequence Titles[] = {"Brands", "Wishes"};
    /**
     * Defines the total number of tabs.
     */
    int numberOfTabs = 2;

    /**
     * Default oncerateView function.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_profile_base_fragment, container, false);
        /**
         * Creating The HomePagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
         */

        adapter = new UserProfilePagerAdapter(getActivity().getSupportFragmentManager(), Titles, numberOfTabs);
        /**
         * Assigning ViewPager View and setting the adapter
         */
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(adapter);


        /**
         * Assiging the Sliding Tab Layout View
         */
        tabs = (TabLayout) view.findViewById(R.id.tabs);
        /**
         * To make the Tabs Fixed , This makes the tabs Space Evenly in Available width
         */
        tabs.setDistributeEvenly(true);
        /**
         * Setting Custom Color for the Scroll bar indicator of the Tab View
         */
        tabs.setCustomTabColorizer(new TabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.ColorPrimary);
            }
        });
        tabs.setViewPager(pager, TabLayout.ICON_MODE);

        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        HeaderFragment headerFragment = (HeaderFragment) getChildFragmentManager().findFragmentById(R.id.brand_profile_header_fragment);
        //headerFragment.setBrand(new BrandShortInfo());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
