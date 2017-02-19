package pingo.mobile.com.api.models.accounts;

import com.google.gson.annotations.SerializedName;

import pingo.mobile.com.api.models.User;

/**
 * Created by houssem.fathallah on 23/09/2016.
 */
public class SignInResponse {
    private boolean ok;
    @SerializedName("api_token")
    private String ApiToken ;
    private User user ;
    private String token;

    /**
     *
     * @return
     */
    public String getToken() {
        return ApiToken;
    }

    public User getUser() {
        return user;
    }
}
