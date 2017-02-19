package pingo.mobile.com.api.models.accounts;

import pingo.mobile.com.api.models.User;

/**
 * Created by houssem.fathallah on 23/09/2016.
 */
public class SignInRequest {
    private String username ;
    private String password ;
    /**
     *
     * @param username
     * @param password
     */
    public SignInRequest(String username, String password){
        this.username = username ;
        this.password = password ;
    }
}
