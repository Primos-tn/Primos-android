package pingo.mobile.com.utils.fcm;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import pingo.mobile.com.stores.AccountsStore;
import pingo.mobile.com.utils.constants.Permissions;
import pingo.mobile.com.utils.storage.Preferences;

/**
 * Created by houssem.fathallah on 23/09/2016.
 */
public class PingoFireBaseInstanceIdService extends FirebaseInstanceIdService {

    private String TAG = "FirebaseInstanceIdService";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is also called
     * when the InstanceID token is initially generated, so this is where
     * you retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        // TODO: Implement this method to send any registration to your app's servers.
        AccountsStore.registerPushToken(getApplicationContext(), refreshedToken);
    }

    /**
     * @return
     */
    public static String getToken() {
        return FirebaseInstanceId.getInstance().getToken();
    }

}
