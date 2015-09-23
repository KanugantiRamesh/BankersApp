package thrymr.apps.bankersapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thrymr on 23/9/15.
 */
public class DailyChallenge extends Fragment {

    ListView listView;
    TextView timer;

    List<String> optionsList;
    String answers[] = {"A. entertainment tax", "B. expenditure tax", "C. agricultural income tax", "D. land revenue", "E. Service Tax"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.daily_challenge_start, container, false);

        timer = (TextView) v.findViewById(R.id.textViewCountDown);

        new CountDownTimer(300000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timer.setText("done!");
            }
        }.start();


        optionsList = new ArrayList<>();
        listView = (ListView) v.findViewById(R.id.listViewDailyQuestions);
        for (int index = 0; index < answers.length; index++) {

            optionsList.add(answers[index]);
        }

        ArrayAdapter<String> answerAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_answers, optionsList);
        listView.setAdapter(answerAdapter);


        return v;
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_challenge_start);
        optionsList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listViewDailyQuestions);
        for (int index = 0; index < answers.length; index++) {

            optionsList.add(answers[index]);
        }

        ArrayAdapter<String> answerAdapter = new ArrayAdapter<String>(this, R.layout.list_answers, optionsList);
        listView.setAdapter(answerAdapter);

    }*/
}
