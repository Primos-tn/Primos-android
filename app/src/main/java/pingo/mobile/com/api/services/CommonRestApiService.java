/**
 * File Common
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 * Common fields for retrofit
 */
package pingo.mobile.com.api.services;


import retrofit.RestAdapter;
import pingo.mobile.com.api.routes.common;

public class CommonRestApiService {
    static RestAdapter instance;

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
                    .build();
        }
        return instance;
    }
}
