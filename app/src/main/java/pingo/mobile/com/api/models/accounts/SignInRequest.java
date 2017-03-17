package pingo.mobile.com.api.models.accounts;

import pingo.mobile.com.api.models.User;

/**
 * Created by houssem.fathallah on 23/09/2016.
 */
public class SignInRequest {
    private String login ;
    private String password ;
    /**
     *
     * @param login
     * @param password
     */
    public SignInRequest(String login, String password){
        this.login = login ;
        this.password = password ;
    }
}
