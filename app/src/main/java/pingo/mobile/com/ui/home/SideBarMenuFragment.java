package pingo.mobile.com.ui.home;

import butterknife.OnClick;

import android.support.annotation.Nullable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


import pingo.mobile.com.R;
import pingo.mobile.com.ui.account.LoginActivity;
import pingo.mobile.com.activities.TourActivity;
import pingo.mobile.com.ui.brands.activities.BrandsActivity;
import pingo.mobile.com.ui.common.Dialogs;
import pingo.mobile.com.ui.common.SideBarMenuItemOpenedListener;
import pingo.mobile.com.utils.constants.Bundles;
import pingo.mobile.com.ui.user.activities.ProfileActivity;
import pingo.mobile.com.utils.storage.Preferences;
import pingo.mobile.com.ui.user.activities.SettingsActivity;
import pingo.mobile.com.stores.AccountsStore;
import pingo.mobile.com.ui.user.activities.FavoritesActivity;

/**
 * Created by houssem.fathallah on 03/10/2016.
 */
public class SideBarMenuFragment extends Fragment {
    View view;
    private SideBarMenuItemOpenedListener sideBarMenuItemOpenedListener;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_side_bar_menu, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.brands_menu_id)
    void Brands() {
        Intent intent = new Intent(getContext(), BrandsActivity.class);
        startActivity(intent);
        sideBarMenuItemOpenedListener.onItemOpened();
    }

    /**
     *
     */
    @OnClick(R.id.favorites_menu_id)
    void Favorites() {
        if (!Preferences.getInstance(getContext()).isLoggedIn()) {
            startActivity(new Intent(getContext(), FavoritesActivity.class));
        } else {
            Dialogs.openLogin(getContext(), LoginActivity.class);
        }
        sideBarMenuItemOpenedListener.onItemOpened();
    }


    /**
     *
     */
    @OnClick(R.id.profile_menu_id)
    void profile() {
        if (!Preferences.getInstance(getContext()).isLoggedIn()) {
            startActivity(new Intent(getContext(), ProfileActivity.class));
        } else {
            Dialogs.openLogin(getContext(), LoginActivity.class);
        }
        sideBarMenuItemOpenedListener.onItemOpened();
    }


    /**
     *
     */
    @OnClick(R.id.settings_menu_id)
    void settings() {
        Intent intent = new Intent(getContext(), SettingsActivity.class);
        startActivity(intent);
        sideBarMenuItemOpenedListener.onItemOpened();
    }

    /**
     *
     */
    @OnClick(R.id.login_out_menu_id)
    void logOut() {
        AccountsStore.logout(getContext());
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
        sideBarMenuItemOpenedListener.onItemOpened();
    }

    /**
     *
     */
    @OnClick(R.id.take_tour)
    void takeTour() {
        Intent intent = new Intent(getContext(), TourActivity.class);
        Bundle bundle = new Bundle();
        // set it to only
        bundle.putString(Bundles.TOUR_SOURCE_CALL_KEY, "menu");
        intent.putExtras(bundle);
        startActivity(intent);
        sideBarMenuItemOpenedListener.onItemOpened();
    }

    public void setSideBarMenuItemOpenedListener(SideBarMenuItemOpenedListener sideBarMenuItemOpenedListener) {
        this.sideBarMenuItemOpenedListener = sideBarMenuItemOpenedListener;
    }
}
