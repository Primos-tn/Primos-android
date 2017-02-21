package pingo.mobile.com.utils.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import pingo.mobile.com.utils.constants.Application;
import pingo.mobile.com.utils.constants.Locale;

/**
 * Created by Lincoln on 05/05/16.
 */
public class Preferences {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    static Preferences instance;

    // shared pref mode
    int PRIVATE_MODE = 0;

    /**
     * @param context
     * @return
     */
    public static Preferences getInstance(Context context) {
        if (instance != null) {
            return instance;
        }
        instance = new Preferences(context);
        return instance;
    }

    /**
     * Note you need to be sure that it's loaded
     *
     * @return
     */
    public static Preferences getInstance() {
        return instance;
    }

    /**
     * @param context
     */
    public Preferences(Context context) {
        if (instance != null) {
            return;
        }
        this._context = context;
        pref = _context.getSharedPreferences(Application.PREFERENCES_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * @param isFirstTime
     */
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(PreferencesConstants.IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    /**
     * @return
     */
    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(PreferencesConstants.IS_FIRST_TIME_LAUNCH, true);
    }

    /**
     * @return
     */
    public int getCurrentBrandId() {
        return pref.getInt(PreferencesConstants.CURRENT_OPENED_BRAND, 1);
    }


    /**
     * @return
     */
    public void setCurrentBrandId(int brandId) {
        editor.putInt(PreferencesConstants.CURRENT_OPENED_BRAND, brandId);
        editor.commit();
    }

    /**
     * @return
     */
    public boolean isLoggedIn() {
        return pref.getBoolean(PreferencesConstants.IS_LOGGED_IN, false);

    }

    /**
     * @return
     */
    public void setIsLoggedIn(boolean isLoggedIn) {
        editor.putBoolean(PreferencesConstants.IS_LOGGED_IN, isLoggedIn);
        editor.commit();
    }

    public void setCurrentUserId(int currentUserId) {
        editor.putInt(PreferencesConstants.CURRENT_USER_ID, currentUserId);
        editor.commit();
    }

    public int getCurrentUserId() {
        return pref.getInt(PreferencesConstants.CURRENT_USER_ID, 1);
    }

    /**
     * @param currentUserId
     */
    public void setCurrentProductId(int currentUserId) {
        editor.putInt(PreferencesConstants.CURRENT_PRODUCT_ID, currentUserId);
        editor.commit();
    }

    /**
     * @return
     */
    public int getCurrentProductId() {
        return pref.getInt(PreferencesConstants.CURRENT_PRODUCT_ID, 1);
    }

    /**
     * @param pushToken
     */
    public void setFireBasePushToken(String pushToken) {
        editor.putString(PreferencesConstants.LAST_FIRE_BASE_TOKEN_VALUE, pushToken);
        editor.commit();
    }

    /**
     *
     */
    public String getFireBasePushToken() {
        return pref.getString(PreferencesConstants.LAST_FIRE_BASE_TOKEN_VALUE, null);
    }


    /**
     *
     */
    public String getLasActiveActivity() {
        return pref.getString(PreferencesConstants.LAST_ACTIVE_ACTIVITY, "");
    }

    /**
     *
     */
    public void setLasActiveActivity(String activityClassName) {
        editor.putString(PreferencesConstants.LAST_ACTIVE_ACTIVITY, activityClassName);
        editor.commit();
    }

    /**
     *
     */
    public void setApiToken(String apiToken) {
        editor.putString(PreferencesConstants.API_TOKEN_VALUE, apiToken);
        editor.commit();
    }

    /**
     *
     */
    public void removeApiToken() {
        editor.remove(PreferencesConstants.API_TOKEN_VALUE);
        editor.commit();
    }

    /**
     *
     */
    public String getApiToken() {
        return pref.getString(PreferencesConstants.API_TOKEN_VALUE, null);
    }

    /**
     *
     */
    public String setLocale(String locale) {
        return pref.getString(PreferencesConstants.USER_LOCALE, null);
    }

    /**
     *
     */
    public String getLocale() {
        return pref.getString(PreferencesConstants.USER_LOCALE, Locale.EN);
    }

    /**
     * @param lastLocation
     */
    public void setLastUserLocationMovedTo(LatLng lastLocation) {
        Gson gson = new Gson();
        String json = gson.toJson(lastLocation);
        editor.putString(PreferencesConstants.USER_LAST_LOCATION_MOVED, json);
        editor.commit();
    }

    /**
     * @return
     */
    public LatLng getLastUserLocationMovedTo() {
        Gson gson = new Gson();
        String json = pref.getString(PreferencesConstants.USER_LAST_LOCATION_MOVED, "");
        return gson.fromJson(json, LatLng.class);
    }
}