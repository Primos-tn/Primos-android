/**
 * File Common
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 * Common fields for retrofit
 */
package pingo.mobile.com.api.services;


import android.content.Context;
import android.util.Log;

import pingo.mobile.com.R;
import pingo.mobile.com.ui.common.Helpers;
import pingo.mobile.com.utils.storage.Preferences;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import pingo.mobile.com.api.routes.common;
import retrofit.RetrofitError;

import static pingo.mobile.com.utils.constants.Api.AUTHENTICATION_REQUEST_HEADER;

public class CommonRestApiService {
    static RestAdapter instance;
    static ErrorHandler customErrorHandler;
    /**
     *
     */
    static ErrorHandler defaultErrorHandler = new ErrorHandler() {
        @Override
        public Throwable handleError(RetrofitError cause) {
            if (customErrorHandler != null) {
                customErrorHandler.handleError(cause);
            }
            return cause;
        }
    };

    /**
     *
     * @return
     */
    public static void setCurrentErrorHandler(ErrorHandler errorHandler) {
        customErrorHandler = errorHandler;
    }
    /**
     *
     * @return
     */
    public static void unSetErrorHandler() {
        customErrorHandler = null;
    }
    /**
     * Return adapter
     *
     * @return
     */
    public static RestAdapter getRestAdapter() {
        if (instance == null) {
            instance = new RestAdapter.Builder()
                    .setEndpoint(common.BASE_API_URL)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setRequestInterceptor(new RequestInterceptor() {
                        @Override
                        public void intercept(RequestFacade request) {
                            if (Preferences.getInstance().isLoggedIn()) {
                                request.addHeader(
                                        AUTHENTICATION_REQUEST_HEADER,
                                        Helpers.formatApiTokenForHeader(Preferences.getInstance().getApiToken()));
                            }
                        }
                    })
                    .setErrorHandler(defaultErrorHandler)
                    .build();
        }
        return instance;
    }

}
