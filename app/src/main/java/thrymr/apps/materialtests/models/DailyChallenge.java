package thrymr.apps.materialtests.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by thrymr on 23/9/15.
 */
@ParseClassName("DailyChallenge")
public class DailyChallenge extends ParseObject {


    public String getUserName() {
        return getString("user_name");
    }

    public void setUserName(String name) {
        put("user_name", name);
    }

    public Number getUserScoredPoints() {
        return getNumber("points_scored");
    }

    public void setUserScoredPoints(Number scoredPoints) {
        put("points_scored", scoredPoints);
    }
    public Date getDate() {
        return getDate("test_date");
    }

    public void setDate(Date date) {
        put("test_date", date);
    }

    @Override
    public String toString() {
        return getString("user_name")+"\n"+getNumber("points_scored");
    }
}
