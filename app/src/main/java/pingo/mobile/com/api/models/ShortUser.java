
package pingo.mobile.com.api.models;


public class ShortUser {
    private String username;
    public Integer id;
    public String thumb;
    /**
     *
     * @return
     */
    public String getUserName() {
        return username;
    }
    /**
     *
     * @return
     */
    public int getUserId() {
        return id;
    }
    /**
     *
     * @return
     */
    public String getThumb() {
        return "https://s3-us-west-1.amazonaws.com/hawaii-com-wp/wp-content/uploads/2015/08/24125942/Hilton-Hawaiian-Village-1024x589.jpg";
    }
}
