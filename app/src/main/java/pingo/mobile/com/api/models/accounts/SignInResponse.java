package pingo.mobile.com.api.models.accounts;

import com.google.gson.annotations.SerializedName;

import pingo.mobile.com.api.models.User;

/**
 * Created by houssem.fathallah on 23/09/2016.
 */
public class SignInResponse {
    // {"id":1,"account_id":3,"expires":"2017-04-16T22:52:25+01:00","uuid":null,
    // "info":null,"created_at":"2017-03-17T21:52:25.863Z","updated_at":"2017-03-17T21:52:25.863Z",
    // "token_value":"6vjM+9mah1nNEroyHjB/oZ1oSf1dSVUF7HWTcE6AWmx0wSdA9/AvW0FJGhL+Xu4au75H4zBEBy2uTu/4XfRJKA=="}

    @SerializedName("token_value")
    private String tokenValue ;
    @SerializedName("account_id")
    private int accountId ;

    /**
     *
     * @return
     */
    public String getToken() {
        return tokenValue;
    }

    public int getAccountId() {
        return accountId;
    }
}
