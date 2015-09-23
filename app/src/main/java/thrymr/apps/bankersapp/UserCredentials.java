package thrymr.apps.bankersapp;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by thrymr on 23/9/15.
 */
@ParseClassName("UserCredentials")
public class UserCredentials extends ParseObject {
    public UserCredentials() {

    }

    public  void setEmail(String questionId) {
        put("email_id", questionId);
    }

    public String getEmail() {
        return getString("email_id");
    }

    public void setUserName(String questionId) {
        put("user_name", questionId);
    }

    public String getUserName() {
        return getString("user_name");
    }


}