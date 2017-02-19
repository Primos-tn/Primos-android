/**
 * File UsersService
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 */
package pingo.mobile.com.api.services;


import pingo.mobile.com.api.models.accounts.PushRegistrationRequest;
import pingo.mobile.com.api.models.accounts.PushRegistrationResponse;
import pingo.mobile.com.api.models.accounts.SignInRequest;
import pingo.mobile.com.api.models.accounts.SignInResponse;
import pingo.mobile.com.api.models.accounts.SignUpRequest;
import pingo.mobile.com.api.models.accounts.SignUpResponse;
import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

import pingo.mobile.com.api.routes.accounts;


/**
 * Asynchronous execution requires the last parameter of the method be a Callback
 */
public interface AccountsService {
    /**
     * Register a new push token
     * @param pushRegistrationRequest
     */
    @POST(accounts.REGISTER_PUSH)
    Observable<PushRegistrationResponse> registerPushToken(@Body PushRegistrationRequest pushRegistrationRequest);

    /**
     * Register a new user
     * @param signUpRequest
     */
    @POST(accounts.SIGN_UP)
    Observable<SignUpResponse> register(@Body SignUpRequest signUpRequest);

    /**
     * Sign in
     * @param signInRequest
     */
    @POST(accounts.SIGN_IN)
    Observable<SignInResponse> login(@Body SignInRequest signInRequest);
}