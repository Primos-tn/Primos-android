package pingo.mobile.com.api.models.accounts;

import com.google.gson.annotations.SerializedName;

import pingo.mobile.com.api.models.User;

/**
 * Created by houssem.fathallah on 23/09/2016.
 */
public class PushRegistrationRequest {
    @SerializedName("push_token")
    private String pushToken;
    @SerializedName("old_push_token")
    private String oldPushToken;
    @SerializedName("token")
    private String token;

    public PushRegistrationRequest(String oldPushToken, String newNotificationToken, String apiToken) {
        this.oldPushToken = oldPushToken;
        this.pushToken = newNotificationToken;
        if (apiToken != null) {
            this.token = apiToken;
        }
    }
}
