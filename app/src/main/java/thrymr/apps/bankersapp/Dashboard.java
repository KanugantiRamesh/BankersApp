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

@SuppressWarnings("ResourceType")
public class Dashboard extends Fragment {

    TextView time_of_exam, score, bonus_point, best_score;
    TextView ic1, ic2, ic3, ic4, ic5, ic6;
    Integer totalScore, time;
    LinearLayout mathTest;
    private SuperInterface superInterface;
    Typeface font;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dashboard, container, false);
        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/fontawesome-webfont.ttf");
        totalScore = MainActivity.bundle.getInt("totalPoints");
        time = MainActivity.bundle.getInt("time");
        Log.d("Time", "ABDC" + time);

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
        best_score = (TextView) rootView.findViewById(R.id.best_score);
        best_score.setTypeface(font);
        time_of_exam.setText(time.toString() + "sec");
        score.setText(totalScore.toString());
        score.setTypeface(font);
        return rootView;
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
