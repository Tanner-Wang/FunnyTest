package com.example.android.funnytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //How many points are the user getting
    int scores = 0;
    // How many questions are the user answering correctly
    int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Display the result of the user doing the test
     */
    public void submitAnswer(View view) {

        //Calculate the scores after the user answering the first, the second and the 3th question
        calculateScores();

        //Whether or not the user answer the 4th question correctly
        boolean answerBSelected = onRadioButtonClicked(R.id.radio_b);
        //Add 3 points if only B being selected
        if (answerBSelected) {
            scores += 3;
            number += 1;
        }

        //Whether or not the user answer the 5th question correctly
        boolean answerAChecked = !(onCheckboxClicked(R.id.checkbox_a));
        boolean answerBChecked = onCheckboxClicked(R.id.checkbox_b);
        boolean answerCChecked = !(onCheckboxClicked(R.id.checkbox_c));
        boolean answerDChecked = onCheckboxClicked(R.id.checkbox_d);
        //Add 4 points if only B and D and both being selected
        if (answerAChecked && answerBChecked && answerCChecked && answerDChecked) {
            scores += 4;
            number += 1;
        }

        //Display the correct answer after the user doing the test
        displayAnswers(R.string.answer1, R.string.answer2, R.string.answer3, R.string.answer4, R.string.answer5);

        //The result of the test
        String message = evaluate(scores);

        //Display the result of the test with a pop-up message
        String text = message + "\n" + getResources().getText(R.string.toast_message).toString() + "\n" + number + "\n" + scores;
        showToast(text);
    }

    /**
     * Set toast
     */
    private static Toast mToast=null;
    private void showToast(String message){
        if(mToast==null){
            mToast=Toast.makeText(this,message,Toast.LENGTH_SHORT);
        }else{
            mToast.setText(message);
        }
        mToast.show();
    }

    /**
     * Reset the scores and the number and delete the correct answer
     */
    public void reset(View view) {
        scores = 0;
        number = 0;
        //
        displayAnswers(R.string.reset_answer, R.string.reset_answer, R.string.reset_answer, R.string.reset_answer, R.string.reset_answer);
    }

    /**
     * Evaluate the performance of the user
     */
    private String evaluate(int points) {
        String message;
        if (points >= 18) {
            message = getResources().getText(R.string.evaluation_1).toString();
        } else if (points >= 12) {
            message = getResources().getText(R.string.evaluation_2).toString();
        } else {
            message = getResources().getText(R.string.evaluation_3).toString();
        }
        return message;
    }

    /**
     * Calculate the scores after the user answering the first, the second and the 3th question
     */
    private void calculateScores() {
        //check the answer of question 1 whether or not being correct
        EditText editText1 = (EditText) findViewById(R.id.edit_answer_1);
        String answer1 = editText1.getText().toString();
        if (answer1 == getResources().getText(R.string.answer1).toString()) {
            scores += 5;
            number += 1;
        }

        //check the answer of question 2 whether or not being correct
        EditText editText2 = (EditText) findViewById(R.id.edit_answer_2);
        String answer2 = editText2.getText().toString();
        if (answer2 == getResources().getText(R.string.answer2).toString()) {
            scores += 5;
            number += 1;
        }

        //check the answer of question 3th whether or not being correct
        EditText editText3 = (EditText) findViewById(R.id.edit_answer_3);
        String answer3 = editText3.getText().toString();
        if (answer3 == getResources().getText(R.string.answer3).toString()) {
            scores += 5;
            number += 1;
        }
    }

    /**
     * Check the answer of question 4th whether or not being correct
     */
    public boolean onRadioButtonClicked(int radioButtonId) {
        RadioButton radiobutton = (RadioButton) findViewById(radioButtonId);
        return radiobutton.isChecked();
    }

    /**
     * Check the answer of question 5th whether or not being correct
     */
    public boolean onCheckboxClicked(int checkboxId) {
        CheckBox checkbox = (CheckBox) findViewById(checkboxId);
        return checkbox.isChecked();
    }

    /**
     * Display the correct answers after clicking the submit button
     */
    private void displayAnswers(int id1, int id2, int id3, int id4, int id5) {
        //display the correct answer for question 1
        TextView textView1 = (TextView) findViewById(R.id.show_answer_1);
        textView1.setText(getResources().getText(id1).toString());

        //display the correct answer for question 2
        TextView textView2 = (TextView) findViewById(R.id.show_answer_2);
        textView2.setText(getResources().getText(id2).toString());

        //display the correct answer for question 3
        TextView textView3 = (TextView) findViewById(R.id.show_answer_3);
        textView3.setText(getResources().getText(id3).toString());

        //display the correct answer for question 4
        TextView textView4 = (TextView) findViewById(R.id.show_answer_4);
        textView4.setText(getResources().getText(id4).toString());

        //display the correct answer for question 5
        TextView textView5 = (TextView) findViewById(R.id.show_answer_5);
        textView5.setText(getResources().getText(id5).toString());
    }
}
