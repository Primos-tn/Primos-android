
package pingo.mobile.com.api.models;


import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    public int id;
    private String username;
    public String thumb;

    public User(String username, int id) {
        this.username = username;
        this.id = id;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @return
     */
    public String getThumbnail() {
        return thumb;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    public String getUserName() {
        return name;
    }

    public static List<User> createContactsList(int numContacts, int offset) {
        List<User> contacts = new ArrayList<>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new User("user_i" + String.valueOf(i), i + offset));
        }

        return contacts;
    }

    public boolean isOnline() {
        return true;
    }
}
