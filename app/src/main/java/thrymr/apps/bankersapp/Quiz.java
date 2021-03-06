package thrymr.apps.bankersapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by thrymr on 23/9/15.
 */
public class Quiz extends Fragment {


    public static List<Question> quesList = new ArrayList<Question>();
    View view;
    CheckBox[] userAnswers = {null, null, null, null, null, null, null, null,
            null, null, null, null};
    boolean[] attepmted = {false, false, false, false, false, false, false,
            false, false, false, false, false};
    int correct, wrong, total;
    int score = 0;
    Integer QuizModelNumber = 0;
    Question currentQuizModel;
    TextView txtQuizModel, currentQuestion, totalQuestions;
    RadioGroup rgp;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    String answer;
    Button butNext, butPrevious, butFinish, save, get;
    private TextView timer, totalPoints, correctPoints, wrongPoints;
    public SuperInterface superInterface;
    public static Integer timeInt;
    private CounterClass counterTimer;
    private  Date today;
    private SharedPref sharedPref;
    private String stringDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.daily_challenge_start, container, false);
        ParseObject.registerSubclass(Question.class);
        timer = (TextView) view.findViewById(R.id.textViewCountDown);



        today = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        stringDate = simpleDateFormat.format(today);
        Log.e("","date"+stringDate);
       /* new CountDownTimer(300000, 1000) {

            public void onTick(long millisUntilFinished) {

                timer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timer.setText("done!");
                Integer integer = 300;
                MainActivity.bundle.putInt("totalPoints", total);
                MainActivity.bundle.putInt("time", integer);
                Quiz.this.superInterface.resultScreen();
            }
        }.start();*/

        counterTimer = new CounterClass(60000, 1000);
        timer.setText("01:00");



        totalPoints = (TextView) view.findViewById(R.id.totalPoints);
        correctPoints = (TextView) view.findViewById(R.id.correctPoints);
        wrongPoints = (TextView) view.findViewById(R.id.wrongPoints);
        txtQuizModel = (TextView) view.findViewById(R.id.textView1);
        currentQuestion = (TextView) view.findViewById(R.id.currentQ);
        totalQuestions = (TextView) view.findViewById(R.id.totalQs);
        checkBox1 = (CheckBox) view.findViewById(R.id.checkBox01);
        checkBox2 = (CheckBox) view.findViewById(R.id.checkBox02);
        checkBox3 = (CheckBox) view.findViewById(R.id.checkBox03);
        checkBox4 = (CheckBox) view.findViewById(R.id.checkBox04);
        butNext = (Button) view.findViewById(R.id.nextButton);
        butPrevious = (Button) view.findViewById(R.id.previousButton);
        butFinish = (Button) view.findViewById(R.id.Finish);
        save = (Button) view.findViewById(R.id.save);
        get = (Button) view.findViewById(R.id.get);

        getData();
        counterTimer.start();

        return view;
    }


    private void showQuizModel(Question currentQuizModel) {
        txtQuizModel.setText(currentQuizModel.getQuestion());
        currentQuestion.setText(QuizModelNumber + 1 + "");
        totalQuestions.setText(" of " + quesList.size());
        checkBox1.setText(currentQuizModel.getOptionA());
        checkBox2.setText(currentQuizModel.getOptionB());
        checkBox3.setText(currentQuizModel.getOptionC());
        checkBox4.setText(currentQuizModel.getOptionD());

    }


    private View.OnClickListener listner = new View.OnClickListener() {


        @SuppressLint("ShowToast")
        @Override
        public void onClick(View v) {

            if (v == butPrevious) {
                butNext.setText("Next");
                checkAnswer();
                if (QuizModelNumber == 1) {
                    butPrevious.setEnabled(false);
                }
                QuizModelNumber--;

                if (userAnswers[QuizModelNumber] == null) {
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                } else {
                    userAnswers[QuizModelNumber].setChecked(true);
                }
                if (QuizModelNumber < 10) {
                    currentQuizModel = quesList.get(QuizModelNumber);

                    showQuizModel(currentQuizModel);

                    if (userAnswers[QuizModelNumber] != null) {
                        Log.d("QuizModel number", "" + QuizModelNumber);
                        userAnswers[QuizModelNumber].setChecked(true);
                    }

                } else {
                    String hms = null;
                    String timeLeft = timer.getText().toString();

                    Pattern p = Pattern.compile("(\\d+):(\\d+)");
                    Matcher m = p.matcher(timeLeft);
                    if (m.matches()) {
                        int minutes = Integer.parseInt(m.group(1));
                        int seconds = Integer.parseInt(m.group(2));
                        long timeRemain = (long) minutes * 60 * 1000 + seconds * 1000;

                        long ms = 60000 - timeRemain;
                        System.out.println("hrs=" + minutes + " min=" + seconds + " ms=" + ms);


                        hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)), TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));


                    } else {
                        // throw Exception("Bad time format");
                    }

                    MainActivity.bundle.putInt("totalPoints", total);
                    MainActivity.bundle.putString("time", hms);
                    Quiz.this.superInterface.resultScreen();
                }


                showQuizModel(currentQuizModel);

            }
            if (v == butNext) {
                butPrevious.setEnabled(true);
                checkAnswer();
                QuizModelNumber++;
                Log.e("QuizModelNumber" + QuizModelNumber + "", "size of" + quesList.size());
                if (QuizModelNumber < quesList.size()) {
                    if (userAnswers[QuizModelNumber] == null) {
                        checkBox1.setChecked(false);
                        checkBox2.setChecked(false);
                        checkBox3.setChecked(false);
                        checkBox4.setChecked(false);
                    } else {
                        userAnswers[QuizModelNumber].setChecked(true);
                    }


                    currentQuizModel = quesList.get(QuizModelNumber);

                    if (QuizModelNumber == quesList.size() - 1) {
                        butNext.setText("Finish");
                    }
                    showQuizModel(currentQuizModel);

                } else {
                    sharedPref = new SharedPref(getActivity());

                    checkAnswer();
                    counterTimer.cancel();
                    String hms = null;
                    Log.d("result" + score + "--", "===");

                    String timeLeft = timer.getText().toString();

                    Pattern p = Pattern.compile("(\\d+):(\\d+)");
                    Matcher m = p.matcher(timeLeft);
                    if (m.matches()) {
                        int minutes = Integer.parseInt(m.group(1));
                        int seconds = Integer.parseInt(m.group(2));
                        long timeRemain = (long) minutes * 60 * 1000 + seconds * 1000;
                        Log.e("", "remainig time" + timeRemain);

                        long ms = 60000 - timeRemain;
                        System.out.println("hrs=" + minutes + " min=" + seconds + " ms=" + ms);


                        hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)), TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));


                    } else {
                        // throw Exception("Bad time format");
                    }


                   /* MainActivity.bundle.putInt("totalPoints", total);
                    MainActivity.bundle.putString("time", hms);*/
                    MainActivity.counter++;
                    Log.d("Counter", "ABCD" + MainActivity.counter + "date" + stringDate + "score" + total + "time" + hms);

                    SharedPref.setExamDetails(total + "", hms, "true", stringDate);
                    HashMap<String,String> hash = SharedPref.getExamDetails();

                    Log.e("", "hashmap" + hash);
                    Quiz.this.superInterface.resultScreen();

                }


            }
            if (v == butFinish) {

            }
        }

    };

    public void getData() {
        ParseQuery<Question> query = ParseQuery.getQuery(Question.class);
        try {
            Log.e("=====================", "s" + query.toString() + "777" + query.count());
            quesList = query.find();
            Log.e("size" + quesList.size(), "");

        } catch (ParseException e) {
            e.printStackTrace();
        }


        currentQuizModel = quesList.get(QuizModelNumber);
        showQuizModel(currentQuizModel);
        butNext.setOnClickListener(listner);
        butPrevious.setOnClickListener(listner);
        butFinish.setOnClickListener(listner);

        butPrevious.setEnabled(false);
        checkBox1.setOnCheckedChangeListener(listenerCheck);
        checkBox2.setOnCheckedChangeListener(listenerCheck);
        checkBox3.setOnCheckedChangeListener(listenerCheck);
        checkBox4.setOnCheckedChangeListener(listenerCheck);
    }


    private CompoundButton.OnCheckedChangeListener listenerCheck = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
            if (isChecked) {
                switch (arg0.getId()) {
                    case R.id.checkBox01:
                        userAnswers[QuizModelNumber] = checkBox1;
                        checkBox1.setChecked(true);
                        checkBox2.setChecked(false);
                        checkBox3.setChecked(false);
                        checkBox4.setChecked(false);
                        break;
                    case R.id.checkBox02:
                        userAnswers[QuizModelNumber] = checkBox2;
                        checkBox1.setChecked(false);
                        checkBox2.setChecked(true);
                        checkBox3.setChecked(false);
                        checkBox4.setChecked(false);
                        break;
                    case R.id.checkBox03:
                        userAnswers[QuizModelNumber] = checkBox3;
                        checkBox1.setChecked(false);
                        checkBox2.setChecked(false);
                        checkBox3.setChecked(true);
                        checkBox4.setChecked(false);
                        break;
                    case R.id.checkBox04:
                        userAnswers[QuizModelNumber] = checkBox4;
                        checkBox1.setChecked(false);
                        checkBox2.setChecked(false);
                        checkBox3.setChecked(false);
                        checkBox4.setChecked(true);
                        break;

                }
            }
        }
    };

    public void showNextQuestion() {


        checkAnswer();

        QuizModelNumber++;
        if (userAnswers[QuizModelNumber] == null) {
            checkBox1.setChecked(false);
            checkBox2.setChecked(false);
            checkBox3.setChecked(false);
            checkBox4.setChecked(false);
        } else {
            userAnswers[QuizModelNumber].setChecked(true);
        }

        if (QuizModelNumber < quesList.size()) {
            currentQuizModel = quesList.get(QuizModelNumber);

            if (QuizModelNumber == quesList.size() - 1) {
                butNext.setText("Finish");
            }
            showQuizModel(currentQuizModel);

        } else {


            Log.d("result" + score + "--", "===");
        }
    }

    private void checkAnswer() {

        if (getAnswer() != null) {

            Log.d("Correct answer", "" + currentQuizModel.getQuestion());
            Log.d("Correct answer", "" + currentQuizModel.getAnswer());
            Log.d("Correct answer", "" + currentQuizModel.toString());
            Log.d("your answer", "" + getAnswer().getText().toString());


            if (currentQuizModel.getAnswer().equals(getAnswer().getText().toString())) {
                if (QuizModelNumber != attepmted.length && attepmted[QuizModelNumber] == false) {
                    score++;
                    correct = score * 20;
                    correctPoints.setText(Integer.toString(correct));
                    attepmted[QuizModelNumber] = true;
                    Log.d("correct answer", " score" + score);
                }

            } else {
                wrong = wrong - 10;
                wrongPoints.setText(Integer.toString(wrong));
                totalPoints.setText(Integer.toString(total));
                Log.d("wrong answer", "score" + score);//
            }


            total = correct + wrong;
            totalPoints.setText(Integer.toString(total));
            Log.d("correct" + correct + "wrong" + wrong, "total" + total);
        }

    }

    private CheckBox getAnswer() {

        if (checkBox1.isChecked()) {
            Log.d("elseelse", "" + (CheckBox) view.findViewById(R.id.checkBox01) + "");

            return (CheckBox) view.findViewById(R.id.checkBox01);
        } else if (checkBox2.isChecked()) {

            return (CheckBox) view.findViewById(R.id.checkBox02);
        } else if (checkBox3.isChecked()) {

            return (CheckBox) view.findViewById(R.id.checkBox03);
        } else if (checkBox4.isChecked()) {

            return (CheckBox) view.findViewById(R.id.checkBox04);
        } else {
            Log.d("elseelse", "else");
            return null;

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

    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            sharedPref = new SharedPref(getActivity());
            String hms = "";
            timer.setText("done!");


            long ms = 60000;


            hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)), TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));





            SharedPref.setExamDetails(total + "", hms, "true", stringDate);
            HashMap<String,String> hash = SharedPref.getExamDetails();
            Log.e("","hashmap"+hash);
               /* MainActivity.bundle.putInt("totalPoints", total);
            MainActivity.bundle.putString("time", hms);*/

            Quiz.this.superInterface.resultScreen();

        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);
            timer.setText(hms);
        }
    }

}
