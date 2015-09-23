package thrymr.apps.bankersapp;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by thrymr on 23/9/15.
 */
@ParseClassName("Question")
public class Question extends ParseObject{
    String question;
    String optionA;
    String optionB;
    String optionC;
    String optionD;
    String answer;
    public Question(){
       /* question = "";
        optionA = "";
        optionB = "";
        optionC = "";
        optionD = "";
        answer = "";*/
    }
   /* public Question(String question,String optionA, String optionB, String optionC, String optionD, String answer){
       this.question = question;
        optionA = optionA;
        optionB = optionB;
        optionC = optionC;
        optionD = optionD;
        answer = answer;


    }*/
    public String getQuestion() {
        return getString("question");
    }

    public void setQuestion(String question) {
        this.question = question;
        put("question",question);
    }

    public String getOptionA() {

        return getString("optionA");
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
        put("optionA",optionA);
    }

    public String getOptionB() {

        return getString("optionB");

    }

    public void setOptionB(String optionB)
    {
        this.optionB = optionB;
        put("optionB",optionB);
    }

    public String getOptionC() {

        return getString("optionC");

    }

    public void setOptionC(String optionC) {

        this.optionC = optionC;
        put("optionC",optionC);
    }

    public String getOptionD() {
        return getString("optionD");

    }

   /* @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }*/

    public void setOptionD(String optionD)
    {
        this.optionD = optionD;
        put("optionD",optionD);
    }

    public String getAnswer() {

        return getString("answer");
    }

    public void setAnswer(String answer) {
        this.answer = answer;
        put("answer",answer);
    }
}
