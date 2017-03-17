/**
 * File BrandProfileActivity
 * Project Pingo
 * Created by Tarek .. at 10:09
 * (c) Pingo tn
 * *
 * The base fragment wrapper of application.
 */
package pingo.mobile.com.ui.brands.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pingo.mobile.com.R;
import pingo.mobile.com.api.models.brands.Brand;
import pingo.mobile.com.stores.BrandsStore;
import pingo.mobile.com.ui.brands.BrandProfilePagerAdapter;
import pingo.mobile.com.utils.constants.Bundles;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class BrandProfileContainerFragment extends Fragment implements View.OnClickListener {
    /**
     * Setter of base fragment toolbar.
     */
    ViewPager pager;
    BrandProfilePagerAdapter adapter;
    TabLayout tabLayout;

    /**
     * Defines the total number of tabLayout.
     */
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



        adapter = new BrandProfilePagerAdapter(brandId, getActivity().getSupportFragmentManager());
        /**
         * Assigning ViewPager View and setting the adapter
         */
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(adapter);


        /**
         * Assiging the Sliding Tab Layout View
         */
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int index = tab.getPosition() ;
                tabLayout.getTabAt(index).setIcon(adapter.getIconOn(index));

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int index = tab.getPosition() ;
                tabLayout.getTabAt(index).setIcon(adapter.getIconOff(index));

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // Iterate over all tabs and set the custom view
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (i == 0){
                tab.setIcon(adapter.getIconOn(i));
            }
            else {
                tab.setIcon(adapter.getIconOff(i));
            }

            //tab.setCustomView(adapter.getTabView(getContext(), i));
        }
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
        // Send an Intent with an action named "custom-event-name". The Intent sent should
// be received by the ReceiverActivity.
        Log.d("sender", "Broadcasting message");
        Intent intent = new Intent(Bundles.BRAND_INFO_MESSAGE_RECEIVER);
        // You can also include some extra data.
        intent.putExtra(Bundles.BRAND_PARCELABLE_KEY, brand);
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
