/**
 * File MapFragment
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 * Fragment code of the fourth Tab
 */
package pingo.mobile.com.ui.brands.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import pingo.mobile.com.R;
import pingo.mobile.com.api.models.Store;
import pingo.mobile.com.api.services.BrandsService;
import pingo.mobile.com.api.services.CommonRestApiService;
import pingo.mobile.com.utils.storage.Preferences;
import retrofit.RestAdapter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class StoresFragment extends Fragment {
    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    MapView mMapView;
    public static StoresFragment storesFragment;
    private GoogleMap googleMap;

    // newInstance constructor for creating fragment with arguments
    public static StoresFragment newInstance(int page, String title) {
        if (storesFragment == null) {
            storesFragment = new StoresFragment();
            Bundle args = new Bundle();
            args.putInt("someInt", page);
            args.putString("someTitle", title);
            storesFragment.setArguments(args);
        }
        return storesFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        /*
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
                LatLng sydney = new LatLng(-34, 151);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                loadLatLng();
            }
        });
        */
        return view;
    }

    /**
     * Place Marker into the map
     *
     * @param lat
     * @param lng
     * @param title
     */
    public void addMarker(double lat, double lng, String title) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lng))
                .title(title));

    }

    /**
     * This method is responsible to import lat and lng information from WebService and to place markers
     */
    private void loadLatLng() {
        /**
         * The RESTAdapter assumes that the URLs and JSON associated with each model are conventional
         */
        RestAdapter restAdapter = CommonRestApiService.getRestAdapter();
        BrandsService service = restAdapter.create(BrandsService.class);
        service
                .getStores(Preferences.getInstance(getContext()).getCurrentBrandId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Store>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Store> stores) {

                    }
                });
    }
}