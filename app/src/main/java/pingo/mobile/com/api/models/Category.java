
package pingo.mobile.com.api.models;
import pingo.mobile.com.utils.constants.Locale;
import pingo.mobile.com.utils.storage.Preferences;

public class Category {
    private String name;
    private String name_fr;
    private String name_ar;
    public int id;
    public String thumb;

    public Category(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * @return
     */
    public String getName() {
        String userLocale = Preferences.getInstance().getLocale();
        if (userLocale.equals(Locale.AR) && name_ar != null) {
            return name_ar;

        }
        if (userLocale.equals(Locale.FR) && name_fr != null) {
            return name_fr;

        }
        return name;
    }

    /**
     * @return
     */
    public String getThumbnail() {
        return thumb;
    }


    /**
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

}
