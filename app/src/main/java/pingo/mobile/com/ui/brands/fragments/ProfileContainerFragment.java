/**
 * File BrandProfileActivity
 * Project Pingo
 * Created by Tarek .. at 10:09
 * (c) Pingo tn
 * *
 * The base fragment wrapper of application.
 */
package pingo.mobile.com.ui.brands.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pingo.mobile.com.R;
import pingo.mobile.com.api.models.Brand;
import pingo.mobile.com.stores.BrandsStore;
import pingo.mobile.com.ui.common.TabLayout;
import pingo.mobile.com.utils.constants.Bundles;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ProfileContainerFragment extends Fragment implements View.OnClickListener {
    /**
     * Setter of base fragment toolbar.
     */
    ViewPager pager;
    ProfilePagerAdapter adapter;
    TabLayout tabs;

    /**
     * Defines the total number of tabs.
     */
    int numberOfTabs = 3;
    int brandId;
    private HeaderFragment headerFragment;

    /**
     * Default oncerateView function.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.brand_profile_base_fragment, container, false);
        brandId = getActivity().getIntent().getExtras().getInt(Bundles.OPENED_BRAND_ID);
        /**
         * Creating The HomePagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
         */
        CharSequence Titles[] = {
                getResources().getString(R.string.brands_info_tab),
                getResources().getString(R.string.brands_products_tab),
                getResources().getString(R.string.brands_stores_tab)
        };


        adapter = new ProfilePagerAdapter(brandId, getActivity().getSupportFragmentManager(), Titles, numberOfTabs);
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
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
        tabs.setViewPager(pager, TabLayout.ICON_MODE);
        loadInformation();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        headerFragment = (HeaderFragment) getChildFragmentManager().findFragmentById(R.id.brand_profile_header_fragment);
    }

    /**
     *
     */
    void loadInformation() {
        BrandsStore
                .getBrandInfo(brandId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Brand>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("EREE", e.toString());
                    }

                    @Override
                    public void onNext(Brand brand) {
                        attachBrandInfo(brand);
                    }
                });
    }

    /**
     * @param brand
     */
    void attachBrandInfo(Brand brand) {
        headerFragment.setBrand(brand);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
