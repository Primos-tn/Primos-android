package pingo.mobile.com.api.routes;

/**
 * Created by houssem.fathallah on 18/02/2017.
 */

public class common {
    public static final String BASE_URL = "https://staging.primos.tn/";
    /**
     * Base api url
     * FIXME  change this as xml value
     */
    public static final String BASE_API_URL = BASE_URL + "api/v1/";
    /**
     * Media url
     */
    public static final String MEDIA_URL = BASE_URL + "media/";
    public static int API_LIST_PAGE_LIMIT = 10;

    /**
     * @return
     */
    public static int getApiLimitPage() {
        return API_LIST_PAGE_LIMIT;
    }

    /**
     *
     * @param relativePath
     * @return
     */
    public static String getMediaUrl(String relativePath) {
        return MEDIA_URL + relativePath;
    }
}
