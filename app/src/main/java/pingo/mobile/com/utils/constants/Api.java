/**
 * File Api
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 * WebService constants
 */
package pingo.mobile.com.utils.constants;
/**
 * TODO, move this to dynamic
 */
public class Api {
    public static String BASE_API_URL = "https://staging.primos.tn/api/v1/";
    public static String AUTHENTICATION_REQUEST_HEADER = "Authentication bearer";
    public static int API_LIST_PAGE_LIMIT = 10;
    public static int API_MAX_PAGE_LIMIT = 1000;
    public static int getApiLimitPage (){
        return API_LIST_PAGE_LIMIT ;
    }
    public static int getApiMaxPageLimit (){
        return API_MAX_PAGE_LIMIT ;
    }
}
