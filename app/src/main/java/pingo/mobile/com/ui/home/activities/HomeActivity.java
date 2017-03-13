package pingo.mobile.com.ui.home.activities;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import pingo.mobile.com.R;
import pingo.mobile.com.ui.brands.activities.BrandsListActivity;
import pingo.mobile.com.ui.common.activities.TourActivity;
import pingo.mobile.com.ui.home.fragments.MainFragment;
import pingo.mobile.com.ui.user.activities.UserBrandsActivity;
import pingo.mobile.com.ui.user.activities.UserProfileActivity;
import pingo.mobile.com.ui.user.activities.SettingsActivity;
import pingo.mobile.com.utils.constants.Bundles;
import pingo.mobile.com.utils.storage.Preferences;


public class HomeActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private DrawerLayout drawerLayout;
    private NavigationView nvDrawer;
    private RelativeLayout mainSearchViewResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.play_again_desc, R.string.play_again_desc) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mToolbar.setAlpha(1 - slideOffset / 2);
            }
        };


        actionBarDrawerToggle.syncState();
        mToolbar.setTitle("");
        getSupportActionBar().setTitle("");
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        mToolbar.setNavigationIcon(R.drawable.ic_menu_white);
        setupDrawerContent(nvDrawer);
        // activate first item
        selectDrawerItem(nvDrawer.getMenu().getItem(0));
        // get saerch list
        mainSearchViewResults = (RelativeLayout) findViewById(R.id.main_search_view_results);
        mainSearchViewResults.setVisibility(View.GONE);
        ButterKnife.bind(this);
    }

    /**
     *
     */
    @OnClick(R.id.take_tour)
    void takeTour() {
        Preferences.getInstance().setFirstTimeLaunch(true);
        Intent intent = new Intent(this, TourActivity.class);
        Bundle bundle = new Bundle();
        // set it to only
        bundle.putString(Bundles.TOUR_SOURCE_CALL_KEY, "menu");
        intent.putExtras(bundle);
        startActivity(intent);
        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    /**
     *
     */
    @OnClick(R.id.about_us)
    void aboutUs() {
        Preferences.getInstance().setFirstTimeLaunch(true);
        Intent intent = new Intent(this, AboutUsActivity.class);
        startActivity(intent);
        drawerLayout.closeDrawer(Gravity.LEFT);
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true); // Do not iconify the widget; expand it by default
        if (searchView != null) {
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(this, SearchResultsActivity.class)));
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setOnCloseListener(new android.widget.SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    mainSearchViewResults.setVisibility(View.GONE);
                    return false;
                }
            });
            searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    mainSearchViewResults.setVisibility(View.VISIBLE);
                    //mainSearchViewResults.bringToFront();
                    return true;
                }
            });
        }
        //super.onCreateOptionsMenu(menu);
        return true;
    }

    /**
     * @param menuItem
     */
    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Intent intent;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                fragmentClass = MainFragment.class;
                break;
            case R.id.nav_brands:
                intent = new Intent(this, BrandsListActivity.class);
                startActivity(intent);
                closeDrawers();
                return;
            case R.id.nav_my_brands:
                intent = new Intent(this, UserBrandsActivity.class);
                startActivity(intent);
                closeDrawers();
                return;
            case R.id.nav_profile:
                intent = new Intent(this, UserProfileActivity.class);
                startActivity(intent);
                closeDrawers();
                return;
            case R.id.nav_settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                closeDrawers();
                return;
            default:
                fragmentClass = MainFragment.class;
        }
        /**
         *
         */

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment alreadyAttached = fragmentManager.findFragmentById(R.id.container_body);
        if (alreadyAttached != null && alreadyAttached instanceof MainFragment) {
            closeDrawers();
        } else {
            fragmentManager.beginTransaction().replace(R.id.container_body, fragment).commit();
            closeDrawers();
        }

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar productName
        // setTitle(menuItem.getTitle());
        // Close the navigation drawer
    }

    /**
     *
     */
    void closeDrawers() {
        drawerLayout.closeDrawers();
        nvDrawer.setCheckedItem(0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_filter) {
            drawerLayout.openDrawer(GravityCompat.END);
        }

        return super.onOptionsItemSelected(item);
    }

}
