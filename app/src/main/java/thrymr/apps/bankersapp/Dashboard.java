package thrymr.apps.bankersapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import thrymr.apps.materialtests.models.DailyChallenge;

@SuppressWarnings("ResourceType")
public class Dashboard extends Fragment {

    private TextView time_of_exam, score, bonus_point, bestscoreTv, bestScoreFont;
    private TextView ic1, ic2, ic3, ic4, ic5, ic6;
    private Integer totalScore, time;
    private LinearLayout mathTest;
    private SuperInterface superInterface;
    private Typeface font;
    private List<DailyChallenge> listValues;
    List<DailyChallenge> dialyList;
    DailyChallenge dialyChallenge;
    private List<Integer> bestScoreList;
    public static String userNameFromSplashScreen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dashboard, container, false);
        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/fontawesome-webfont.ttf");
        userNameFromSplashScreen = SplashScreen.userNameForDialyChallenge;
        totalScore = MainActivity.bundle.getInt("totalPoints");
        listValues = new ArrayList<>();
        bestScoreList = new ArrayList<Integer>();
        dialyList = new ArrayList<DailyChallenge>();

        ParseQuery<DailyChallenge> query = ParseQuery.getQuery("DailyChallenge");
        try {
            listValues = query.find();
        } catch (com.parse.ParseException e) {
            e.printStackTrace();
        }
        for (DailyChallenge dailyChallenge : listValues) {
            if (dailyChallenge.getUserName().equalsIgnoreCase(SplashScreen.personName)) {
                totalScore = Integer.parseInt(dailyChallenge.getUserScoredPoints().toString());
                time = Integer.parseInt(dailyChallenge.getTimeTaken().toString());
                Log.d("Total Time and UserName", "ABCD" + dailyChallenge.getUserName() + totalScore + time);
            }
        }

        mathTest = (LinearLayout) rootView.findViewById(R.id.speedChanlege);
        time_of_exam = (TextView) rootView.findViewById(R.id.exam_time_taken);
        score = (TextView) rootView.findViewById(R.id.score_daily_challenge);

        ic1 = (TextView) rootView.findViewById(R.id.videoicon);
        ic2 = (TextView) rootView.findViewById(R.id.reviewicon);
        ic3 = (TextView) rootView.findViewById(R.id.challengeicon);
        ic4 = (TextView) rootView.findViewById(R.id.leadreboard_icon);
        ic5 = (TextView) rootView.findViewById(R.id.takespeedmathstest);
        ic6 = (TextView) rootView.findViewById(R.id.bolt);
        ic1.setTypeface(font);
        ic2.setTypeface(font);
        ic3.setTypeface(font);
        ic4.setTypeface(font);
        ic5.setTypeface(font);
        ic6.setTypeface(font);
        LinearLayout expert_video = (LinearLayout) rootView.findViewById(R.id.expert_video);
        LinearLayout review_answer = (LinearLayout) rootView.findViewById(R.id.review_answer);
        LinearLayout challenge_friends = (LinearLayout) rootView.findViewById(R.id.challenge_friends);
        LinearLayout math_test = (LinearLayout) rootView.findViewById(R.id.math_test);
        LinearLayout leader_board = (LinearLayout) rootView.findViewById(R.id.leaderboard);
        // getData();
        mathTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("setOnClickListener", "setOnClickListener");
                Dashboard.this.superInterface.speedMathsTest();
            }
        });

        expert_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dashboard.this.superInterface.expertVideo();

            }
        });
        review_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dashboard.this.superInterface.reviewQuestions();
            }
        });
        challenge_friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dashboard.this.superInterface.challengeFriend();

            }
        });


        leader_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dashboard.this.superInterface.leaderBoard();
            }
        });

        bonus_point = (TextView) rootView.findViewById(R.id.bonus_point);
        bestscoreTv = (TextView) rootView.findViewById(R.id.result);
        bestScoreFont = (TextView) rootView.findViewById(R.id.best_scoreFont);
        bestScoreFont.setTypeface(font);

     //   time_of_exam.setText(time.toString() + "sec");
        score.setText(totalScore.toString());
        score.setTypeface(font);
        return rootView;
    }

    public void getData() {
        ParseQuery<DailyChallenge> queryDialy = ParseQuery.getQuery(DailyChallenge.class);
        try {
            Log.e("-------------", "----------------");
            Log.e("=====DailyChallenge===", "s" + queryDialy.toString() + "777" + queryDialy.count());
            dialyList = queryDialy.find();
            Log.e("size of DialyChallenge" + dialyList.size(), "uuu" + dialyList.get(0).getUserName());
          /*for (int i = 0;i<dialyList.size();i++){
              if(userNameFromSplashScreen.equals())
          }*/
            for (DailyChallenge o : dialyList) {
                Log.e("222222222222222", "222222222222222");
                Log.d("pppppp" + o.getUserName() + "", "qqqqq" + userNameFromSplashScreen + "size" + dialyList.size());
                if (o.getUserName().equals(userNameFromSplashScreen)) {
                    Log.e("1111111111111111111", "11111111111111 scorin is" + o.getUserScoredPoints().intValue());
                    bestScoreList.add(o.getUserScoredPoints().intValue());
                }
            }
            Log.d("bestScoreList", "bestScoreList" + bestScoreList.toString());
            Collections.reverse(bestScoreList);
            Log.d("bestScoreList", "bestScoreList" + bestScoreList.get(0));
            bestscoreTv.setText(bestScoreList.get(0) + "");
            Log.e("333333333333333", "333333333333333");
        } catch (com.parse.ParseException e) {
            e.printStackTrace();
        }
        dialyChallenge = dialyList.get(0);
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

        }
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        try {
            this.superInterface = (SuperInterface) activity;
        } catch (final ClassCastException e) {
            throw new ClassCastException(
                    "Activity must implement OnDietDiaryRequestedListener.");
        }

    }
}
