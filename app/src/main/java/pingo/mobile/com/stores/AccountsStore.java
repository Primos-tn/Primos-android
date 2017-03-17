package pingo.mobile.com.stores;

import android.content.Context;

import java.util.HashMap;

import pingo.mobile.com.api.models.accounts.PushRegistrationRequest;
import pingo.mobile.com.api.models.accounts.PushRegistrationResponse;
import pingo.mobile.com.api.models.accounts.SignInRequest;
import pingo.mobile.com.api.models.accounts.SignInResponse;
import pingo.mobile.com.api.models.accounts.SignUpRequest;
import pingo.mobile.com.api.models.accounts.SignUpResponse;
import pingo.mobile.com.api.services.AccountsService;
import pingo.mobile.com.api.services.CommonRestApiService;
import pingo.mobile.com.utils.constants.Api;
import pingo.mobile.com.utils.fcm.PingoFireBaseInstanceIdService;
import pingo.mobile.com.utils.storage.Preferences;
import retrofit.RestAdapter;
import rx.Observable;

/**
 * Class for handling accounts data
 * Created by houssem.fathallah on 20/09/2016.
 */
public class AccountsStore {
    /**
     * @param context
     * @param signInResponse
     */
    public static void setApiToken(Context context, SignInResponse signInResponse) {
        Preferences.getInstance(context).setApiToken(signInResponse.getToken());
        Preferences.getInstance(context).setIsLoggedIn(true);
    }

    /**
     * @param context
     */
    public static void clearUserData(Context context) {
        Preferences.getInstance(context).removeApiToken();
    }

    public static Observable<PushRegistrationResponse> attachUserToNotification(Context context) {
        String token = Preferences.getInstance(context).getFireBasePushToken();
        if (token == null) {
            token = PingoFireBaseInstanceIdService.getToken();
        }
        return AccountsStore.registerPushToken(context, token);
    }

    /**
     * @param context
     * @param newNotification
     * @return
     */
    public static Observable<PushRegistrationResponse> registerPushToken(Context context, String newNotification) {
        String oldNotificationToken = Preferences.getInstance(context).getFireBasePushToken();
        String apiToken = Preferences.getInstance(context).getApiToken();
        RestAdapter restAdapter = CommonRestApiService.getRestAdapter();
        AccountsService service = restAdapter.create(AccountsService.class);
        // update
        Preferences.getInstance(context).setFireBasePushToken(newNotification);
        return service.registerPushToken(new PushRegistrationRequest(apiToken, oldNotificationToken, newNotification));

    }

    /**
     * @param username
     * @param email
     * @param password
     * @param passwordConfirmation
     * @param age
     * @return
     */
    public static Observable<SignUpResponse> register(String username, String email,
                                                      String password, String passwordConfirmation,
                                                      int age) {
        RestAdapter restAdapter = CommonRestApiService.getRestAdapter();
        AccountsService service = restAdapter.create(AccountsService.class);
        return service.register(new SignUpRequest(username, email, password, passwordConfirmation, age));
    }

    /**
     * Login to app server
     *
     * @param usernameValue
     * @param passwordValue
     * @return
     */
    public static Observable<SignInResponse> login(String usernameValue, String passwordValue) {
        RestAdapter restAdapter = CommonRestApiService.getRestAdapter();
        AccountsService service = restAdapter.create(AccountsService.class);
        HashMap request = new HashMap<String, SignInRequest>();
        request.put(Api.ACCOUNT_BODY_KEY, new SignInRequest(usernameValue, passwordValue));
        return service.login(request);
    }

    /**
     * Log out
     *
     * @param context
     * @return
     */
    public static void logout(Context context) {
        Preferences.getInstance(context).removeApiToken();
        AccountsStore.clearUserData(context);
        //
    }
}
