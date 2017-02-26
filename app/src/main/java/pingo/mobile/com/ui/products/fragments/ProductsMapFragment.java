/**
 * File MapFragment
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 * Fragment code of the fourth Tab
 */
package pingo.mobile.com.ui.products.fragments;

import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import pingo.mobile.com.R;
import pingo.mobile.com.api.responses.ProductsApiResponse;
import pingo.mobile.com.stores.ProductsStore;
import pingo.mobile.com.utils.storage.Preferences;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.content.Context.LOCATION_SERVICE;


public class ProductsMapFragment extends Fragment {
    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    MapView mMapView;
    public static ProductsMapFragment storesFragment;
    private GoogleMap googleMap;
    private LocationManager mLocationManager;
    LatLngBounds oldBounds;
    private CameraUpdate cameraUpdate;

    GoogleMap.OnCameraIdleListener onCameraIdleListener = new GoogleMap.OnCameraIdleListener() {
        @Override
        public void onCameraIdle() {
            loadData(true);
        }
    };

    // newInstance constructor for creating fragment with arguments
    public static ProductsMapFragment getInstance() {
        if (storesFragment == null) {
            storesFragment = new ProductsMapFragment();
        }
        return storesFragment;
    }

    Circle circle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.brand_stores_map_fragment, container, false);
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
                // request gps
                mLocationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
                initFirstPosition();
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 20, 500, mLocationListener);
                googleMap.setOnCameraIdleListener(onCameraIdleListener);
                loadData(false);
            }
        });

        return view;
    }

    /**
     *
     */
    private void initFirstPosition() {
        Location locationGPS = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location locationNet = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (locationGPS != null) {
            updatePosition(locationGPS.getLatitude(), locationGPS.getLongitude(), true);
        } else if (locationNet != null) {
            updatePosition(locationNet.getLatitude(), locationNet.getLongitude(), true);
        } else {
            LatLng last = Preferences.getInstance().getLastUserLocationMovedTo();
            if (last != null) {
                updatePosition(last.latitude, last.latitude, true);
            }
        }

    }

    /**
     * @param latitude
     * @param longitude
     */
    private void updatePosition(double latitude, double longitude, boolean firstSet) {
        LatLng latLng = new LatLng(latitude, longitude);
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, firstSet ? 12 : googleMap.getCameraPosition().zoom);
        googleMap.moveCamera(cameraUpdate);
        if (circle == null) {
            setCircle(latLng);
        } else {
            circle.setCenter(latLng);
        }

    }

    /**
     *
     */
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            //your code here
            updatePosition(location.getLatitude(), location.getLongitude(), false);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

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
     *
     */
    public void setCircle(LatLng latLng) {
        circle = googleMap.addCircle(new CircleOptions().center(latLng)
                .strokeColor(R.color.ColorPrimary).radius(50));
        ValueAnimator vAnimator = new ValueAnimator();
        vAnimator.setRepeatCount(ValueAnimator.INFINITE);
        vAnimator.setRepeatMode(ValueAnimator.RESTART);  /* PULSE */
        vAnimator.setIntValues(0, 100);
        vAnimator.setDuration(5000);
        vAnimator.setEvaluator(new IntEvaluator());
        vAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        vAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedFraction = valueAnimator.getAnimatedFraction();
                // Log.e("", "" + animatedFraction);
                circle.setRadius(animatedFraction * 10000);
            }
        });
        vAnimator.start();
    }

    /**
     * @return
     */
    boolean needToLoadData(LatLngBounds newLatLngBounds) {
        // check
        if (oldBounds.contains(newLatLngBounds.northeast) && oldBounds.contains(newLatLngBounds.southwest)) {
            googleMap.addPolyline(new PolylineOptions()
                    .add(newLatLngBounds.northeast, newLatLngBounds.southwest)
                    .width(5)
                    .color(Color.RED));
            return false;
        }
        return true;

    }

    /**
     *
     */
    double getDistanceFromCenter() {
        LatLng center = oldBounds.getCenter();
        Location centerLocation = new Location("");
        centerLocation.setLatitude(center.latitude);
        centerLocation.setLongitude(center.longitude);
        LatLng southwest = oldBounds.southwest;

        Location southWestLocation = new Location("");
        southWestLocation.setLatitude(southwest.latitude);
        southWestLocation.setLongitude(center.longitude);
        return centerLocation.distanceTo(southWestLocation);

    }

    /**
     * This method is responsible to import lat and lng information from WebService and to place markers
     */
    private void loadData(final boolean updateLastLocation) {
        /**
         * The RESTAdapter assumes that the URLs and JSON associated with each model are conventional
         */
        LatLngBounds newLatLngBounds = googleMap.getProjection().getVisibleRegion().latLngBounds;
        // check
        if (oldBounds != null && !needToLoadData(newLatLngBounds)) {
            return;
        }
        oldBounds = newLatLngBounds;
        ProductsStore
                .getProducts(oldBounds.getCenter(), getDistanceFromCenter())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductsApiResponse>() {
                               @Override
                               public void onCompleted() {

                               }

                               @Override
                               public void onError(Throwable e) {

                               }

                               @Override
                               public void onNext(ProductsApiResponse products) {
                                   if (updateLastLocation) {
                                       Preferences.getInstance().setLastUserLocationMovedTo(googleMap.getCameraPosition().target);
                                   }
                                   // TODO update marker
                               }
                           }

                );
    }
}