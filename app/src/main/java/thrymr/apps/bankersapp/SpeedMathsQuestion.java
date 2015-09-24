package thrymr.apps.bankersapp;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by thrymr on 23/9/15.
 */
@ParseClassName("SpeedMathsQuestion")
public class SpeedMathsQuestion extends ParseObject {
    String question;
    String optionA;
    String optionB;
    String optionC;
    String optionD;
    String answer;

    public SpeedMathsQuestion() {

    }

    public String getQuestion() {
        return getString("speed_question");
    }


    public String getOptionA() {

        return getString("optionA");
    }


    public String getOptionB() {

        return getString("optionB");

    }


    public String getOptionC() {

        return getString("optionC");

    }


    public String getOptionD() {
        return getString("optionD");

    }


    public String getAnswer() {

        return getString("answer");
    }


}
