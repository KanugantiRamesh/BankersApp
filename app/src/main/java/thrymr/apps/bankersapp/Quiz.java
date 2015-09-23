package thrymr.apps.bankersapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thrymr on 23/9/15.
 */
public class Quiz extends Fragment {


    List<Question> quesList = new ArrayList<Question>();
    View view;
    CheckBox[] userAnswers = {null, null, null, null, null, null, null, null,
            null, null, null, null};
    boolean[] attepmted = {false, false, false, false, false, false, false,
            false, false, false, false, false};
    static int correct, wrong, total;

    static int score = 0;
    Integer QuizModelNumber = 0;
    Question currentQuizModel;


    TextView txtQuizModel;
    RadioGroup rgp;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    String answer;

    Button butNext, butPrevious, butFinish, save, get;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.quiz, container, false);
        ParseObject.registerSubclass(Question.class);
        txtQuizModel = (TextView) view.findViewById(R.id.textView1);
        checkBox1 = (CheckBox) view.findViewById(R.id.checkBox01);
        checkBox2 = (CheckBox) view.findViewById(R.id.checkBox02);
        checkBox3 = (CheckBox) view.findViewById(R.id.checkBox03);
        checkBox4 = (CheckBox) view.findViewById(R.id.checkBox04);
        butNext = (Button) view.findViewById(R.id.nextButton);
        butPrevious = (Button) view.findViewById(R.id.previousButton);
        butFinish = (Button) view.findViewById(R.id.Finish);
        save = (Button) view.findViewById(R.id.save);
        get = (Button) view.findViewById(R.id.get);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        return view;
    }


    private void showQuizModel(Question currentQuizModel) {
        txtQuizModel.setText("Q" + (QuizModelNumber + 1) + "\n   "
                + currentQuizModel.getQuestion());
        // txtQuizModel.set
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

                   /* Intent intent = new Intent(getBaseContext(),
                            ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score); // Your score
                    intent.putExtras(b); // Put your score to your next Intent
                    score = 0;
                    startActivity(intent);*/
                    getActivity().finish();

                }


                showQuizModel(currentQuizModel);

            }
            if (v == butNext) {
                butPrevious.setEnabled(true);

                // Log.d("storing ans of que no"+QuizModelNumber,userAnswers[QuizModelNumber].getText().toString());

                checkAnswer();


                // Log.d("storing ans of que no   after checking "+QuizModelNumber,userAnswers[QuizModelNumber].getText().toString());
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

                    Log.d("result" + score + "--", "===");
                /*    Intent intent = new Intent(MainActivity.this,
                            ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score); // Your score
                    intent.putExtras(b);// Put your score to your next Intent
                    score = 0;
                    startActivity(intent);*/
                    getActivity().finish();

                }

            }
            if (v == butFinish) {
                checkAnswer();
               /* Intent intent = new Intent(MainActivity.this,
                        ResultActivity.class);
                Bundle b = new Bundle();
                b.putInt("score", score); // Your score
                intent.putExtras(b);
                score = 0;
                startActivity(intent);*/
                getActivity().finish();
            }
        }

    };

    public void save() {
       /* Question q = new Question();
        q.setQuestion(" Which of the following is a valid declaration of an object of class Box?");
        q.setOptionA("Box obj = new Box();");
        q.setOptionB("Box obj = new Box;");
        q.setOptionC("obj = new Box();");
        q.setOptionD("new Box obj;");
        q.setAnswer("Box obj = new Box();");
        q.saveInBackground();
*/

    }

    public void getData() {
        ParseQuery<Question> query = ParseQuery.getQuery(Question.class);
        try {
            Log.e("=====================", "s" + query.toString() + "777" + query.count());
            quesList = query.find();
            Log.e("size" + quesList.size(), "");
          /*  for (int i = 0; i < obj.size(); i++) {
                Question task = obj.get(i);

                Log.e("testing", "for" + i + task.getQuestion());

            }*/

        } catch (ParseException e) {
            e.printStackTrace();
        }

      /*  query.findInBackground(new FindCallback<Question>() {

            @Override
            public void done(List<Question> tasks, ParseException error) {
                if (tasks != null) {
                    *//*mAdapter.clear();
                    mAdapter.addAll(tasks);*//*
                    List<Question> obj = tasks;
                    for (int i = 0; i < obj.size(); i++) {
                        Question task = obj.get(i);

                        Log.e("testing", "for" + i + task.getQuestion());

                    }

                }
            }
        });*/

       /* query.findInBackground(new FindCallback<Question>() {
            @Override
            public void done(List<Question> tasks, ParseException error) {
                Log.e("------error"+error,"tasks"+tasks);
             *//*   if(error == null){
                    if (tasks != null) {
                    *//**//*mAdapter.clear();
                    mAdapter.addAll(tasks);*//**//*

                        Log.e("size",tasks.size()+"uuu"+tasks.toString());
                        quesList = tasks;
                *//**//*    for (int i = 0; i < obj.size(); i++) {
                        QuizModel task = obj.get(i);

                        Log.e("testing", "for" + i + task.getQuesition());

                    }*//**//*

                    }else{
                        Log.e("null","null");
                    }
                }
                else {
                    Log.e("exception","test"+error);
                }
*//*
            }
        });*/
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
                // statusAttempted[QuizModelNumber] = true;
                switch (arg0.getId()) {
                    case R.id.checkBox01:
                        userAnswers[QuizModelNumber] = checkBox1;
                        checkBox1.setChecked(true);
                        checkBox2.setChecked(false);
                        checkBox3.setChecked(false);
                        checkBox4.setChecked(false);
                        //  showNextQuestion();
                        break;
                    case R.id.checkBox02:
                        userAnswers[QuizModelNumber] = checkBox2;
                        checkBox1.setChecked(false);
                        checkBox2.setChecked(true);
                        checkBox3.setChecked(false);
                        checkBox4.setChecked(false);
                        //  showNextQuestion();
                        break;
                    case R.id.checkBox03:
                        userAnswers[QuizModelNumber] = checkBox3;
                        checkBox1.setChecked(false);
                        checkBox2.setChecked(false);
                        checkBox3.setChecked(true);
                        checkBox4.setChecked(false);
                        //showNextQuestion();
                        break;
                    case R.id.checkBox04:
                        userAnswers[QuizModelNumber] = checkBox4;
                        checkBox1.setChecked(false);
                        checkBox2.setChecked(false);
                        checkBox3.setChecked(false);
                        checkBox4.setChecked(true);
                        //  showNextQuestion();
                        break;

                }
            }
        }
    };

    public void showNextQuestion() {

        // Log.d("storing ans of que no"+QuizModelNumber,userAnswers[QuizModelNumber].getText().toString());

        checkAnswer();

        // Log.d("storing ans of que no   after checking "+QuizModelNumber,userAnswers[QuizModelNumber].getText().toString());
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
                 /*   Intent intent = new Intent(MainActivity.this,
                            ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score); // Your score
                    intent.putExtras(b);// Put your score to your next Intent
                    score = 0;
                    startActivity(intent);
                    finish();*/

        }
    }

    private void checkAnswer() {

        if (getAnswer() != null) {

            Log.d("Correct answer", "" + currentQuizModel.getQuestion());
            Log.d("Correct answer", "" + currentQuizModel.getAnswer());
            Log.d("Correct answer", "" + currentQuizModel.toString());
            Log.d("your answer", "" + getAnswer().getText().toString());
            if (currentQuizModel.getAnswer().equals(getAnswer().getText().toString())) {
                if (attepmted[QuizModelNumber] == false) {
                    score++;
                    correct = correct + 10;
                    total = total + correct;

                    attepmted[QuizModelNumber] = true;
                    Log.d("correct answer", " score" + score);
                }

            } else {
                wrong = wrong - 10;
                total = total - wrong;
                Log.d("wrong answer", "score" + score);//
            }
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
            // Toast.makeText(QuizActivity.this, "please select a option",
            // Toast.LENGTH_SHORT).show();
            return null;

        }

    }


}
