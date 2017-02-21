/**
 * File MainFragment
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 *
 */
package pingo.mobile.com.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pingo.mobile.com.R;
import pingo.mobile.com.api.models.SearchParamsOptions;
import pingo.mobile.com.ui.common.FilterOptionsViewListener;

import pingo.mobile.com.ui.common.SideBarMenuItemOpenedListener;
import pingo.mobile.com.ui.products.ProductsListFragment;
import pingo.mobile.com.ui.products.ProductsMapFragment;


public class MainFragment extends Fragment implements View.OnClickListener {
    private static final String LOG_TAG = "LOG";
    private boolean isSearchOpened = false;
    MainFragmentFilter mainFragmentFilter;
    private ImageView optionsOpenerImageView;
    private LinearLayout filterContainer;
    private LinearLayout sideMenuContainer;
    DrawerLayout drawerLayout;

    @BindView(R.id.main_search_bar)
    TextView textViewMainSearch;


    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        // set filter show/hide
        optionsOpenerImageView = (ImageView) view.findViewById(R.id.home_products_fragment_filter_options_opener_id);
        optionsOpenerImageView.setOnClickListener(this);



        //Initializing viewPager
        viewPager = (ViewPager) view.findViewById(R.id.home_pager);
        setupViewPager(viewPager);

        //Initializing the tablayout
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        setTabLayout(tabLayout);

        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        /**
         * Any changes in adapter content cannot change the size of the RecyclerView itself.
         */
        filterContainer = (LinearLayout) view.findViewById(R.id.right_drawer);
        sideMenuContainer = (LinearLayout) view.findViewById(R.id.left_drawer);



        mainFragmentFilter = (MainFragmentFilter) getChildFragmentManager().findFragmentById(R.id.main_filter_fragment);
        mainFragmentFilter.setFilterOptionsViewListener(new FilterOptionsViewListener() {
            @Override
            public void onFilterChanged(SearchParamsOptions filterOptions) {
                closeRightDrawer();
            }

            @Override
            public void onFilterReset() {
                closeRightDrawer();
            }
        });

        SideBarMenuFragment menuFragment = (SideBarMenuFragment) getChildFragmentManager().findFragmentById(R.id.main_menu_fragment);
        menuFragment.setSideBarMenuItemOpenedListener(new SideBarMenuItemOpenedListener() {
            @Override
            public void onItemOpened() {
                closeLeftDrawer();
            }
        });
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     *
     * @param tabLayout
     */
    private void setTabLayout(TabLayout tabLayout) {
        final int[] mapTabsIcons = {
                R.drawable.ic_map,
                R.drawable.ic_map_white
        };
        final int[] listTabsIcons = {
                R.drawable.ic_bubble_black,
                R.drawable.ic_bubble_white
        };
        tabLayout.getTabAt(0).setIcon(listTabsIcons[1]);
        tabLayout.getTabAt(0).setIcon(listTabsIcons[0]);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                /**
                 * If map
                 */
                int position = tab.getPosition();
                if (position == 0){
                    tab.setIcon(listTabsIcons[1]);
                }
                else {
                    tab.setIcon(mapTabsIcons[1]);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0){
                    tab.setIcon(listTabsIcons[0]);
                }
                else {
                    tab.setIcon(mapTabsIcons[0]);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /***
     *
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(ProductsListFragment.getInstance(), "");
        adapter.addFragment(ProductsMapFragment.getInstance(), "");
        viewPager.setAdapter(adapter);
    }

    @OnClick(R.id.home_products_fragment_search_opener_id)
    void openSearch() {
        if (!isSearchOpened) {
            textViewMainSearch.setVisibility(View.VISIBLE);
            textViewMainSearch.requestFocus(View.FOCUS_LEFT);
        } else {
            textViewMainSearch.setVisibility(View.GONE);
        }
        isSearchOpened = !isSearchOpened ;
    }

    /**
     *
     */
    private void closeRightDrawer() {
        drawerLayout.closeDrawer(filterContainer);
    }
    /**
     *
     */
    private void closeLeftDrawer() {
        drawerLayout.closeDrawer(sideMenuContainer);
    }

    /**
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_products_fragment_filter_options_opener_id:
                drawerLayout.openDrawer(filterContainer);
                break;
        }
    }
}
