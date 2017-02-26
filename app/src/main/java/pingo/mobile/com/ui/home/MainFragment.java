/**
 * File MainFragment
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 */
package pingo.mobile.com.ui.home;

import android.os.Bundle;
import android.os.CountDownTimer;
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

import java.text.BreakIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import pingo.mobile.com.R;

import pingo.mobile.com.ui.products.fragments.ProductsHottestFragment;
import pingo.mobile.com.ui.products.fragments.ProductsListFragment;
import pingo.mobile.com.ui.products.fragments.ProductsMapFragment;


public class MainFragment extends Fragment implements View.OnClickListener {
    private static final String LOG_TAG = "LOG";
    private boolean isSearchOpened = false;
    MainFragmentFilter mainFragmentFilter;
    private ImageView optionsOpenerImageView;
    private LinearLayout filterContainer;
    private LinearLayout sideMenuContainer;
    DrawerLayout drawerLayout;

//    @BindView(R.id.main_search_bar)
//    TextView textViewMainSearch;


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainViewPagerAdapter adapter;
    private TextView timerText;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        View view = inflater.inflate(R.layout.home_main_fragment, container, false);

        setTabLayout(view);

        setTimer(view);
        // Iterate over all tabs and set the custom view
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(getContext(), i));
        }
//        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
//        /**
//         * Any changes in adapter content cannot change the size of the RecyclerView itself.
//         */
//        filterContainer = (LinearLayout) view.findViewById(R.id.right_drawer);
//        sideMenuContainer = (LinearLayout) view.findViewById(R.id.left_drawer);
//
//
//
//        mainFragmentFilter = (MainFragmentFilter) getChildFragmentManager().findFragmentById(R.id.main_filter_fragment);
//        mainFragmentFilter.setFilterOptionsViewListener(new FilterOptionsViewListener() {
//            @Override
//            public void onFilterChanged(SearchParamsOptions filterOptions) {
//                closeRightDrawer();
//            }
//
//            @Override
//            public void onFilterReset() {
//                closeRightDrawer();
//            }
//        });
//
//        SideBarMenuFragment menuFragment = (SideBarMenuFragment) getChildFragmentManager().findFragmentById(R.id.main_menu_fragment);
//        menuFragment.setSideBarMenuItemOpenedListener(new SideBarMenuItemOpenedListener() {
//            @Override
//            public void onItemOpened() {
//                closeLeftDrawer();
//            }
//        });
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * @param timeStamp
     * @return
     */
    private String getDate(long timeStamp) {
        long toHours = TimeUnit.MILLISECONDS.toHours(timeStamp);
        long toMinutes = TimeUnit.MILLISECONDS.toMinutes(timeStamp) -
                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeStamp));
        long toSeconds = TimeUnit.MILLISECONDS.toSeconds(timeStamp) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeStamp));
        return String.format("%d hours, %d min, %d sec",
                toHours,
                toMinutes,
                toSeconds
        );
    }

    private void setTimer(View view) {
        timerText = (TextView) view.findViewById(R.id.day_timer_text);
        final Calendar tomorrow = Calendar.getInstance();
        tomorrow.set(Calendar.HOUR_OF_DAY, 0);
        tomorrow.set(Calendar.MINUTE, 0);
        tomorrow.set(Calendar.SECOND, 0);
        tomorrow.set(Calendar.MILLISECOND, 0);
        tomorrow.add(Calendar.DATE, 1);
        new CountDownTimer(tomorrow.getTimeInMillis() - System.currentTimeMillis(), 1000) {

            public void onTick(long millisUntilFinished) {
                timerText.setText(getDate(tomorrow.getTimeInMillis() - System.currentTimeMillis()));
            }

            public void onFinish() {
                timerText.setText("done!");
            }
        }.start();

    }

    /***
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(ProductsListFragment.getInstance(), "");
        adapter.addFragment(ProductsMapFragment.getInstance(), "");
        adapter.addFragment(ProductsHottestFragment.getInstance(), "");
        viewPager.setAdapter(adapter);
    }

    /**
     * @param view
     */
    private void setTabLayout(View view) {
        adapter = new MainViewPagerAdapter(getActivity().getSupportFragmentManager());
        //Initializing viewPager
        viewPager = (ViewPager) view.findViewById(R.id.home_pager);
        setupViewPager(viewPager);

        //Initializing the tablayout
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        final int[] mapTabsIcons = {
                R.drawable.ic_map,
                R.drawable.ic_map_white
        };
        final int[] mapHottestIcons = {
                R.drawable.ic_hot_white,
                R.drawable.ic_hot_black
        };
        final int[] listTabsIcons = {
                R.drawable.ic_bubble_black,
                R.drawable.ic_bubble_white
        };
        tabLayout.getTabAt(0).setIcon(listTabsIcons[1]);
        tabLayout.getTabAt(1).setIcon(mapTabsIcons[0]);
        tabLayout.getTabAt(2).setIcon(mapHottestIcons[0]);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                /**
                 * If map
                 */
                int position = tab.getPosition();
                if (position == 0) {
                    tab.setIcon(listTabsIcons[1]);
                } else if (position == 1) {
                    tab.setIcon(mapTabsIcons[1]);
                } else {
                    tab.setIcon(mapHottestIcons[1]);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    tab.setIcon(listTabsIcons[0]);
                } else if (position == 1) {
                    tab.setIcon(mapTabsIcons[0]);
                } else {
                    tab.setIcon(mapTabsIcons[0]);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

//    @OnClick(R.id.home_products_fragment_search_opener_id)
//    void openSearch() {
//        if (!isSearchOpened) {
//            textViewMainSearch.setVisibility(View.VISIBLE);
//            textViewMainSearch.requestFocus(View.FOCUS_LEFT);
//        } else {
//            textViewMainSearch.setVisibility(View.GONE);
//        }
//        isSearchOpened = !isSearchOpened ;
//    }

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
