package pingo.mobile.com.api.models.accounts;

import pingo.mobile.com.api.models.User;

/**
 * Created by houssem.fathallah on 23/09/2016.
 */
public class SignUpRequest {
    private String username ;
    private String password ;
    private String email ;
    private String passwordConfirmation ;
    private int age ;
    public SignUpRequest(String username, String password){
        this.username = username ;
        this.password = password ;
    }

    /**
     *
     * @param username
     * @param email
     * @param password
     * @param passwordConfirmation
     * @param age
     */
    public SignUpRequest(String username, String email, String password, String passwordConfirmation, int age) {
        this.age = age;
        this.username = username ;
        this.email = email;
        this.passwordConfirmation = passwordConfirmation ;
        this.password = password ;
    }
}
