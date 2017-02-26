/**
 * File BrandProfileActivity
 * Project Pingo
 * Created by Tarek .. at 10:09
 * (c) Pingo tn
 * *
 * The base fragment wrapper of application.
 */
package pingo.mobile.com.ui.user.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.greenfrvr.hashtagview.HashtagView;

import java.util.ArrayList;

import pingo.mobile.com.R;
import pingo.mobile.com.api.models.Category;
import pingo.mobile.com.stores.CategoriesStore;
import pingo.mobile.com.stores.UsersStore;
import pingo.mobile.com.ui.common.CategoriesTag;
import pingo.mobile.com.utils.storage.Preferences;

import static pingo.mobile.com.ui.common.Colors.getAppARGBColor;

/**
 *
 */
public class ProfilePreferencesFragment extends Fragment {
    /**
     * Default oncerateView function.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    HashtagView hashtagView;
    private MapView mMapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_profile_preferences, container, false);
        hashtagView = (HashtagView) view.findViewById(R.id.user_profile_preferences_tags);
        final ArrayList<Category> categories = CategoriesStore.getCategories();
        hashtagView.setData(categories, CategoriesTag.getTagsTransformer(getActivity()), new HashtagView.DataSelector<Category>() {
            @Override
            public boolean preselect(Category item) {
                return categories.indexOf(item) % 2 == 1;
            }
        });
        hashtagView.addOnTagSelectListener(new HashtagView.TagsSelectListener() {
            @Override
            public void onItemSelected(Object item, boolean selected) {
                Category p = (Category) item;
                Log.d("CATTT", String.valueOf(selected));
            }
        });

        mMapView = (MapView) view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                // For showing a move to my location button
                googleMap.setMyLocationEnabled(true);
                // For dropping a marker at a point on the Map
                LatLng center = Preferences.getInstance().getLastUserLocationMovedTo();
                if (center == null) {
                    center = new LatLng(35, 10);
                }else {
                    String x = "x";
                }
                final Circle circle = googleMap.addCircle(new CircleOptions()
                        .center(center)
                        .radius(50000)
                        .strokeColor(Color.RED)
                        .fillColor(getAppARGBColor()));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(center).zoom(8).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                    @Override
                    public void onCameraIdle() {
                        LatLng center = googleMap.getCameraPosition().target;
                        Preferences.getInstance().setLastUserLocationMovedTo(center);
                        circle.setCenter(center);
                        // TODO add store
                        UsersStore.updateNearInterests(center);
                    }
                });
            }
        });
        return view;
    }
}
