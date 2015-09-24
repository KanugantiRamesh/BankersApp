package thrymr.apps.materialtests.models;


import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by thrmyr on 24/9/15.
 */
@ParseClassName("SpeedMathsChallenge")
public class SpeedMathsChallenge extends ParseObject {


    public String getSpeedUserName() {
        return getString("user_name");
    }

    public void setSpeedUserName(String name) {
        put("user_name", name);
    }

    public Number getSpeedUserScoredPoints() {
        return getNumber("speed_points");
    }

    public void setSpeedUserScoredPoints(Number scoredPoints) {
        put("speed_points", scoredPoints);
    }
    public Date getSpeedDate() {
        return getDate("speed_date");
    }

    public void setSpeedDate(Date date) {
        put("speed_date", date);
    }

    @Override
    public String toString() {
        return getString("user_name")+"\n"+getNumber("speed_points")+"\n"+getDate("updatedAt");
    }
}
