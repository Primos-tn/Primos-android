package pingo.mobile.com.api.models.accounts;

import com.google.gson.annotations.SerializedName;

import pingo.mobile.com.api.models.User;

/**
 * Created by houssem.fathallah on 23/09/2016.
 */
public class PushRegistrationResponse {
    private boolean ok;
    private User user ;
    public boolean getIsOk (){
        return ok;
    }


}
