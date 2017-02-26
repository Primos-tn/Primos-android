package pingo.mobile.com.ui.common.activities;

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

import butterknife.ButterKnife;
import butterknife.OnClick;
import pingo.mobile.com.R;
import pingo.mobile.com.ui.brands.fragments.ListFragment;
import pingo.mobile.com.ui.brands.fragments.MineFragment;
import pingo.mobile.com.ui.home.MainFragment;
import pingo.mobile.com.ui.user.fragments.ContainerFragment;
import pingo.mobile.com.ui.user.activities.SettingsActivity;
import pingo.mobile.com.utils.constants.Bundles;
import pingo.mobile.com.utils.storage.Preferences;


public class HomeActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private DrawerLayout drawerLayout;
    private NavigationView nvDrawer;


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
        mToolbar.setNavigationIcon(R.drawable.ic_bubble_white);
        setupDrawerContent(nvDrawer);
        selectDrawerItem(nvDrawer.getMenu().getItem(0));
        ButterKnife.bind(this);
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
        super.onCreateOptionsMenu(menu);
        return true;
    }

    /**
     * @param menuItem
     */
    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                fragmentClass = MainFragment.class;
                break;
            case R.id.nav_trends:
                fragmentClass = MainFragment.class;
                break;
            case R.id.nav_favorites:
                fragmentClass = MineFragment.class;
                break;
            case R.id.nav_profile:
                fragmentClass = ContainerFragment.class;
                break;
            case R.id.nav_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return;
            default:
                fragmentClass = MainFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_body, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar productName
        // setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawerLayout.closeDrawers();
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
