package thrymr.apps.bankersapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by thrymr on 24/9/15.
 */
public class ReviewFragment extends Fragment {
    private List<Question> questionList;
    private TextView currentQs, totalQs, questionTextView, leftArrow, rightArrow;
    Integer QuizModelNumber = 0;
    private Question currentQuizModel;
    private TextView option1, option2, option3, option4;
    private String correctAnswer;
    private TextView imageString, imageString1, imageString2, imageString3;
    Typeface font;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review_layout, container, false);
        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/fontawesome-webfont.ttf");
        currentQs = (TextView) view.findViewById(R.id.currentQs);
        questionTextView = (TextView) view.findViewById(R.id.review_q1);
        totalQs = (TextView) view.findViewById(R.id.totalQ);
        leftArrow = (TextView) view.findViewById(R.id.leftArrow);
        rightArrow = (TextView) view.findViewById(R.id.rightArrow);
        option1 = (TextView) view.findViewById(R.id.answer);
        option2 = (TextView) view.findViewById(R.id.answer1);
        option3 = (TextView) view.findViewById(R.id.answer2);
        option4 = (TextView) view.findViewById(R.id.answer3);

        imageString = (TextView) view.findViewById(R.id.imageString);
        imageString1 = (TextView) view.findViewById(R.id.imageString1);
        imageString2 = (TextView) view.findViewById(R.id.imageString2);
        imageString3 = (TextView) view.findViewById(R.id.imageString3);
        imageString.setTypeface(font);

        leftArrow.setTypeface(font);
        rightArrow.setTypeface(font);
        questionList = Quiz.quesList;

        questionTextView.setText(questionList.get(0).getQuestion());
        option1.setText(questionList.get(0).getOptionA());
        option2.setText(questionList.get(0).getOptionB());
        option3.setText(questionList.get(0).getOptionC());
        option4.setText(questionList.get(0).getOptionD());
        correctAnswer = questionList.get(0).getAnswer();
        if (option1.getText().toString().equalsIgnoreCase(correctAnswer)) {
            imageString.setText(getResources().getString(R.string.correct_icon));
            imageString.setTextColor(getResources().getColor(R.color.green));
            imageString1.setText(getResources().getString(R.string.wrong_answer));
            imageString2.setText(getResources().getString(R.string.wrong_answer));
            imageString3.setText(getResources().getString(R.string.wrong_answer));
            imageString1.setTextColor(getResources().getColor(R.color.red));
            imageString2.setTextColor(getResources().getColor(R.color.red));
            imageString3.setTextColor(getResources().getColor(R.color.red));
        }
        if (option2.getText().toString().equalsIgnoreCase(correctAnswer)) {
            imageString1.setText(getResources().getString(R.string.correct_icon));
            imageString1.setTextColor(getResources().getColor(R.color.green));
            imageString.setText(getResources().getString(R.string.wrong_answer));
            imageString2.setText(getResources().getString(R.string.wrong_answer));
            imageString3.setText(getResources().getString(R.string.wrong_answer));
            imageString.setTextColor(getResources().getColor(R.color.red));
            imageString2.setTextColor(getResources().getColor(R.color.red));
            imageString3.setTextColor(getResources().getColor(R.color.red));
        }
        if (option3.getText().toString().equalsIgnoreCase(correctAnswer)) {
            imageString2.setText(getResources().getString(R.string.correct_icon));
            imageString2.setTextColor(getResources().getColor(R.color.green));

            imageString1.setText(getResources().getString(R.string.wrong_answer));
            imageString.setText(getResources().getString(R.string.wrong_answer));
            imageString3.setText(getResources().getString(R.string.wrong_answer));
            imageString1.setTextColor(getResources().getColor(R.color.red));
            imageString.setTextColor(getResources().getColor(R.color.red));
            imageString3.setTextColor(getResources().getColor(R.color.red));

        }
        if (option4.getText().toString().equalsIgnoreCase(correctAnswer)) {
            imageString.setText(getResources().getString(R.string.correct_icon));
            imageString.setTextColor(getResources().getColor(R.color.green));
            imageString1.setText(getResources().getString(R.string.wrong_answer));
            imageString2.setText(getResources().getString(R.string.wrong_answer));
            imageString3.setText(getResources().getString(R.string.wrong_answer));
            imageString1.setTextColor(getResources().getColor(R.color.red));
            imageString2.setTextColor(getResources().getColor(R.color.red));
            imageString3.setTextColor(getResources().getColor(R.color.red));

        }
        currentQs.setText(QuizModelNumber + 1 + " ");
        totalQs.setText("of " + questionList.size());
        imageString1.setTypeface(font);
        imageString2.setTypeface(font);
        imageString3.setTypeface(font);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizModelNumber--;
                if (QuizModelNumber >= 1) {
                    leftArrow.setVisibility(View.VISIBLE);
                    if (QuizModelNumber == questionList.size() - 1) {
                        leftArrow.setVisibility(View.GONE);
                        rightArrow.setVisibility(View.VISIBLE);
                    }
                }
                if (QuizModelNumber < questionList.size()) {

                    currentQuizModel = questionList.get(QuizModelNumber);
                    correctAnswer = currentQuizModel.getAnswer();
                    if (QuizModelNumber == questionList.size() - 1) {
                        rightArrow.setVisibility(View.GONE);
                    }
                    currentQs.setText(QuizModelNumber + 1 + " ");

                    questionTextView.setText(currentQuizModel.getQuestion());
                    option1.setText(currentQuizModel.getOptionA());
                    option2.setText(currentQuizModel.getOptionB());
                    option3.setText(currentQuizModel.getOptionC());
                    option4.setText(currentQuizModel.getOptionD());


                    if (option1.getText().toString().equalsIgnoreCase(correctAnswer)) {
                        imageString.setText(getResources().getString(R.string.correct_icon));
                        imageString.setTextColor(getResources().getColor(R.color.green));
                        imageString1.setText(getResources().getString(R.string.wrong_answer));
                        imageString2.setText(getResources().getString(R.string.wrong_answer));
                        imageString3.setText(getResources().getString(R.string.wrong_answer));
                        imageString1.setTextColor(getResources().getColor(R.color.red));
                        imageString2.setTextColor(getResources().getColor(R.color.red));
                        imageString3.setTextColor(getResources().getColor(R.color.red));
                    }
                    if (option2.getText().toString().equalsIgnoreCase(correctAnswer)) {
                        imageString1.setText(getResources().getString(R.string.correct_icon));
                        imageString1.setTextColor(getResources().getColor(R.color.green));
                        imageString.setText(getResources().getString(R.string.wrong_answer));
                        imageString2.setText(getResources().getString(R.string.wrong_answer));
                        imageString3.setText(getResources().getString(R.string.wrong_answer));
                        imageString.setTextColor(getResources().getColor(R.color.red));
                        imageString2.setTextColor(getResources().getColor(R.color.red));
                        imageString3.setTextColor(getResources().getColor(R.color.red));
                    }
                    if (option3.getText().toString().equalsIgnoreCase(correctAnswer)) {
                        imageString2.setText(getResources().getString(R.string.correct_icon));
                        imageString2.setTextColor(getResources().getColor(R.color.green));

                        imageString1.setText(getResources().getString(R.string.wrong_answer));
                        imageString.setText(getResources().getString(R.string.wrong_answer));
                        imageString3.setText(getResources().getString(R.string.wrong_answer));
                        imageString1.setTextColor(getResources().getColor(R.color.red));
                        imageString.setTextColor(getResources().getColor(R.color.red));
                        imageString3.setTextColor(getResources().getColor(R.color.red));

                    }
                    if (option4.getText().toString().equalsIgnoreCase(correctAnswer)) {
                        imageString.setText(getResources().getString(R.string.correct_icon));
                        imageString.setTextColor(getResources().getColor(R.color.green));
                        imageString1.setText(getResources().getString(R.string.wrong_answer));
                        imageString2.setText(getResources().getString(R.string.wrong_answer));
                        imageString3.setText(getResources().getString(R.string.wrong_answer));
                        imageString1.setTextColor(getResources().getColor(R.color.red));
                        imageString2.setTextColor(getResources().getColor(R.color.red));
                        imageString3.setTextColor(getResources().getColor(R.color.red));

                    }
                }

            }
        });
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizModelNumber++;
                Log.e("QuizModelNumber" + QuizModelNumber + "", "size of" + questionList.size());
                if (QuizModelNumber < questionList.size()) {

                    currentQuizModel = questionList.get(QuizModelNumber);
                    correctAnswer = currentQuizModel.getAnswer();
                    if (QuizModelNumber == questionList.size() - 1) {
                        rightArrow.setVisibility(View.GONE);
                    }
                    currentQs.setText(QuizModelNumber + 1 + " ");

                    questionTextView.setText(currentQuizModel.getQuestion());
                    option1.setText(currentQuizModel.getOptionA());
                    option2.setText(currentQuizModel.getOptionB());
                    option3.setText(currentQuizModel.getOptionC());
                    option4.setText(currentQuizModel.getOptionD());


                    if (option1.getText().toString().equalsIgnoreCase(correctAnswer)) {
                        imageString.setText(getResources().getString(R.string.correct_icon));
                        imageString.setTextColor(getResources().getColor(R.color.green));
                        imageString1.setText(getResources().getString(R.string.wrong_answer));
                        imageString2.setText(getResources().getString(R.string.wrong_answer));
                        imageString3.setText(getResources().getString(R.string.wrong_answer));
                        imageString1.setTextColor(getResources().getColor(R.color.red));
                        imageString2.setTextColor(getResources().getColor(R.color.red));
                        imageString3.setTextColor(getResources().getColor(R.color.red));
                    }
                    if (option2.getText().toString().equalsIgnoreCase(correctAnswer)) {
                        imageString1.setText(getResources().getString(R.string.correct_icon));
                        imageString1.setTextColor(getResources().getColor(R.color.green));
                        imageString.setText(getResources().getString(R.string.wrong_answer));
                        imageString2.setText(getResources().getString(R.string.wrong_answer));
                        imageString3.setText(getResources().getString(R.string.wrong_answer));
                        imageString.setTextColor(getResources().getColor(R.color.red));
                        imageString2.setTextColor(getResources().getColor(R.color.red));
                        imageString3.setTextColor(getResources().getColor(R.color.red));
                    }
                    if (option3.getText().toString().equalsIgnoreCase(correctAnswer)) {
                        imageString2.setText(getResources().getString(R.string.correct_icon));
                        imageString2.setTextColor(getResources().getColor(R.color.green));

                        imageString1.setText(getResources().getString(R.string.wrong_answer));
                        imageString.setText(getResources().getString(R.string.wrong_answer));
                        imageString3.setText(getResources().getString(R.string.wrong_answer));
                        imageString1.setTextColor(getResources().getColor(R.color.red));
                        imageString.setTextColor(getResources().getColor(R.color.red));
                        imageString3.setTextColor(getResources().getColor(R.color.red));

                    }
                    if (option4.getText().toString().equalsIgnoreCase(correctAnswer)) {
                        imageString.setText(getResources().getString(R.string.correct_icon));
                        imageString.setTextColor(getResources().getColor(R.color.green));
                        imageString1.setText(getResources().getString(R.string.wrong_answer));
                        imageString2.setText(getResources().getString(R.string.wrong_answer));
                        imageString3.setText(getResources().getString(R.string.wrong_answer));
                        imageString1.setTextColor(getResources().getColor(R.color.red));
                        imageString2.setTextColor(getResources().getColor(R.color.red));
                        imageString3.setTextColor(getResources().getColor(R.color.red));

                    }
                }
            }
        });
        return view;
    }

}
