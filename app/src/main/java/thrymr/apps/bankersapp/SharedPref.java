package thrymr.apps.bankersapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by thrymr on 24/9/15.
 */
public class SharedPref {

    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;
    public static final String PREF_NAME = "resultReview";
    public static final String QUESTION = "question";
    public static final String OPTION = "option";
    public Set<String> stringQuestionSet;
    public Set<String> stringOptionSet;


    public SharedPref(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void questionWithOption(List<LinkedHashMap<String, String>> hashMaps) {
        stringQuestionSet = new LinkedHashSet<String>();
        stringOptionSet = new LinkedHashSet<String>();
        for (LinkedHashMap<String, String> map : hashMaps) {
            for (Map.Entry<String, String> mapEntry : map.entrySet()) {
                String key = mapEntry.getKey();
                String value = mapEntry.getValue();
                stringQuestionSet.add(key);
                stringOptionSet.add(value);
            }
        }
        Log.d("stringQuestionSetShared", "ABCD" + stringQuestionSet);
        Log.d("stringOptionSetShared", "ABCD" + stringOptionSet);
        editor.putStringSet(QUESTION, stringQuestionSet);
        editor.putStringSet(OPTION, stringOptionSet);

        editor.commit();
    }


    public LinkedHashMap<String, Set<String>> getOptionSelected() {
        LinkedHashMap<String, Set<String>> user = new LinkedHashMap<String, Set<String>>();
        user.put(QUESTION, pref.getStringSet(QUESTION, null));
        user.put(OPTION, pref.getStringSet(OPTION, null));
        return user;
    }
}
