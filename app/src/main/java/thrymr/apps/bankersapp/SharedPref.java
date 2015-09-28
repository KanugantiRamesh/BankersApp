package thrymr.apps.bankersapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by thrymr on 24/9/15.
 */
public class SharedPref {

   static SharedPreferences pref;
    static SharedPreferences examPref;

  static   SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "sharePref";
    private static final String QUESTION = "question";
    private static final String OPTION = "option";






    public SharedPref(Context context) {

        Log.e("","context"+pref+"editor"+editor);
        this._context = context;

            pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editor = pref.edit();

    }

    public static void questionWithOption(String question, String option) {

        editor.putString(QUESTION, question);

        editor.putString(OPTION, option);

        editor.commit();
    }


    public static HashMap<String, String> getOptionSelected() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(QUESTION, pref.getString(QUESTION, null));
        user.put(OPTION, pref.getString(OPTION, null));
        return user;
    }

    public  static  void setExamDetails(String score,String duration,String examStatus,String examDate) {
        editor.putString("total_points",score);
        editor.putString("total_time",duration );
        editor.putString("exam_status",examStatus );
        editor.putString("exam_date", examDate);
        editor.commit();


    }



    public static HashMap<String, String> getExamDetails() {
        HashMap<String, String> examDetails = new HashMap<String, String>();
        examDetails.put("score", pref.getString("total_points", null));
        examDetails.put("duration", pref.getString("total_time", null));
        examDetails.put("status",pref.getString("exam_status","false"));
        examDetails.put("examDate",pref.getString("exam_date",null));
        return examDetails;
    }




}
